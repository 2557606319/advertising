package com.stylefeng.guns.modular.system.dao;

import com.stylefeng.guns.modular.system.model.Advertising;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 * 广告表 Mapper 接口
 * </p>
 *
 * @author joey
 * @since 2020-03-25
 */
public interface AdvertisingMapper extends BaseMapper<Advertising> {
    /**
     * 查询该用户的默认广告
     * @param uid
     * @return
     */
    List<Advertising> defaultAdv(Long uid);

    /**
     * 取消默认广告
     * @param uid
     */
    @Update("update tb_advertising set is_default = 0 where is_default = 1 and user_id = #{uid}")
    void noDefault(Long uid);
}
