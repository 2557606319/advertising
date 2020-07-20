package com.stylefeng.guns.modular.system.enums;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public enum PayStatus {

    STATUS0(0),//待支付
    STATUS1(1),//支付成功
    STATUS2(2),//支付失败
    STATUS3(3);//已退款

    int status;

    PayStatus(int status) {
        this.status=status;
    }

}
