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
import com.stylefeng.guns.modular.system.model.Advertising;
import com.stylefeng.guns.modular.system.service.IAdvertisingService;

/**
 * 广告信息表控制器
 *
 * @author fengshuonan
 * @Date 2020-03-25 08:32:07
 */
@Controller
@RequestMapping("/advertising")
public class AdvertisingController extends BaseController {

    private String PREFIX = "/system/advertising/";

    @Autowired
    private IAdvertisingService advertisingService;

    /**
     * 跳转到广告信息表首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "advertising.html";
    }

    /**
     * 跳转到添加广告信息表
     */
    @RequestMapping("/advertising_add")
    public String advertisingAdd() {
        return PREFIX + "advertising_add.html";
    }

    /**
     * 跳转到修改广告信息表
     */
    @RequestMapping("/advertising_update/{advertisingId}")
    public String advertisingUpdate(@PathVariable Integer advertisingId, Model model) {
        Advertising advertising = advertisingService.selectById(advertisingId);
        model.addAttribute("item",advertising);
        LogObjectHolder.me().set(advertising);
        return PREFIX + "advertising_edit.html";
    }

    /**
     * 获取广告信息表列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return advertisingService.selectList(null);
    }

    /**
     * 新增广告信息表
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Advertising advertising) {
        advertisingService.insert(advertising);
        return SUCCESS_TIP;
    }

    /**
     * 删除广告信息表
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer advertisingId) {
        advertisingService.deleteById(advertisingId);
        return SUCCESS_TIP;
    }

    /**
     * 修改广告信息表
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Advertising advertising) {
        advertisingService.updateById(advertising);
        return SUCCESS_TIP;
    }

    /**
     * 广告信息表详情
     */
    @RequestMapping(value = "/detail/{advertisingId}")
    @ResponseBody
    public Object detail(@PathVariable("advertisingId") Integer advertisingId) {
        return advertisingService.selectById(advertisingId);
    }
}
