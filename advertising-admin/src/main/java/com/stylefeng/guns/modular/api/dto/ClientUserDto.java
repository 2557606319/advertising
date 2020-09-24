package com.stylefeng.guns.modular.api.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户dto
 *
 * @author joey
 * @since 2020-03-26
 */
@Data
public class ClientUserDto {
    private Long id;
    private String nick;
    private String avatar;
    private String industry;
    private String professional;
    private String phone;
    private String wxqr;
    private String wxAccount;
    private String email;
    private Long goldBalance;
    private BigDecimal sumCommission;
    private BigDecimal sumLower;
    private Integer vip;
    private Date vipExpire;
    private Integer agencyLevel;
    private BigDecimal earnings1;
    private BigDecimal earnings2;
    private BigDecimal earnings3;
    private Date ctime;
    private Long pid;
    private String qrImg;
    private String tgImg1;
    private String tgImg2;
}
