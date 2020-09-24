package com.stylefeng.guns.modular.system.dao;

import com.stylefeng.guns.modular.api.vo.RebateVo;
import com.stylefeng.guns.modular.system.model.Rebate;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 返佣明细表 Mapper 接口
 * </p>
 *
 * @author joey
 * @since 2020-03-25
 */
public interface RebateMapper extends BaseMapper<Rebate> {

    /**
     * 统计用户对应日期的直推收益
     *
     * @param date 按日查询=yyyy-mm-dd, 按月查=yyyy-mm
     * @return
     */
    BigDecimal countCommissionByDate(@Param("date") String date, @Param("userId") Long userId, @Param("underlingIds") List<Long> underlingIds);

    /**
     * 统计用户对应日期的下级收益
     *
     * @param date
     * @param userId
     * @param underlingIds
     * @return
     */
    BigDecimal countLowerCommissionByDate(@Param("date") String date, @Param("userId") Long userId, @Param("underlingIds") List<Long> underlingIds);

    /**
     * 获取用户所得佣金列表
     * @param date
     * @param userId
     * @return
     */
    List<RebateVo> rebateListByPid(@Param("date") String date, @Param("userId") Long userId);
}
