package com.stylefeng.guns.modular.api.dto;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 * 发布的任务信息dto
 * @author joey
 */
@Data
public class TaskDto{

    private Long id;
    /**
     * 发起用户id
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 阅读奖励金币
     */
    private Integer award;
    /**
     * 总奖励金币
     */
    private Long sumAward;
    /**
     * 到期时间
     */
    private String expire;
    private String ctime;
    /**
     * 推广人数
     */
    private Integer generalizeCount;
    /**
     * 浏览人数
     */
    private Integer lookCount;
    /**
     * 广告id
     */
    private Long advertisingId;

}
