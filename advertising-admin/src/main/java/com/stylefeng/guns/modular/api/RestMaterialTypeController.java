package com.stylefeng.guns.modular.api;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.util.JwtTokenUtil;
import com.stylefeng.guns.modular.api.vo.ResultBody;
import com.stylefeng.guns.modular.system.dao.TypeMapper;
import com.stylefeng.guns.modular.system.dao.UserMapper;
import com.stylefeng.guns.modular.system.model.Type;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 素材分类controller
 * @author joey
 */
@RestController
@RequestMapping("/restApi/material")
public class RestMaterialTypeController extends BaseController {

    @Autowired
    private TypeMapper typeMapper;

    @ApiOperation("客户端用户关联的素材类型列表")
    @PostMapping("/clientRelationTypeList")
    public ResultBody clientRelationTypeList(){
        List<Type> types=new ArrayList<>();
        try{
            Long uid = JwtTokenUtil.getClientUserIdFromToken();
            types = typeMapper.findListByUid(uid);

        }catch (Exception e){
            //未登录或者token有误
            types = typeMapper.selectList(null);
        }
        return new ResultBody(types);
    }



}

