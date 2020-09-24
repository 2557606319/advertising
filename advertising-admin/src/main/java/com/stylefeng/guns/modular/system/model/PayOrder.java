package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 支付订单表
 */
@TableName("tb_pay_order")
@Data
public class PayOrder extends Model<PayOrder> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    @TableField("user_id")
    private Long userId;

    /**
     * 三方支付订单编号
     */
    @TableField("pay_num")
    private String payNum;

    private BigDecimal money;

    /**
     * 支付金额类型
     */
    @TableField("pay_amount_type")
    private String payAmountType;

    /**
     * 订单类型
     * 0-充值 1-提现
     */
    private Integer type;

    /**
     * 支付状态  0-待支付  1-支付成功 2-支付失败 3-已退款
     */
    private Integer status;

    private String ctime;

    private String describe;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }


}
