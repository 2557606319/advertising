package com.stylefeng.guns.modular.api;

import com.github.binarywang.wxpay.bean.entpay.EntPayRequest;
import com.github.binarywang.wxpay.bean.entpay.EntPayResult;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.stylefeng.guns.config.properties.WxPayProperties;
import com.stylefeng.guns.core.exception.GunsException;
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
    WxPayService wxPayService;

    @PostMapping(value = "/auth/pay/create/{type}")
    @Transactional
    public Object createPayOrder(@PathVariable("type") String type, HttpServletRequest request) {
        long userId = JwtTokenUtil.getClientUserIdFromToken();
        PayOrder order = new PayOrder();
        PayAmountType payAmountType = PayAmountType.valueOf(type);
        AssertUtils.notNull(payAmountType, GunsRestExceptionEnum.PAY_TYPE_NOT_EXIST.getMessage());
        order.setMoney(payAmountType.getAmount());
        order.setDescribe(payAmountType.getMessage());
        order.setUserId(userId);
        order.setPayAmountType(payAmountType.name());
        order.setId(Long.valueOf(String.valueOf(System.currentTimeMillis()) + String.valueOf(userId)));
        orderMapper.insert(order);
        return createOrder(request, order);
    }


    public Object createOrder(HttpServletRequest httpServletRequest, PayOrder order) {
        log.info("统一下单，并组装所需支付参数---------------------------------");
        ClientUser user = clientUserMapper.selectById(JwtTokenUtil.getClientUserIdFromToken());
        WxPayUnifiedOrderRequest request = new WxPayUnifiedOrderRequest();
        request.setBody("视你广告-" + order.getId());
        request.setDeviceInfo("WEB");//客户终端类型
        request.setNotifyUrl(wxPayProperties.getNotifyUrl());//回调通知支付结果地址(必须外网能访问的地址)
        request.setOpenid(user.getOpenId());//openid
        request.setOutTradeNo(String.valueOf(order.getId()));//商户订单号
        request.setSignType(WxPayConstants.SignType.MD5);//加密方式
        request.setSpbillCreateIp(SignUtil.getRemortIP(httpServletRequest));//用户ip
        request.setTotalFee(order.getMoney().multiply(new BigDecimal(100)).intValue());
        request.setTradeType("JSAPI");
        try {
            return wxPayService.createOrder(request);//公众号支付
        } catch (WxPayException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 提现
     *
     * @param amount
     * @param httpServletRequest
     * @return
     */
    @PostMapping(value = "/auth/pay/extract")
    @Transactional
    public Object extractCapital(Long amount, HttpServletRequest httpServletRequest) {
        long userId = JwtTokenUtil.getClientUserIdFromToken();
        ClientUser user = clientUserMapper.selectById(JwtTokenUtil.getClientUserIdFromToken());
        AssertUtils.isLt(user.getGoldBalance(), amount, GunsRestExceptionEnum.GOLD_BALANCE_LT_ERR.getMessage());
        int item = clientUserMapper.subtractGoldBalance(userId, amount);
        AssertUtils.isLt(item, 1, GunsRestExceptionEnum.GOLD_BALANCE_LT_ERR.getMessage());
        PayOrder order = new PayOrder();
        order.setMoney(new BigDecimal(amount));
        order.setDescribe("余额提现");
        order.setUserId(userId);
        order.setPayAmountType(null);
        order.setType(1);
        order.setStatus(0);
        order.setId(Long.valueOf(String.valueOf(System.currentTimeMillis()) + String.valueOf(userId)));
        orderMapper.insert(order);

        EntPayRequest payRequest = new EntPayRequest();
        payRequest.setMchId(wxPayProperties.getAppId());
        payRequest.setMchId(wxPayProperties.getMchId());
        payRequest.setPartnerTradeNo(order.getId().toString());
        payRequest.setOpenid(user.getOpenId());
        payRequest.setCheckName("NO_CHECK");
        payRequest.setAmount(amount.intValue()*10);//1金币=1毛，微信提现金额单位为 分
        payRequest.setDescription("余额提现");
        payRequest.setSpbillCreateIp("180.164.49.103");
//        try {
//            EntPayResult result = wxPayService.getEntPayService().entPay(payRequest);
//        } catch (WxPayException e) {
//            log.error("微信支付至零钱失败，userId={} ", userId, e);
//            throw new GunsException(GunsRestExceptionEnum.PAY_ERROR);
//        }
        return true;
    }
}
