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
import com.stylefeng.guns.modular.system.model.GoldExtract;
import com.stylefeng.guns.modular.system.service.IGoldExtractService;

/**
 * 金币明提现申请表控制器
 *
 * @author fengshuonan
 * @Date 2020-03-25 08:35:24
 */
@Controller
@RequestMapping("/goldExtract")
public class GoldExtractController extends BaseController {

    private String PREFIX = "/system/goldExtract/";

    @Autowired
    private IGoldExtractService goldExtractService;

    /**
     * 跳转到金币明提现申请表首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "goldExtract.html";
    }

    /**
     * 跳转到添加金币明提现申请表
     */
    @RequestMapping("/goldExtract_add")
    public String goldExtractAdd() {
        return PREFIX + "goldExtract_add.html";
    }

    /**
     * 跳转到修改金币明提现申请表
     */
    @RequestMapping("/goldExtract_update/{goldExtractId}")
    public String goldExtractUpdate(@PathVariable Integer goldExtractId, Model model) {
        GoldExtract goldExtract = goldExtractService.selectById(goldExtractId);
        model.addAttribute("item",goldExtract);
        LogObjectHolder.me().set(goldExtract);
        return PREFIX + "goldExtract_edit.html";
    }

    /**
     * 获取金币明提现申请表列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return goldExtractService.selectList(null);
    }

    /**
     * 新增金币明提现申请表
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(GoldExtract goldExtract) {
        goldExtractService.insert(goldExtract);
        return SUCCESS_TIP;
    }

    /**
     * 删除金币明提现申请表
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer goldExtractId) {
        goldExtractService.deleteById(goldExtractId);
        return SUCCESS_TIP;
    }

    /**
     * 修改金币明提现申请表
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(GoldExtract goldExtract) {
        goldExtractService.updateById(goldExtract);
        return SUCCESS_TIP;
    }

    /**
     * 金币明提现申请表详情
     */
    @RequestMapping(value = "/detail/{goldExtractId}")
    @ResponseBody
    public Object detail(@PathVariable("goldExtractId") Integer goldExtractId) {
        return goldExtractService.selectById(goldExtractId);
    }
}
