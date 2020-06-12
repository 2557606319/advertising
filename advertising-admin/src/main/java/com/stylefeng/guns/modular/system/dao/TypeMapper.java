package com.stylefeng.guns.modular.system.dao;

import com.stylefeng.guns.modular.system.model.Type;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 素材类型表 Mapper 接口
 * </p>
 *
 * @author joey
 * @since 2020-03-25
 */
public interface TypeMapper extends BaseMapper<Type> {

    List<Type> findListByUid(Long uid);

}
