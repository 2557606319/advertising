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
import com.stylefeng.guns.modular.system.model.ArticleTask;
import com.stylefeng.guns.modular.system.service.IArticleTaskService;

/**
 * 文章金币任务表控制器
 *
 * @author fengshuonan
 * @Date 2020-03-25 08:33:36
 */
@Controller
@RequestMapping("/articleTask")
public class ArticleTaskController extends BaseController {

    private String PREFIX = "/system/articleTask/";

    @Autowired
    private IArticleTaskService articleTaskService;

    /**
     * 跳转到文章金币任务表首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "articleTask.html";
    }

    /**
     * 跳转到添加文章金币任务表
     */
    @RequestMapping("/articleTask_add")
    public String articleTaskAdd() {
        return PREFIX + "articleTask_add.html";
    }

    /**
     * 跳转到修改文章金币任务表
     */
    @RequestMapping("/articleTask_update/{articleTaskId}")
    public String articleTaskUpdate(@PathVariable Integer articleTaskId, Model model) {
        ArticleTask articleTask = articleTaskService.selectById(articleTaskId);
        model.addAttribute("item",articleTask);
        LogObjectHolder.me().set(articleTask);
        return PREFIX + "articleTask_edit.html";
    }

    /**
     * 获取文章金币任务表列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return articleTaskService.selectList(null);
    }

    /**
     * 新增文章金币任务表
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(ArticleTask articleTask) {
        articleTaskService.insert(articleTask);
        return SUCCESS_TIP;
    }

    /**
     * 删除文章金币任务表
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer articleTaskId) {
        articleTaskService.deleteById(articleTaskId);
        return SUCCESS_TIP;
    }

    /**
     * 修改文章金币任务表
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(ArticleTask articleTask) {
        articleTaskService.updateById(articleTask);
        return SUCCESS_TIP;
    }

    /**
     * 文章金币任务表详情
     */
    @RequestMapping(value = "/detail/{articleTaskId}")
    @ResponseBody
    public Object detail(@PathVariable("articleTaskId") Integer articleTaskId) {
        return articleTaskService.selectById(articleTaskId);
    }
}
