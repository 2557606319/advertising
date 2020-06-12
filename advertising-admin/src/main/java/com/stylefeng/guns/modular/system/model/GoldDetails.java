package com.stylefeng.guns.modular.system.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 金币明细表
 * </p>
 *
 * @author joey
 * @since 2020-03-25
 */
@TableName("tb_gold_details")
public class GoldDetails extends Model<GoldDetails> {

    private static final long serialVersionUID = 1L;

    private Long id;
    @TableField("user_id")
    private Long userId;
    /**
     * 金币数
     */
    @TableField("gold_num")
    private Integer goldNum;
    /**
     * 收入支出  0-收入 1-支出
     */
    private Integer direction;
    private String remark;
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

    public Integer getGoldNum() {
        return goldNum;
    }

    public void setGoldNum(Integer goldNum) {
        this.goldNum = goldNum;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "GoldDetails{" +
        "id=" + id +
        ", userId=" + userId +
        ", goldNum=" + goldNum +
        ", direction=" + direction +
        ", remark=" + remark +
        ", ctime=" + ctime +
        "}";
    }
}
