package com.stylefeng.guns.core.common.constant;

/**
 * jwt相关配置
 *
 * @author fengshuonan
 * @date 2017-08-23 9:23
 */
public interface JwtConstants {

    String AUTH_HEADER = "Authorization";

    String SECRET = "HBSF##EWABO^^AOI++BAWENAOE==";//token加密秘钥

    Long EXPIRATION = 604800L;//token失效时间

    String AUTH_PATH = "/restApi/auth";

}
