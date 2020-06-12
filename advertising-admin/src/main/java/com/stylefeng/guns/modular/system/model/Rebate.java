package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 返佣明细表
 * </p>
 *
 * @author joey
 * @since 2020-03-25
 */
@TableName("tb_rebate")
public class Rebate extends Model<Rebate> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 收益用户
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 目标用户
     */
    @TableField("target_user_id")
    private Long targetUserId;
    /**
     * 消费金额
     */
    @TableField("pro_money")
    private BigDecimal proMoney;
    /**
     * 佣金
     */
    private BigDecimal commission;
    /**
     * 返佣比例
     */
    private BigDecimal ratio;
    /**
     * 获得金币
     */
    private Long gold;
    private Date ctime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTargetUserId() {
        return targetUserId;
    }

    public void setTargetUserId(Long targetUserId) {
        this.targetUserId = targetUserId;
    }

    public BigDecimal getProMoney() {
        return proMoney;
    }

    public void setProMoney(BigDecimal proMoney) {
        this.proMoney = proMoney;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public BigDecimal getRatio() {
        return ratio;
    }

    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
    }

    public Long getGold() {
        return gold;
    }

    public void setGold(Long gold) {
        this.gold = gold;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Rebate{" +
        "id=" + id +
        ", userId=" + userId +
        ", targetUserId=" + targetUserId +
        ", proMoney=" + proMoney +
        ", commission=" + commission +
        ", ratio=" + ratio +
        ", gold=" + gold +
        ", ctime=" + ctime +
        "}";
    }
}
