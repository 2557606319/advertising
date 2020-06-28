package com.stylefeng.guns.modular.weChartApi.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.binarywang.wxpay.bean.request.WxPayRefundRequest;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.stylefeng.guns.config.properties.GunsProperties;
import com.stylefeng.guns.config.properties.WxMpProperties;
import com.stylefeng.guns.config.properties.WxPayProperties;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.util.DateUtil;
import com.stylefeng.guns.core.util.HttpUtils;
import com.stylefeng.guns.core.util.JwtTokenUtil;
import com.stylefeng.guns.modular.system.dao.ClientUserMapper;
import com.stylefeng.guns.modular.system.model.ClientUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 微信公众号业务接口
 */
@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("/restApi/wx/business")
public class WxBusinessController extends BaseController {

    @Autowired
    WxMpProperties wxMpProperties;

    @Autowired
    GunsProperties properties;

    @Autowired
    WxPayService wxPayService;

    @Autowired
    WxPayProperties wxPayProperties;

    @Autowired
    ClientUserMapper clientUserMapper;

    /**
     * 微信业务回调鉴别接口
     * @param fileName
     * @return
     */
    @RequestMapping(value = "/{fileName}",method = RequestMethod.GET)
    public String verify(@PathVariable String fileName){
        return "/"+fileName;
    }

    /**
     * 微信公众号授权
     * @param code 微信响应的获取access_token的code,
     * @param state 邀请人的邀请码
     * @param response
     */
    @RequestMapping(value = "/wxAuthResp/success",method = RequestMethod.GET)
    public void wxAuthResp(String code, String state, HttpServletResponse response){

        //拼接获取access_token 的url
        StringBuffer reqTokenUrl=new StringBuffer(wxMpProperties.getLoginATokenUrl());
        reqTokenUrl.append("&code=").append(code).append("&grant_type=authorization_code");

        //请求 assessToken
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(reqTokenUrl.toString());
        try{
            CloseableHttpResponse reqTokenResponse = httpclient.execute(httpPost);
            JSONObject respBody = JSONObject.parseObject(EntityUtils.toString(reqTokenResponse.getEntity()));
            if(respBody.get("errcode")!=null){
                log.error("微信授权登录，获取access_token失败！errCode:{} errMsg:{}",respBody.get("errcode"),respBody.get("errmsg"));
                response.sendRedirect("http://www.szrfweb.wang/advertising/home1.html");
                return;
            }

            String accessToken=respBody.get("access_token").toString();
            String refreshToken=respBody.get("refresh_token").toString();
            String openId=respBody.get("openid").toString();
            log.info("微信授权登陆获取access_token={},openId={}",accessToken,openId);

            //请求微信用户信息
            StringBuffer reqUserInfoUrl=new StringBuffer(wxMpProperties.getUserInfoUrl());
            reqUserInfoUrl.append("?access_token=").append(accessToken).append("&openid=").append(openId).append("&lang=zh_CN");
            HttpGet httpGet = new HttpGet(reqUserInfoUrl.toString());
            CloseableHttpResponse reqUserInfoResponse = httpclient.execute(httpGet);
            JSONObject userInfo = JSONObject.parseObject(EntityUtils.toString(reqUserInfoResponse.getEntity()));

            log.info("微信用户信息={}",userInfo.toJSONString());

            //查询是否有该用户
            Map<String,Object> where=new HashMap<>();
            where.put("open_id",openId);
            List<ClientUser> clientUsers = clientUserMapper.selectByMap(where);
            ClientUser clientUser;
            if(clientUsers.size()==0){
                String fileName= DateUtil.getDays()+"/"+UUID.randomUUID().toString();
                HttpUtils.download(userInfo.getString("headimgurl"), properties.getWebServerPath()+"/web-imgs/"+fileName,null);
                clientUser=new ClientUser();
                clientUser.setOpenId(openId);
                clientUser.setRefreshToken(refreshToken);
                clientUser.setAccessToken(accessToken);
                clientUser.setNick(new String(userInfo.getString("nickname").getBytes("ISO-8859-1"), "UTF-8"));
                clientUser.setAvatar(fileName);
                //邀请人
                if(state!=null&&!state.equals("[state]")){
                  ClientUser invite = clientUserMapper.selectById(state);
                  if(invite!=null)clientUser.setPid(invite.getId());
                }
                clientUserMapper.insert(clientUser);
            }else{
                clientUser=clientUsers.get(0);
            }

            //生成登录token
            String token = "?token="+JwtTokenUtil.generateToken(String.valueOf(clientUser.getId()));
            log.info("重定向路径={}",wxMpProperties.getLoginSucUrl()+token);
            response.sendRedirect(wxMpProperties.getLoginSucUrl()+token);

        } catch (Exception e){
            log.error("微信授权登陆异常",e.getMessage());
            try {
                response.sendRedirect(wxMpProperties.getLoginSucUrl());
            } catch (Exception e1) {
                log.error("登录授权重定向失败！",e1);
            }
        }
    }

    /**
     *支付成功后  修改订单信息
     */
    @PostMapping(value = "/updateSubmitOrder/{orderId}")
    @ResponseBody
    public boolean updateSubmitOrder(@PathVariable("orderId") String orderId, WxPayRefundRequest wxPayRefundRequest) throws WxPayException{
        return true;
    }

    /**
     * 支付成功回调函数(此函数会被执行多次，如果支付状态已经修改为已支付，则下次再调的时候判断是否已经支付，如果已经支付了，则什么也执行)
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/paySuccessCallback")
    @ResponseBody
    public String paySuccessCallback(@RequestBody String xmlData) throws Exception{
        WxPayRefundRequest wxPayRefundRequest=new WxPayRefundRequest();
        Map<String, String> map = parseXml(xmlData);
        log.info("微信支付回调========="+map.toString());
        if("SUCCESS".equals(map.get("return_code"))){
            String orderId=map.get("out_trade_no");
            log.info("订单编号====================="+orderId);
//            try {
//                iProductService.updateSubmitOrder(orderId,wxPayRefundRequest);
//            } catch (WxPayException e) {
//                log.info("库存不足，退款至微信账户",e);
//                return "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
//            }

        }
        return "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
    }


    /**
     * 解析xml文件
     * @param xmlData
     * @return  Map<String,String>
     * @throws Exception
     */
    public Map<String,String> parseXml(String xmlData)throws Exception{
        Map<String, String> map = new HashMap<String, String>();
        Document doc = null;
        doc = DocumentHelper.parseText(new String(xmlData.getBytes("gbk"),"utf-8")); // 将字符串转为XML
        Element rootElt = doc.getRootElement(); // 获取根节点
        @SuppressWarnings("unchecked")
        List<Element> list = rootElt.elements();// 获取根节点下所有节点
        for (Element element : list) { // 遍历节点
            map.put(element.getName(), element.getText()); // 节点的name为map的key，text为map的value
        }
        return map;
    }

}
