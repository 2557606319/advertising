package com.stylefeng.guns.modular.api;

import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.stylefeng.guns.config.properties.WxPayProperties;
import com.stylefeng.guns.core.exception.GunsRestExceptionEnum;
import com.stylefeng.guns.core.util.AssertUtils;
import com.stylefeng.guns.core.util.JwtTokenUtil;
import com.stylefeng.guns.core.util.SignUtil;
import com.stylefeng.guns.modular.system.dao.ClientUserMapper;
import com.stylefeng.guns.modular.system.dao.PayOrderMapper;
import com.stylefeng.guns.modular.system.enums.PayAmountType;
import com.stylefeng.guns.modular.system.model.ClientUser;
import com.stylefeng.guns.modular.system.model.PayOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/restApi")
public class RestPayController {

    @Autowired
    PayOrderMapper orderMapper;

    @Autowired
    ClientUserMapper clientUserMapper;

    @Autowired
    WxPayProperties wxPayProperties;

    @Autowired
    WxPayService wxService;

    @PostMapping(value = "/auth/pay/create/{type}")
    @Transactional
    public Object createPayOrder(@PathVariable("type") String type,HttpServletRequest request) {
        long userId = JwtTokenUtil.getClientUserIdFromToken();
        PayOrder order = new PayOrder();
        PayAmountType payAmountType = PayAmountType.valueOf(type);
        AssertUtils.notNull(payAmountType, GunsRestExceptionEnum.PAY_TYPE_NOT_EXIST.getMessage());
        order.setMoney(payAmountType.getAmount());
        order.setDescribe(payAmountType.getMessage());
        order.setUserId(userId);
        order.setPayAmountType(payAmountType.name());
        order.setId(Long.valueOf(String.valueOf(System.currentTimeMillis())+String.valueOf(userId)));
        orderMapper.insert(order);
        return createOrder(request,order);
    }


    public Object createOrder(HttpServletRequest httpServletRequest, PayOrder order) {
        log.info("统一下单，并组装所需支付参数---------------------------------");
        ClientUser user = clientUserMapper.selectById(JwtTokenUtil.getClientUserIdFromToken());
        WxPayUnifiedOrderRequest request = new WxPayUnifiedOrderRequest();
        request.setBody("视你广告-" + order.getId());
        request.setDeviceInfo("WEB");//客户终端类型
        request.setNonceStr(UUID.randomUUID().toString().substring(0, 20));//随机字符串
        request.setNotifyUrl(wxPayProperties.getNotifyUrl());//回调通知支付结果地址(必须外网能访问的地址)
        request.setOpenid(user.getOpenId());//openid
        request.setOutTradeNo(String.valueOf(order.getId()));//商户订单号
        request.setSignType(WxPayConstants.SignType.MD5);//加密方式
        request.setSpbillCreateIp(SignUtil.getRemortIP(httpServletRequest));//用户ip
        request.setTotalFee(order.getMoney().multiply(new BigDecimal(100)).intValue());
        request.setTradeType("JSAPI");
        try {
            return wxService.createOrder(request);//公众号支付
        } catch (WxPayException e) {
            e.printStackTrace();
        }
        return null;
    }
}
