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
import com.stylefeng.guns.modular.system.model.VideoIssueLooks;
import com.stylefeng.guns.modular.system.service.IVideoIssueLooksService;

/**
 * 发布商品浏览记录表控制器
 *
 * @author fengshuonan
 * @Date 2020-03-25 18:49:51
 */
@Controller
@RequestMapping("/videoIssueLooks")
public class VideoIssueLooksController extends BaseController {

    private String PREFIX = "/system/videoIssueLooks/";

    @Autowired
    private IVideoIssueLooksService videoIssueLooksService;

    /**
     * 跳转到发布商品浏览记录表首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "videoIssueLooks.html";
    }

    /**
     * 跳转到添加发布商品浏览记录表
     */
    @RequestMapping("/videoIssueLooks_add")
    public String videoIssueLooksAdd() {
        return PREFIX + "videoIssueLooks_add.html";
    }

    /**
     * 跳转到修改发布商品浏览记录表
     */
    @RequestMapping("/videoIssueLooks_update/{videoIssueLooksId}")
    public String videoIssueLooksUpdate(@PathVariable Integer videoIssueLooksId, Model model) {
        VideoIssueLooks videoIssueLooks = videoIssueLooksService.selectById(videoIssueLooksId);
        model.addAttribute("item",videoIssueLooks);
        LogObjectHolder.me().set(videoIssueLooks);
        return PREFIX + "videoIssueLooks_edit.html";
    }

    /**
     * 获取发布商品浏览记录表列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return videoIssueLooksService.selectList(null);
    }

    /**
     * 新增发布商品浏览记录表
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(VideoIssueLooks videoIssueLooks) {
        videoIssueLooksService.insert(videoIssueLooks);
        return SUCCESS_TIP;
    }

    /**
     * 删除发布商品浏览记录表
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer videoIssueLooksId) {
        videoIssueLooksService.deleteById(videoIssueLooksId);
        return SUCCESS_TIP;
    }

    /**
     * 修改发布商品浏览记录表
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(VideoIssueLooks videoIssueLooks) {
        videoIssueLooksService.updateById(videoIssueLooks);
        return SUCCESS_TIP;
    }

    /**
     * 发布商品浏览记录表详情
     */
    @RequestMapping(value = "/detail/{videoIssueLooksId}")
    @ResponseBody
    public Object detail(@PathVariable("videoIssueLooksId") Integer videoIssueLooksId) {
        return videoIssueLooksService.selectById(videoIssueLooksId);
    }
}
