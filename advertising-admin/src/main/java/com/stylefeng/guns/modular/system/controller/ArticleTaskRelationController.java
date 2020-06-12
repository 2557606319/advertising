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
import com.stylefeng.guns.modular.system.model.ArticleTaskRelation;
import com.stylefeng.guns.modular.system.service.IArticleTaskRelationService;

/**
 * 文章金币任务与发布者关联表控制器
 *
 * @author fengshuonan
 * @Date 2020-03-25 08:34:35
 */
@Controller
@RequestMapping("/articleTaskRelation")
public class ArticleTaskRelationController extends BaseController {

    private String PREFIX = "/system/articleTaskRelation/";

    @Autowired
    private IArticleTaskRelationService articleTaskRelationService;

    /**
     * 跳转到文章金币任务与发布者关联表首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "articleTaskRelation.html";
    }

    /**
     * 跳转到添加文章金币任务与发布者关联表
     */
    @RequestMapping("/articleTaskRelation_add")
    public String articleTaskRelationAdd() {
        return PREFIX + "articleTaskRelation_add.html";
    }

    /**
     * 跳转到修改文章金币任务与发布者关联表
     */
    @RequestMapping("/articleTaskRelation_update/{articleTaskRelationId}")
    public String articleTaskRelationUpdate(@PathVariable Integer articleTaskRelationId, Model model) {
        ArticleTaskRelation articleTaskRelation = articleTaskRelationService.selectById(articleTaskRelationId);
        model.addAttribute("item",articleTaskRelation);
        LogObjectHolder.me().set(articleTaskRelation);
        return PREFIX + "articleTaskRelation_edit.html";
    }

    /**
     * 获取文章金币任务与发布者关联表列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return articleTaskRelationService.selectList(null);
    }

    /**
     * 新增文章金币任务与发布者关联表
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(ArticleTaskRelation articleTaskRelation) {
        articleTaskRelationService.insert(articleTaskRelation);
        return SUCCESS_TIP;
    }

    /**
     * 删除文章金币任务与发布者关联表
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer articleTaskRelationId) {
        articleTaskRelationService.deleteById(articleTaskRelationId);
        return SUCCESS_TIP;
    }

    /**
     * 修改文章金币任务与发布者关联表
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(ArticleTaskRelation articleTaskRelation) {
        articleTaskRelationService.updateById(articleTaskRelation);
        return SUCCESS_TIP;
    }

    /**
     * 文章金币任务与发布者关联表详情
     */
    @RequestMapping(value = "/detail/{articleTaskRelationId}")
    @ResponseBody
    public Object detail(@PathVariable("articleTaskRelationId") Integer articleTaskRelationId) {
        return articleTaskRelationService.selectById(articleTaskRelationId);
    }
}
