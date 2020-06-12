package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 金币提现
 * </p>
 *
 * @author joey
 * @since 2020-03-25
 */
@TableName("tb_gold_extract")
public class GoldExtract extends Model<GoldExtract> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("user_id")
    private Long userId;
    /**
     * 金币数
     */
    @TableField("gold_num")
    private Long goldNum;
    /**
     * 提现类型 0-支付宝 1-微信
     */
    private Integer type;
    /**
     * 提现账户
     */
    private String account;
    private Date ctime;
    /**
     * 审核状态 0-待审核 1-审核成功  2-审核失败
     */
    private Integer status;
    /**
     * 审核时间
     */
    @TableField("auth_time")
    private Date authTime;
    /**
     * 备注
     */
    private String remark;


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

    public Long getGoldNum() {
        return goldNum;
    }

    public void setGoldNum(Long goldNum) {
        this.goldNum = goldNum;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getAuthTime() {
        return authTime;
    }

    public void setAuthTime(Date authTime) {
        this.authTime = authTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "GoldExtract{" +
        "id=" + id +
        ", userId=" + userId +
        ", goldNum=" + goldNum +
        ", type=" + type +
        ", account=" + account +
        ", ctime=" + ctime +
        ", status=" + status +
        ", authTime=" + authTime +
        ", remark=" + remark +
        "}";
    }
}
