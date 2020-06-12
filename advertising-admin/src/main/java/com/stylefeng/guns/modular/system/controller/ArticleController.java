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
import com.stylefeng.guns.modular.system.model.Article;
import com.stylefeng.guns.modular.system.service.IArticleService;

/**
 * 文章素材控制器
 *
 * @author fengshuonan
 * @Date 2020-03-25 08:27:17
 */
@Controller
@RequestMapping("/article")
public class ArticleController extends BaseController {

    private String PREFIX = "/system/article/";

    @Autowired
    private IArticleService articleService;

    /**
     * 跳转到文章素材首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "article.html";
    }

    /**
     * 跳转到添加文章素材
     */
    @RequestMapping("/article_add")
    public String articleAdd() {
        return PREFIX + "article_add.html";
    }

    /**
     * 跳转到修改文章素材
     */
    @RequestMapping("/article_update/{articleId}")
    public String articleUpdate(@PathVariable Integer articleId, Model model) {
        Article article = articleService.selectById(articleId);
        model.addAttribute("item",article);
        LogObjectHolder.me().set(article);
        return PREFIX + "article_edit.html";
    }

    /**
     * 获取文章素材列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return articleService.selectList(null);
    }

    /**
     * 新增文章素材
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Article article) {
        articleService.insert(article);
        return SUCCESS_TIP;
    }

    /**
     * 删除文章素材
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer articleId) {
        articleService.deleteById(articleId);
        return SUCCESS_TIP;
    }

    /**
     * 修改文章素材
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Article article) {
        articleService.updateById(article);
        return SUCCESS_TIP;
    }

    /**
     * 文章素材详情
     */
    @RequestMapping(value = "/detail/{articleId}")
    @ResponseBody
    public Object detail(@PathVariable("articleId") Integer articleId) {
        return articleService.selectById(articleId);
    }
}
