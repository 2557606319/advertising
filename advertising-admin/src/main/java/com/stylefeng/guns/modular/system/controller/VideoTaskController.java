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
import com.stylefeng.guns.modular.system.model.VideoTask;
import com.stylefeng.guns.modular.system.service.IVideoTaskService;

/**
 * 视频金币任务表控制器
 *
 * @author fengshuonan
 * @Date 2020-03-25 18:50:15
 */
@Controller
@RequestMapping("/videoTask")
public class VideoTaskController extends BaseController {

    private String PREFIX = "/system/videoTask/";

    @Autowired
    private IVideoTaskService videoTaskService;

    /**
     * 跳转到视频金币任务表首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "videoTask.html";
    }

    /**
     * 跳转到添加视频金币任务表
     */
    @RequestMapping("/videoTask_add")
    public String videoTaskAdd() {
        return PREFIX + "videoTask_add.html";
    }

    /**
     * 跳转到修改视频金币任务表
     */
    @RequestMapping("/videoTask_update/{videoTaskId}")
    public String videoTaskUpdate(@PathVariable Integer videoTaskId, Model model) {
        VideoTask videoTask = videoTaskService.selectById(videoTaskId);
        model.addAttribute("item",videoTask);
        LogObjectHolder.me().set(videoTask);
        return PREFIX + "videoTask_edit.html";
    }

    /**
     * 获取视频金币任务表列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return videoTaskService.selectList(null);
    }

    /**
     * 新增视频金币任务表
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(VideoTask videoTask) {
        videoTaskService.insert(videoTask);
        return SUCCESS_TIP;
    }

    /**
     * 删除视频金币任务表
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer videoTaskId) {
        videoTaskService.deleteById(videoTaskId);
        return SUCCESS_TIP;
    }

    /**
     * 修改视频金币任务表
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(VideoTask videoTask) {
        videoTaskService.updateById(videoTask);
        return SUCCESS_TIP;
    }

    /**
     * 视频金币任务表详情
     */
    @RequestMapping(value = "/detail/{videoTaskId}")
    @ResponseBody
    public Object detail(@PathVariable("videoTaskId") Integer videoTaskId) {
        return videoTaskService.selectById(videoTaskId);
    }
}
