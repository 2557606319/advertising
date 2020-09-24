package com.stylefeng.guns.modular.api.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class RebateVo {
    private Long id;
    /**
     * 返佣用户
     */
    private Long userId;
    /**
     * 消费用户
     */
    private Long targetUserId;
    /**
     * 目标用户头像
     */
    private String targetAvatar;
    /**
     * 目标用户昵称
     */
    private String targetNick;
    /**
     * 消费金额
     */
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
     * 获得金币，1元=10金币；
     */
    private Long gold;

    private String ctime;

    /**
     * 支付订单id
     */
    private Long orderId;
}
