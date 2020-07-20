package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author joey
 * @since 2020-03-26
 */
@TableName("tb_client_user")
@Data
public class ClientUser extends Model<ClientUser> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 昵称
     */
    private String nick;
    private String avatar;
    /**
     * 行业
     */
    private String industry;
    /**
     * 职业
     */
    private String professional;
    private String phone;
    /**
     * 微信二维码
     */
    private String wxqr;

    @TableField("wx_account")
    private String wxAccount;

    private String email;
    /**
     * 金币余额
     */
    @TableField("gold_balance")
    private Long goldBalance;
    /**
     * 总获佣金
     */
    @TableField("sum_commission")
    private BigDecimal sumCommission;
    /**
     * 总获下级佣金
     */
    @TableField("sum_lower")
    private BigDecimal sumLower;
    /**
     * 是否为vip 0-不是  1-是
     */
    private Integer vip;
    /**
     * vip到期时间
     */
    @TableField("vip_expire")
    private String vipExpire;
    /**
     * 代理等级 0-青铜 1-黄金 2-钻石
     */
    @TableField("agency_level")
    private Integer agencyLevel;
    /**
     * 一层返佣比例
     */
    private BigDecimal earnings1;
    /**
     * 二层返佣比例
     */
    private BigDecimal earnings2;
    /**
     * 三层返佣比例
     */
    private BigDecimal earnings3;
    private Date ctime;
    /**
     * 邀请人
     */
    private Long pid;

    /**  微信 依赖字段 */
    @TableField("refresh_token")
    private String refreshToken;

    @TableField("access_token")
    private String accessToken;

    @TableField("open_id")
    private String openId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
