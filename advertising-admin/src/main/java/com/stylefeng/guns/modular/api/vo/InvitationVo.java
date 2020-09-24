package com.stylefeng.guns.modular.api.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class InvitationVo {
    private Long id;
    private String nick;
    private String avatar;
    private BigDecimal sumCommission;
    private String ctime;
}
