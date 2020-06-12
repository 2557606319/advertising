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
import com.stylefeng.guns.modular.system.model.VideoIssue;
import com.stylefeng.guns.modular.system.service.IVideoIssueService;

/**
 * 文章素材发布表控制器
 *
 * @author fengshuonan
 * @Date 2020-03-25 18:49:29
 */
@Controller
@RequestMapping("/videoIssue")
public class VideoIssueController extends BaseController {

    private String PREFIX = "/system/videoIssue/";

    @Autowired
    private IVideoIssueService videoIssueService;

    /**
     * 跳转到文章素材发布表首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "videoIssue.html";
    }

    /**
     * 跳转到添加文章素材发布表
     */
    @RequestMapping("/videoIssue_add")
    public String videoIssueAdd() {
        return PREFIX + "videoIssue_add.html";
    }

    /**
     * 跳转到修改文章素材发布表
     */
    @RequestMapping("/videoIssue_update/{videoIssueId}")
    public String videoIssueUpdate(@PathVariable Integer videoIssueId, Model model) {
        VideoIssue videoIssue = videoIssueService.selectById(videoIssueId);
        model.addAttribute("item",videoIssue);
        LogObjectHolder.me().set(videoIssue);
        return PREFIX + "videoIssue_edit.html";
    }

    /**
     * 获取文章素材发布表列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return videoIssueService.selectList(null);
    }

    /**
     * 新增文章素材发布表
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(VideoIssue videoIssue) {
        videoIssueService.insert(videoIssue);
        return SUCCESS_TIP;
    }

    /**
     * 删除文章素材发布表
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer videoIssueId) {
        videoIssueService.deleteById(videoIssueId);
        return SUCCESS_TIP;
    }

    /**
     * 修改文章素材发布表
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(VideoIssue videoIssue) {
        videoIssueService.updateById(videoIssue);
        return SUCCESS_TIP;
    }

    /**
     * 文章素材发布表详情
     */
    @RequestMapping(value = "/detail/{videoIssueId}")
    @ResponseBody
    public Object detail(@PathVariable("videoIssueId") Integer videoIssueId) {
        return videoIssueService.selectById(videoIssueId);
    }
}
