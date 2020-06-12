package com.stylefeng.guns.modular.system.service.impl;

import com.stylefeng.guns.modular.system.model.Type;
import com.stylefeng.guns.modular.system.dao.TypeMapper;
import com.stylefeng.guns.modular.system.service.ITypeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 素材类型表 服务实现类
 * </p>
 *
 * @author joey
 * @since 2020-03-25
 */
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements ITypeService {

}
