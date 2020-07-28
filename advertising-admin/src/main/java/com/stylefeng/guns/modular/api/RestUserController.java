package com.stylefeng.guns.modular.api;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.exception.GunsException;
import com.stylefeng.guns.core.exception.GunsRestExceptionEnum;
import com.stylefeng.guns.core.util.AssertUtils;
import com.stylefeng.guns.core.util.JwtTokenUtil;
import com.stylefeng.guns.modular.api.dto.ClientUserDto;
import com.stylefeng.guns.modular.api.vo.LevelInfoVo;
import com.stylefeng.guns.modular.api.vo.ResultBody;
import com.stylefeng.guns.modular.system.dao.*;
import com.stylefeng.guns.modular.system.enums.PayAmountType;
import com.stylefeng.guns.modular.system.enums.Settings;
import com.stylefeng.guns.modular.system.model.*;
import com.stylefeng.guns.modular.system.service.IAdvertisingService;
import com.stylefeng.guns.modular.system.service.IClientUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * user模块controller
 * @author joey
 */
@Slf4j
@RestController
@RequestMapping("/restApi/auth/user")
public class RestUserController extends BaseController {

    @Autowired
    IClientUserService clientUserService;

    @Autowired
    SettingMapper settingMapper;

    @GetMapping("/info")
    public ResultBody info(){
        long uid = JwtTokenUtil.getClientUserIdFromToken();
        ClientUser clientUser = clientUserService.selectById(uid);
        ClientUserDto result=new ClientUserDto();
        BeanUtils.copyProperties(clientUser,result);
        return new ResultBody(result);
    }

    @PostMapping("/editInfo")
    public ResultBody editInfo(ClientUser user){
        long uid = JwtTokenUtil.getClientUserIdFromToken();
        user.setId(uid);
        boolean flag = clientUserService.updateById(user);
        return new ResultBody(flag);
    }

    @GetMapping("/levelInfo")
    public ResultBody levelInfo(){
        long uid = JwtTokenUtil.getClientUserIdFromToken();
        ClientUser clientUser = clientUserService.selectById(uid);
        Map<String,Object> result=new HashMap<>();
        result.put("level",clientUser.getAgencyLevel());
        result.put("levelInfo", PayAmountType.toJsonObjects());
        return new ResultBody(result);
    }


}

