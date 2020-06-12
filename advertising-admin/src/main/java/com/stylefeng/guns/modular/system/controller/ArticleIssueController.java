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
import com.stylefeng.guns.modular.system.model.ArticleIssue;
import com.stylefeng.guns.modular.system.service.IArticleIssueService;

/**
 * 文章发布表控制器
 *
 * @author fengshuonan
 * @Date 2020-03-25 08:32:43
 */
@Controller
@RequestMapping("/articleIssue")
public class ArticleIssueController extends BaseController {

    private String PREFIX = "/system/articleIssue/";

    @Autowired
    private IArticleIssueService articleIssueService;

    /**
     * 跳转到文章发布表首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "articleIssue.html";
    }

    /**
     * 跳转到添加文章发布表
     */
    @RequestMapping("/articleIssue_add")
    public String articleIssueAdd() {
        return PREFIX + "articleIssue_add.html";
    }

    /**
     * 跳转到修改文章发布表
     */
    @RequestMapping("/articleIssue_update/{articleIssueId}")
    public String articleIssueUpdate(@PathVariable Integer articleIssueId, Model model) {
        ArticleIssue articleIssue = articleIssueService.selectById(articleIssueId);
        model.addAttribute("item",articleIssue);
        LogObjectHolder.me().set(articleIssue);
        return PREFIX + "articleIssue_edit.html";
    }

    /**
     * 获取文章发布表列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return articleIssueService.selectList(null);
    }

    /**
     * 新增文章发布表
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(ArticleIssue articleIssue) {
        articleIssueService.insert(articleIssue);
        return SUCCESS_TIP;
    }

    /**
     * 删除文章发布表
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer articleIssueId) {
        articleIssueService.deleteById(articleIssueId);
        return SUCCESS_TIP;
    }

    /**
     * 修改文章发布表
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(ArticleIssue articleIssue) {
        articleIssueService.updateById(articleIssue);
        return SUCCESS_TIP;
    }

    /**
     * 文章发布表详情
     */
    @RequestMapping(value = "/detail/{articleIssueId}")
    @ResponseBody
    public Object detail(@PathVariable("articleIssueId") Integer articleIssueId) {
        return articleIssueService.selectById(articleIssueId);
    }
}
