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
import com.stylefeng.guns.modular.system.model.Type;
import com.stylefeng.guns.modular.system.service.ITypeService;

/**
 * 素材类型表控制器
 *
 * @author fengshuonan
 * @Date 2020-03-25 08:36:24
 */
@Controller
@RequestMapping("/type")
public class TypeController extends BaseController {

    private String PREFIX = "/system/type/";

    @Autowired
    private ITypeService typeService;

    /**
     * 跳转到素材类型表首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "type.html";
    }

    /**
     * 跳转到添加素材类型表
     */
    @RequestMapping("/type_add")
    public String typeAdd() {
        return PREFIX + "type_add.html";
    }

    /**
     * 跳转到修改素材类型表
     */
    @RequestMapping("/type_update/{typeId}")
    public String typeUpdate(@PathVariable Integer typeId, Model model) {
        Type type = typeService.selectById(typeId);
        model.addAttribute("item",type);
        LogObjectHolder.me().set(type);
        return PREFIX + "type_edit.html";
    }

    /**
     * 获取素材类型表列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return typeService.selectList(null);
    }

    /**
     * 新增素材类型表
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Type type) {
        typeService.insert(type);
        return SUCCESS_TIP;
    }

    /**
     * 删除素材类型表
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer typeId) {
        typeService.deleteById(typeId);
        return SUCCESS_TIP;
    }

    /**
     * 修改素材类型表
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Type type) {
        typeService.updateById(type);
        return SUCCESS_TIP;
    }

    /**
     * 素材类型表详情
     */
    @RequestMapping(value = "/detail/{typeId}")
    @ResponseBody
    public Object detail(@PathVariable("typeId") Integer typeId) {
        return typeService.selectById(typeId);
    }
}
