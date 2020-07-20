package com.stylefeng.guns.core.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName SignUtil
 * @Author 26672
 * @Date 2019/11/11 17:23
 */
public class SignUtil {
    /**
     * 获取用户IP
     * @param request
     * @return
     */
    public static String getRemortIP(HttpServletRequest request) {
        if (request.getHeader("x-forwarded-for") == null) {
            return request.getRemoteAddr();
        }
        String ipListStr = request.getHeader("x-forwarded-for");
        if(!(ipListStr.indexOf(",")<0)){
            String [] list = ipListStr.split(",");
            return list[0];                                 //当服务部署使用代理，其获取到的IP地址将会是多个，取第一个
        }else {
            return ipListStr ;
        }

    }
}
