package com.stylefeng.guns.modular.system.enums;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public enum PayAmountType {
    VIP_MONTH(new BigDecimal(0.01), "月度vip付费金额",1,0),
    VIP_YEARS(new BigDecimal(0.02), "年度vip付费金额",2,1),
    AGENT_0(new BigDecimal(0.1), "一级代理付费金额",2,0),
    AGENT_1(new BigDecimal(0.2), "二级代理付费金额",2,1),
    AGENT_2(new BigDecimal(0.3), "三级代理付费金额",2,2);

    BigDecimal amount;
    String message;
    /**
     * 1-vip付费  2-代理付费
     */
    int type;

    int level;//对应等级

    PayAmountType(BigDecimal amount, String message, int type,int level) {
        this.amount = amount;
        this.message = message;
        this.type=type;
        this.level=level;
    }

}
