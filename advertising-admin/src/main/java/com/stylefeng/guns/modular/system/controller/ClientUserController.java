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
import com.stylefeng.guns.modular.system.model.ClientUser;
import com.stylefeng.guns.modular.system.service.IClientUserService;

/**
 * 客户端用户控制器
 *
 * @author fengshuonan
 * @Date 2020-03-26 00:17:03
 */
@Controller
@RequestMapping("/clientUser")
public class ClientUserController extends BaseController {

    private String PREFIX = "/system/clientUser/";

    @Autowired
    private IClientUserService clientUserService;

    /**
     * 跳转到客户端用户首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "clientUser.html";
    }

    /**
     * 跳转到添加客户端用户
     */
    @RequestMapping("/clientUser_add")
    public String clientUserAdd() {
        return PREFIX + "clientUser_add.html";
    }

    /**
     * 跳转到修改客户端用户
     */
    @RequestMapping("/clientUser_update/{clientUserId}")
    public String clientUserUpdate(@PathVariable Integer clientUserId, Model model) {
        ClientUser clientUser = clientUserService.selectById(clientUserId);
        model.addAttribute("item",clientUser);
        LogObjectHolder.me().set(clientUser);
        return PREFIX + "clientUser_edit.html";
    }

    /**
     * 获取客户端用户列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return clientUserService.selectList(null);
    }

    /**
     * 新增客户端用户
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(ClientUser clientUser) {
        clientUserService.insert(clientUser);
        return SUCCESS_TIP;
    }

    /**
     * 删除客户端用户
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer clientUserId) {
        clientUserService.deleteById(clientUserId);
        return SUCCESS_TIP;
    }

    /**
     * 修改客户端用户
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(ClientUser clientUser) {
        clientUserService.updateById(clientUser);
        return SUCCESS_TIP;
    }

    /**
     * 客户端用户详情
     */
    @RequestMapping(value = "/detail/{clientUserId}")
    @ResponseBody
    public Object detail(@PathVariable("clientUserId") Integer clientUserId) {
        return clientUserService.selectById(clientUserId);
    }
}
