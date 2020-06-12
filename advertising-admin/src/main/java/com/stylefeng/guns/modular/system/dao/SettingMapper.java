package com.stylefeng.guns.modular.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.modular.system.model.Advertising;
import com.stylefeng.guns.modular.system.model.Setting;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author joey
 * @since 2020-03-25
 */
public interface SettingMapper extends BaseMapper<Setting> {

    @Select("select * from tb_setting where pkey = #{pkey}")
    List<Setting> listByPkey(String pkey);

}
