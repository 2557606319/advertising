package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 广告表
 * </p>
 *
 * @author joey
 * @since 2020-03-25
 */
@TableName("tb_advertising")
@Data
public class Advertising extends Model<Advertising> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String title;
    private String describe;
    /**
     * 封面
     */
    private String cover;
    /**
     * 是否默认 0-否 1-是
     */
    @TableField("is_default")
    private Integer isDefault;
    /**
     * 点击跳转链接
     */
    private String href;
    /**
     * 商品口令
     */
    @TableField("goods_command")
    private String goodsCommand;
    @TableField("user_id")
    private Long userId;
    /**
     * 奖励金币
     */
    private Integer award;
    /**
     * 总奖励金币
     */
    @TableField("sum_award")
    private Long sumAward;
    /**
     * 到期时间
     */
    private String expire;
    private String ctime;

    //任务状态，0-未设置  1-已设置
    @TableField(exist = false)
    private Integer taskStatus;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }


}
