package com.stylefeng.guns.modular.system.dao;

import com.stylefeng.guns.modular.system.model.ClientUser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author joey
 * @since 2020-03-26
 */
public interface ClientUserMapper extends BaseMapper<ClientUser> {

    /**
     * 更新直推返佣佣金
     *
     * @param rebateAmount
     * @param uid
     */
    @Update("update tb_client_user set sum_commission = sum_commission+#{rebateAmount} where id = #{uid}")
    void updateSumCommission(@Param("rebateAmount") BigDecimal rebateAmount, @Param("uid") Long uid);

    /**
     * 更新下级返佣佣金
     *
     * @param rebateAmount
     * @param uid
     */
    @Update("update tb_client_user set sum_lower = sum_lower+#{rebateAmount} where id = #{uid}")
    void updateSumLower(@Param("rebateAmount") BigDecimal rebateAmount, @Param("uid") Long uid);

    /**
     * 查询pid下面所有用户id列表
     *
     * @param pid
     * @return
     */
    @Select("select id from tb_client_user where pid = #{pid}")
    List<Long> selectIdByPid(Long pid);


    /**
     * 提现扣除金币
     *
     * @param uid
     * @param amount
     * @return
     */
    @Update("update tb_client_user set gold_balance = gold_balance - #{amount} where id = #{uid} and gold_balance>=#{amount}")
    int subtractGoldBalance(@Param("uid") Long uid, @Param("amount") Long amount);
}
