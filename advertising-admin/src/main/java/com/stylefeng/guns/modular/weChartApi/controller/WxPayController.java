package com.stylefeng.guns.modular.weChartApi.controller;

import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.modular.system.dao.ClientUserMapper;
import com.stylefeng.guns.modular.system.dao.PayOrderMapper;
import com.stylefeng.guns.modular.system.enums.AgentRebate;
import com.stylefeng.guns.modular.system.enums.PayAmountType;
import com.stylefeng.guns.modular.system.enums.PayStatus;
import com.stylefeng.guns.modular.system.model.ClientUser;
import com.stylefeng.guns.modular.system.model.PayOrder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 微信支付接口
 */
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/restApi/wx/pay")
public class WxPayController extends BaseController {

    private final WxPayService wxService;

    @Autowired
    PayOrderMapper orderMapper;

    @Autowired
    ClientUserMapper clientUserMapper;

    /**
     * 接受微信支付返回通知
     *
     * @param request
     * @param response
     */
    @RequestMapping("/notify")
    @Transactional
    public void PayResultNotify(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("微信支付返回通知函数开始---------------------");
        InputStream inStream = request.getInputStream();
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outSteam.write(buffer, 0, len);
        }
        outSteam.close();
        inStream.close();
        String result = new String(outSteam.toByteArray(), "utf-8");
        boolean isPayOk = false;
        WxPayOrderNotifyResult wxPayOrderNotifyResult = null;
        // 此处调用订单查询接口验证是否交易成功
        try {
            wxPayOrderNotifyResult = wxService.parseOrderNotifyResult(result);
            if ("SUCCESS".equals(wxPayOrderNotifyResult.getResultCode())) {
                isPayOk = true;
            }
//            log.info("解析数据:" + wxPayOrderNotifyResult.toString());
        } catch (WxPayException e) {
            e.printStackTrace();
        }
        String noticeStr = "";
        // 支付成功，商户处理后同步返回给微信参数
        PrintWriter writer = response.getWriter();
        long orderId = Long.valueOf(wxPayOrderNotifyResult.getOutTradeNo());
        PayOrder order = orderMapper.selectById(orderId);
        if (isPayOk) {
            //建议在这里处理付款完成的业务（虽然前端也可以处理后续业务，但是前端处理并不安全，例如：客户突然关闭浏览器了等情况，付款成功后续的业务将中断）
            order.setStatus(PayStatus.STATUS1.getStatus());
            PayAmountType payAmountType = PayAmountType.valueOf(order.getPayAmountType());
            if (payAmountType.getType() == 1) {
                openVipPaySuccess(order, payAmountType);
            } else {
                openAgentPaySuccess(order, payAmountType);
            }
            System.out.println("===============付款成功，业务处理完毕==============");
            // 通知微信已经收到消息，不要再给我发消息了，否则微信会8连击调用本接口
            noticeStr = setXML("SUCCESS", "OK");
            log.info("收到通知返回给微信api信息:-----------" + noticeStr);
            writer.write(noticeStr);
            writer.flush();
        } else {
            // 支付失败， 记录流水失败
            order.setStatus(PayStatus.STATUS2.getStatus());
            noticeStr = setXML("FAIL", "");
            writer.write(noticeStr);
            writer.flush();
            System.out.println("===============支付失败==============");
        }
        orderMapper.updateById(order);
    }


    /**
     * 开通vip支付成功
     *
     * @param order
     * @param payAmountType
     */
    public void openVipPaySuccess(PayOrder order, PayAmountType payAmountType) {
        ClientUser clientUser = clientUserMapper.selectById(order.getUserId());
        clientUser.setVip(1);
        Date currentDate = new Date();
        if (payAmountType.equals(PayAmountType.VIP_MONTH)) {
            DateUtils.addMonths(currentDate, 1);
        } else {
            DateUtils.addMonths(currentDate, 12);
        }
        clientUser.setVipExpire(DateFormatUtils.format(currentDate, "yyyy-MM-dd"));
        clientUserMapper.updateById(clientUser);
    }

    /**
     * 开通代理支付成功
     *
     * @param order
     * @param payAmountType
     */
    public void openAgentPaySuccess(PayOrder order, PayAmountType payAmountType) {
        AgentRebate rebate = AgentRebate.valueOf(payAmountType.name());
        ClientUser clientUser = clientUserMapper.selectById(order.getUserId());
        clientUser.setAgencyLevel(payAmountType.getLevel());
        clientUser.setEarnings1(rebate.getRate1());
        clientUser.setEarnings2(rebate.getRate2());
        clientUser.setEarnings3(rebate.getRate3());
        clientUserMapper.updateById(clientUser);
    }

//
//    /**
//     * 支付成功回调函数(此函数会被执行多次，如果支付状态已经修改为已支付，则下次再调的时候判断是否已经支付，如果已经支付了，则什么也执行)
//     * @return
//     * @throws Exception
//     */
//    @PostMapping(value = "/success/callback")
//    @ResponseBody
//    public String successCallback(@RequestBody String xmlData) throws Exception{
//        WxPayRefundRequest wxPayRefundRequest=new WxPayRefundRequest();
//        Map<String, String> map = parseXml(xmlData);
//        log.info("微信支付回调========="+map.toString());
//        if("SUCCESS".equals(map.get("return_code"))){
//            String orderId=map.get("out_trade_no");
//            log.info("订单编号====================="+orderId);
////            try {
////
////            } catch (WxPayException e) {
////                return "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
////            }
//        }
//        return "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
//    }

    /**
     * 解析xml文件
     *
     * @param xmlData
     * @return Map<String, String>
     * @throws Exception
     */
    public Map<String, String> parseXml(String xmlData) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        Document doc = null;
        doc = DocumentHelper.parseText(new String(xmlData.getBytes("gbk"), "utf-8")); // 将字符串转为XML
        Element rootElt = doc.getRootElement(); // 获取根节点
        @SuppressWarnings("unchecked")
        List<Element> list = rootElt.elements();// 获取根节点下所有节点
        for (Element element : list) { // 遍历节点
            map.put(element.getName(), element.getText()); // 节点的name为map的key，text为map的value
        }
        return map;
    }


    public static String setXML(String return_code, String return_msg) {
        return "<xml><return_code><![CDATA[" + return_code + "]]></return_code><return_msg><![CDATA[" + return_msg + "]]></return_msg></xml>";
    }


}
