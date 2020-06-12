package com.stylefeng.guns.config;

import com.stylefeng.guns.config.properties.WxMpProperties;
import com.stylefeng.guns.modular.weChartApi.handler.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static me.chanjar.weixin.common.api.WxConsts.EventType;
import static me.chanjar.weixin.common.api.WxConsts.EventType.SUBSCRIBE;
import static me.chanjar.weixin.common.api.WxConsts.EventType.UNSUBSCRIBE;
import static me.chanjar.weixin.common.api.WxConsts.MenuButtonType.CLICK;
import static me.chanjar.weixin.common.api.WxConsts.MenuButtonType.VIEW;
import static me.chanjar.weixin.common.api.WxConsts.XmlMsgType;
import static me.chanjar.weixin.common.api.WxConsts.XmlMsgType.EVENT;
import static me.chanjar.weixin.mp.constant.WxMpEventConstants.CustomerService.*;
import static me.chanjar.weixin.mp.constant.WxMpEventConstants.POI_CHECK_NOTIFY;
import static me.chanjar.weixin.mp.enums.WxMpApiUrl.Menu.*;

@Configuration
@EnableConfigurationProperties(WxMpProperties.class)
@Slf4j
public class WxMpConfiguration {
    @Autowired
    private LogHandler logHandler;
    @Autowired
    private NullHandler nullHandler;
    @Autowired
    private KfSessionHandler kfSessionHandler;
    @Autowired
    private StoreCheckNotifyHandler storeCheckNotifyHandler;
    @Autowired
    private LocationHandler locationHandler;
    @Autowired
    private MenuHandler menuHandler;
    @Autowired
    private MsgHandler msgHandler;
    @Autowired
    private UnsubscribeHandler unsubscribeHandler;
    @Autowired
    private SubscribeHandler subscribeHandler;
    @Autowired
    private ScanHandler scanHandler;
    @Autowired
    private WxMpProperties properties;

    private String API_URL_PREFIX = "https://api.weixin.qq.com/cgi-bin/menu";


    @Bean
    public WxMpService wxMpService() {
        // 代码里 getConfigs()处报错的同学，请注意仔细阅读项目说明，你的IDE需要引入lombok插件！！！！
        final List<WxMpProperties.MpConfig> configs = this.properties.getConfigs();
        if (configs == null) {
            throw new RuntimeException("未找到微信公众号配置");
        }

        WxMpService service = new WxMpServiceImpl();
        service.setMultiConfigStorages(configs
                .stream().map(a -> {
                    WxMpDefaultConfigImpl configStorage = new WxMpDefaultConfigImpl();
                    configStorage.setAppId(a.getAppId());
                    configStorage.setSecret(a.getSecret());
                    configStorage.setToken(a.getToken());
                    configStorage.setAesKey(a.getAesKey());

                    return configStorage;
                }).collect(Collectors.toMap(WxMpDefaultConfigImpl::getAppId, a -> a, (o, n) -> o)));
        createMenu(service);
        return service;
    }


    public void createMenu(WxMpService wxMpService){
//        WxMenuButton button1=new WxMenuButton();
//        button1.setType("miniprogram"); //点击事件按钮,跳转小程序
//        button1.setName("小程序");
//        button1.setUrl("http://m.netwhale.cn/we-shop/rest/wx/index.html");
//        button1.setPagePath("pages/index/homepage");
//        button1.setAppId(minProgramPayProperties.getAppId());

        WxMenuButton button1=new WxMenuButton();
        button1.setName("视你广告");
        button1.setType("view");
        button1.setUrl("http://joeyjava.iask.in/advertising/home1.html");  //必须添加http

        List<WxMenuButton> wxMenuButtons = new ArrayList<>();
        wxMenuButtons.add(button1);

        WxMenu wxMenu = new WxMenu();
        wxMenu.setButtons(wxMenuButtons);

        String menuJson = wxMenu.toJson();
        Menu url = MENU_CREATE;
        if (wxMenu.getMatchRule() != null) {
            url = MENU_ADDCONDITIONAL;
        }

        try {
            String result = wxMpService.post(url, menuJson);
        } catch (WxErrorException e) {
            log.error("菜单创建失败：{}，错误信息{}", menuJson,e.toString());
        }

    }


    @Bean
    public WxMpMessageRouter messageRouter(WxMpService wxMpService) {
        final WxMpMessageRouter newRouter = new WxMpMessageRouter(wxMpService);

        // 记录所有事件的日志 （异步执行）
        newRouter.rule().handler(this.logHandler).next();

        // 接收客服会话管理事件
        newRouter.rule().async(false).msgType(EVENT).event(KF_CREATE_SESSION)
            .handler(this.kfSessionHandler).end();
        newRouter.rule().async(false).msgType(EVENT).event(KF_CLOSE_SESSION)
            .handler(this.kfSessionHandler).end();
        newRouter.rule().async(false).msgType(EVENT).event(KF_SWITCH_SESSION)
            .handler(this.kfSessionHandler).end();

        // 门店审核事件
        newRouter.rule().async(false).msgType(EVENT).event(POI_CHECK_NOTIFY).handler(this.storeCheckNotifyHandler).end();

        // 自定义菜单事件
        newRouter.rule().async(false).msgType(EVENT).event(CLICK).handler(this.menuHandler).end();

        // 点击菜单连接事件
        newRouter.rule().async(false).msgType(EVENT).event(VIEW).handler(this.nullHandler).end();

        // 关注事件
        newRouter.rule().async(false).msgType(EVENT).event(SUBSCRIBE).handler(this.subscribeHandler).end();

        // 取消关注事件
        newRouter.rule().async(false).msgType(EVENT).event(UNSUBSCRIBE).handler(this.unsubscribeHandler).end();

        // 上报地理位置事件
        newRouter.rule().async(false).msgType(EVENT).event(EventType.LOCATION).handler(this.locationHandler).end();

        // 接收地理位置消息
        newRouter.rule().async(false).msgType(XmlMsgType.LOCATION).handler(this.locationHandler).end();

        // 扫码事件
        newRouter.rule().async(false).msgType(EVENT).event(EventType.SCAN).handler(this.scanHandler).end();

        // 默认
        newRouter.rule().async(false).handler(this.msgHandler).end();

        return newRouter;
    }

}
