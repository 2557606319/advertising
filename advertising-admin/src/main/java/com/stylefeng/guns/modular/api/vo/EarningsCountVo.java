package com.stylefeng.guns.modular.api.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class EarningsCountVo {

    /**
     * 直推收益
     */
    private BigDecimal sumCommission = BigDecimal.ZERO;

    /**
     * 下级收益
     */
    private BigDecimal sumLower = BigDecimal.ZERO;

}
