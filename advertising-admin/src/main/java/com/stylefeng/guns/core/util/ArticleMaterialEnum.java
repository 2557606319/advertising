package com.stylefeng.guns.core.util;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * 各大平台文章样式适配工具类
 */
@Getter
public enum  ArticleMaterialEnum {

     //百度百家号域名
     BAIJIAHAO("baijiahao.baidu.com"),

     //微信公众号域名
     WEIXIN("mp.weixin.qq.com"),


     //今日头条域名
     TOUTIAO("toutiao.com"),
     TOUTIAO_CDN("toutiaocdn.com"),
     TOUTIAO_1("zjbyte.com"),
     TOUTIAO_2("toutiaoimg.cn"),

     //搜狐域名
     SOHU("sohu.com"),

     /**
      * 其他
      */
     AUTH_REQUEST_ERROR("账号密码错误");

     ArticleMaterialEnum( String message) {
          this.message = message;
     }

     private String message;

     /**
      * 检测这个url 是否属于匹配范围域名之内
      * @param url
      * @return
      */
     public static boolean check(String url) {
          if (url == null) {
               return false;
          } else {
               for (ArticleMaterialEnum ms : ArticleMaterialEnum.values()) {
                    if (url.indexOf(ms.getMessage())!=-1) {
                         return true;
                    }
               }
               return false;
          }
     }

}
