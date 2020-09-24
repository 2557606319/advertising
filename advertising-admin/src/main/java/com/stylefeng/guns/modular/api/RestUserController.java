package com.stylefeng.guns.modular.api;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.util.JwtTokenUtil;
import com.stylefeng.guns.modular.api.dto.ClientUserDto;
import com.stylefeng.guns.modular.api.vo.*;
import com.stylefeng.guns.modular.system.dao.ClientUserMapper;
import com.stylefeng.guns.modular.system.dao.RebateMapper;
import com.stylefeng.guns.modular.system.dao.SettingMapper;
import com.stylefeng.guns.modular.system.enums.PayAmountType;
import com.stylefeng.guns.modular.system.model.ClientUser;
import com.stylefeng.guns.modular.system.service.IClientUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * user模块controller
 *
 * @author joey
 */
@Slf4j
@RestController
@RequestMapping("/restApi/auth/user")
public class RestUserController extends BaseController {

    @Autowired
    IClientUserService clientUserService;
    @Autowired
    ClientUserMapper clientUserMapper;
    @Autowired
    RebateMapper rebateMapper;

    @Autowired
    SettingMapper settingMapper;

    @GetMapping("/info")
    public ResultBody info() {
        long uid = JwtTokenUtil.getClientUserIdFromToken();
        ClientUser clientUser = clientUserService.selectById(uid);
        ClientUserDto result = new ClientUserDto();
        BeanUtils.copyProperties(clientUser, result);
        return new ResultBody(result);
    }

    @PostMapping("/editInfo")
    public ResultBody editInfo(ClientUser user) {
        long uid = JwtTokenUtil.getClientUserIdFromToken();
        user.setId(uid);
        boolean flag = clientUserService.updateById(user);
        return new ResultBody(flag);
    }

    @GetMapping("/levelInfo")
    public ResultBody levelInfo() {
        long uid = JwtTokenUtil.getClientUserIdFromToken();
        ClientUser clientUser = clientUserService.selectById(uid);
        Map<String, Object> result = new HashMap<>();
        result.put("level", clientUser.getAgencyLevel());
        result.put("levelInfo", PayAmountType.toJsonObjects());
        return new ResultBody(result);
    }

    @GetMapping("/earnings")
    public ResultBody meEarnings() {
        long uid = JwtTokenUtil.getClientUserIdFromToken();
        ClientUser clientUser = clientUserService.selectById(uid);
        MyEarningsVo myEarningsVo = new MyEarningsVo();
        myEarningsVo.setNick(clientUser.getNick());
        myEarningsVo.setAvatar(clientUser.getAvatar());
        myEarningsVo.setLevel(clientUser.getAgencyLevel());
        myEarningsVo.setTotalRevenue(clientUser.getSumCommission().add(clientUser.getSumLower()));

        myEarningsVo.setUpgrade(PayAmountType.valueOf("AGENT_" + myEarningsVo.getLevel()).getUpgrade());
        myEarningsVo.setLower1Ratio(clientUser.getEarnings1());
        myEarningsVo.setLower2Ratio(clientUser.getEarnings2());
        myEarningsVo.setLower3Ratio(clientUser.getEarnings3());

        return new ResultBody(myEarningsVo);
    }

    @GetMapping("/earnings/count")
    public ResultBody earningsCount(String date) {
        long uid = JwtTokenUtil.getClientUserIdFromToken();
        EarningsCountVo result = new EarningsCountVo();
        if (StringUtils.isBlank(date)) {
            ClientUser clientUser = clientUserService.selectById(uid);
            result.setSumCommission(clientUser.getSumCommission());
            result.setSumLower(clientUser.getSumLower());
            return new ResultBody(result);
        }
        //直推用户id列表
        List<Long> level1Ids = clientUserMapper.selectIdByPid(uid);
        result.setSumCommission(rebateMapper.countCommissionByDate(date, uid,level1Ids));
        if (level1Ids.size() > 0)
            result.setSumLower(rebateMapper.countLowerCommissionByDate(date, uid, level1Ids));
        return new ResultBody(result);
    }

    @GetMapping("/earnings/details")
    public ResultBody earningsDetails(String date) {
        long uid = JwtTokenUtil.getClientUserIdFromToken();
        List<RebateVo> rebateVos = rebateMapper.rebateListByPid(date, uid);
        return new ResultBody(rebateVos);
    }

    @GetMapping("/invitation/list")
    public ResultBody myInvitationList(){
        long uid = JwtTokenUtil.getClientUserIdFromToken();
        Map<String,Object> where=new HashMap<>();
        where.put("pid",uid);
        List<ClientUser> clientUsers = clientUserMapper.selectByMap(where);
        List<InvitationVo> result=new ArrayList<>();
        clientUsers.forEach((v)->{
            InvitationVo vo = new InvitationVo();
            BeanUtils.copyProperties(v,vo);
            result.add(vo);
        });
        return new ResultBody(result);
    }


}

