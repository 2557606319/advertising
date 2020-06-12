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
import com.stylefeng.guns.modular.system.model.Rebate;
import com.stylefeng.guns.modular.system.service.IRebateService;

/**
 * 返佣明细表控制器
 *
 * @author fengshuonan
 * @Date 2020-03-25 08:35:57
 */
@Controller
@RequestMapping("/rebate")
public class RebateController extends BaseController {

    private String PREFIX = "/system/rebate/";

    @Autowired
    private IRebateService rebateService;

    /**
     * 跳转到返佣明细表首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "rebate.html";
    }

    /**
     * 跳转到添加返佣明细表
     */
    @RequestMapping("/rebate_add")
    public String rebateAdd() {
        return PREFIX + "rebate_add.html";
    }

    /**
     * 跳转到修改返佣明细表
     */
    @RequestMapping("/rebate_update/{rebateId}")
    public String rebateUpdate(@PathVariable Integer rebateId, Model model) {
        Rebate rebate = rebateService.selectById(rebateId);
        model.addAttribute("item",rebate);
        LogObjectHolder.me().set(rebate);
        return PREFIX + "rebate_edit.html";
    }

    /**
     * 获取返佣明细表列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return rebateService.selectList(null);
    }

    /**
     * 新增返佣明细表
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Rebate rebate) {
        rebateService.insert(rebate);
        return SUCCESS_TIP;
    }

    /**
     * 删除返佣明细表
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer rebateId) {
        rebateService.deleteById(rebateId);
        return SUCCESS_TIP;
    }

    /**
     * 修改返佣明细表
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Rebate rebate) {
        rebateService.updateById(rebate);
        return SUCCESS_TIP;
    }

    /**
     * 返佣明细表详情
     */
    @RequestMapping(value = "/detail/{rebateId}")
    @ResponseBody
    public Object detail(@PathVariable("rebateId") Integer rebateId) {
        return rebateService.selectById(rebateId);
    }
}
