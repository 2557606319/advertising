package com.stylefeng.guns.modular.system.model;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author joey
 * @since 2020-03-25
 */
@TableName("tb_pay_details")
public class PayDetails extends Model<PayDetails> {

    private static final long serialVersionUID = 1L;

    /**
     * 交易编号
     */
    private String num;
    @TableField("user_id")
    private Long userId;
    /**
     * 支付编号
     */
    @TableField("pay_num")
    private String payNum;
    /**
     * 金额
     */
    private BigDecimal money;
    /**
     * 支付状态  0-待支付  1-支付成功 2-支付失败
     */
    private Integer status;
    private Date ctime;


    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPayNum() {
        return payNum;
    }

    public void setPayNum(String payNum) {
        this.payNum = payNum;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    @Override
    protected Serializable pkVal() {
        return this.num;
    }

    @Override
    public String toString() {
        return "PayDetails{" +
        "num=" + num +
        ", userId=" + userId +
        ", payNum=" + payNum +
        ", money=" + money +
        ", status=" + status +
        ", ctime=" + ctime +
        "}";
    }
}
