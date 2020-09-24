package com.stylefeng.guns.modular.api.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MyEarningsVo {
    /**
     * 昵称
     */
    private String nick;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 当前代理等级
     */
    private Integer level;
    /**
     * 升至高一级需要的收益
     */
    private Integer upgrade;
    /**
     * 总收益
     */
    private BigDecimal totalRevenue;
    /**
     * 直推收益返佣比例
     */
    private BigDecimal lower1Ratio;
    /**
     * 下级2层收益返佣比例
     */
    private BigDecimal lower2Ratio;
    /**
     * 下级3层收益返佣比例
     */
    private BigDecimal lower3Ratio;

}
