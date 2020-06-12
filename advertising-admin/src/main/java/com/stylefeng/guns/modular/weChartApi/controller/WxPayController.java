package com.stylefeng.guns.modular.weChartApi.controller;

import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.stylefeng.guns.core.base.controller.BaseController;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

/**
 * 微信支付接口
 */
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/restApi/wx/pay")
public class WxPayController extends BaseController {

    private final WxPayService wxService;

    /**
     * 接受微信支付返回通知
     * @param request
     * @param response
     */
    @RequestMapping("/PayResultNotify")
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
        boolean isPayOk =false;
        WxPayOrderNotifyResult wxPayOrderNotifyResult =null;

        // 此处调用订单查询接口验证是否交易成功
        try {
            wxPayOrderNotifyResult = wxService.parseOrderNotifyResult(result);
            if("SUCCESS".equals(wxPayOrderNotifyResult.getResultCode())){
                isPayOk=true;
            }
            log.info("解析数据:"+wxPayOrderNotifyResult.toString());
        } catch (WxPayException e) {
            e.printStackTrace();
        }

        String noticeStr="";
        // 支付成功，商户处理后同步返回给微信参数
        PrintWriter writer = response.getWriter();
        if (isPayOk) {
        //建议在这里处理付款完成的业务（虽然前端也可以处理后续业务，但是前端处理并不安全，例如：客户突然关闭浏览器了等情况，付款成功后续的业务将中断）
            System.out.println("===============付款成功，业务处理完毕==============");
            // 通知微信已经收到消息，不要再给我发消息了，否则微信会8连击调用本接口
            noticeStr = setXML("SUCCESS", "OK");
            log.info("收到通知返回给微信api信息:-----------"+noticeStr);
            writer.write(noticeStr);
            writer.flush();

        } else {

            // 支付失败， 记录流水失败
            noticeStr = setXML("FAIL", "");
            writer.write(noticeStr);
            writer.flush();
            System.out.println("===============支付失败==============");
        }



    }

    public static String setXML(String return_code, String return_msg) {
        return "<xml><return_code><![CDATA[" + return_code + "]]></return_code><return_msg><![CDATA[" + return_msg + "]]></return_msg></xml>";
    }



}
