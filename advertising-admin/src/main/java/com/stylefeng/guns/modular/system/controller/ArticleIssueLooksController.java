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
import com.stylefeng.guns.modular.system.model.ArticleIssueLooks;
import com.stylefeng.guns.modular.system.service.IArticleIssueLooksService;

/**
 * 发布的文章表浏览记录控制器
 *
 * @author fengshuonan
 * @Date 2020-03-25 08:33:12
 */
@Controller
@RequestMapping("/articleIssueLooks")
public class ArticleIssueLooksController extends BaseController {

    private String PREFIX = "/system/articleIssueLooks/";

    @Autowired
    private IArticleIssueLooksService articleIssueLooksService;

    /**
     * 跳转到发布的文章表浏览记录首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "articleIssueLooks.html";
    }

    /**
     * 跳转到添加发布的文章表浏览记录
     */
    @RequestMapping("/articleIssueLooks_add")
    public String articleIssueLooksAdd() {
        return PREFIX + "articleIssueLooks_add.html";
    }

    /**
     * 跳转到修改发布的文章表浏览记录
     */
    @RequestMapping("/articleIssueLooks_update/{articleIssueLooksId}")
    public String articleIssueLooksUpdate(@PathVariable Integer articleIssueLooksId, Model model) {
        ArticleIssueLooks articleIssueLooks = articleIssueLooksService.selectById(articleIssueLooksId);
        model.addAttribute("item",articleIssueLooks);
        LogObjectHolder.me().set(articleIssueLooks);
        return PREFIX + "articleIssueLooks_edit.html";
    }

    /**
     * 获取发布的文章表浏览记录列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return articleIssueLooksService.selectList(null);
    }

    /**
     * 新增发布的文章表浏览记录
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(ArticleIssueLooks articleIssueLooks) {
        articleIssueLooksService.insert(articleIssueLooks);
        return SUCCESS_TIP;
    }

    /**
     * 删除发布的文章表浏览记录
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer articleIssueLooksId) {
        articleIssueLooksService.deleteById(articleIssueLooksId);
        return SUCCESS_TIP;
    }

    /**
     * 修改发布的文章表浏览记录
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(ArticleIssueLooks articleIssueLooks) {
        articleIssueLooksService.updateById(articleIssueLooks);
        return SUCCESS_TIP;
    }

    /**
     * 发布的文章表浏览记录详情
     */
    @RequestMapping(value = "/detail/{articleIssueLooksId}")
    @ResponseBody
    public Object detail(@PathVariable("articleIssueLooksId") Integer articleIssueLooksId) {
        return articleIssueLooksService.selectById(articleIssueLooksId);
    }
}
