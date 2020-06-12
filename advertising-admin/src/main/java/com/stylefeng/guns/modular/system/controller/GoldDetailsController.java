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
import com.stylefeng.guns.modular.system.model.GoldDetails;
import com.stylefeng.guns.modular.system.service.IGoldDetailsService;

/**
 * 金币明细表控制器
 *
 * @author fengshuonan
 * @Date 2020-03-25 08:35:08
 */
@Controller
@RequestMapping("/goldDetails")
public class GoldDetailsController extends BaseController {

    private String PREFIX = "/system/goldDetails/";

    @Autowired
    private IGoldDetailsService goldDetailsService;

    /**
     * 跳转到金币明细表首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "goldDetails.html";
    }

    /**
     * 跳转到添加金币明细表
     */
    @RequestMapping("/goldDetails_add")
    public String goldDetailsAdd() {
        return PREFIX + "goldDetails_add.html";
    }

    /**
     * 跳转到修改金币明细表
     */
    @RequestMapping("/goldDetails_update/{goldDetailsId}")
    public String goldDetailsUpdate(@PathVariable Integer goldDetailsId, Model model) {
        GoldDetails goldDetails = goldDetailsService.selectById(goldDetailsId);
        model.addAttribute("item",goldDetails);
        LogObjectHolder.me().set(goldDetails);
        return PREFIX + "goldDetails_edit.html";
    }

    /**
     * 获取金币明细表列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return goldDetailsService.selectList(null);
    }

    /**
     * 新增金币明细表
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(GoldDetails goldDetails) {
        goldDetailsService.insert(goldDetails);
        return SUCCESS_TIP;
    }

    /**
     * 删除金币明细表
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer goldDetailsId) {
        goldDetailsService.deleteById(goldDetailsId);
        return SUCCESS_TIP;
    }

    /**
     * 修改金币明细表
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(GoldDetails goldDetails) {
        goldDetailsService.updateById(goldDetails);
        return SUCCESS_TIP;
    }

    /**
     * 金币明细表详情
     */
    @RequestMapping(value = "/detail/{goldDetailsId}")
    @ResponseBody
    public Object detail(@PathVariable("goldDetailsId") Integer goldDetailsId) {
        return goldDetailsService.selectById(goldDetailsId);
    }
}
