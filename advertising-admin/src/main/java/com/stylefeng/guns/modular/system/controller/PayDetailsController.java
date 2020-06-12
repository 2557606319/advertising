package com.stylefeng.guns.modular.system.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.PayDetails;
import com.stylefeng.guns.modular.system.service.IPayDetailsService;

/**
 * 支付明细表控制器
 *
 * @author fengshuonan
 * @Date 2020-03-25 08:35:43
 */
@Controller
@RequestMapping("/payDetails")
public class PayDetailsController extends BaseController {

    private String PREFIX = "/system/payDetails/";

    @Autowired
    private IPayDetailsService payDetailsService;

    /**
     * 跳转到支付明细表首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "payDetails.html";
    }

    /**
     * 跳转到添加支付明细表
     */
    @RequestMapping("/payDetails_add")
    public String payDetailsAdd() {
        return PREFIX + "payDetails_add.html";
    }

    /**
     * 跳转到修改支付明细表
     */
    @RequestMapping("/payDetails_update/{payDetailsId}")
    public String payDetailsUpdate(@PathVariable Integer payDetailsId, Model model) {
        PayDetails payDetails = payDetailsService.selectById(payDetailsId);
        model.addAttribute("item",payDetails);
        LogObjectHolder.me().set(payDetails);
        return PREFIX + "payDetails_edit.html";
    }

    /**
     * 获取支付明细表列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return payDetailsService.selectList(null);
    }

    /**
     * 新增支付明细表
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(PayDetails payDetails) {
        payDetailsService.insert(payDetails);
        return SUCCESS_TIP;
    }

    /**
     * 删除支付明细表
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer payDetailsId) {
        payDetailsService.deleteById(payDetailsId);
        return SUCCESS_TIP;
    }

    /**
     * 修改支付明细表
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(PayDetails payDetails) {
        payDetailsService.updateById(payDetails);
        return SUCCESS_TIP;
    }

    /**
     * 支付明细表详情
     */
    @RequestMapping(value = "/detail/{payDetailsId}")
    @ResponseBody
    public Object detail(@PathVariable("payDetailsId") Integer payDetailsId) {
        return payDetailsService.selectById(payDetailsId);
    }
}
