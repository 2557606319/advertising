package com.stylefeng.guns.modular.system.service.impl;

import com.stylefeng.guns.modular.system.model.ClientUser;
import com.stylefeng.guns.modular.system.dao.ClientUserMapper;
import com.stylefeng.guns.modular.system.service.IClientUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author joey
 * @since 2020-03-26
 */
@Service
public class ClientUserServiceImpl extends ServiceImpl<ClientUserMapper, ClientUser> implements IClientUserService {

}
