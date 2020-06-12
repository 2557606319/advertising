package com.stylefeng.guns.core.util;

import lombok.Getter;

/**
 * 各大短视频平台适配枚举类
 */
@Getter
public enum VideoMaterialEnum {

     DOUYIN("douyin.com"),
     KUAISHOU("kuaishou.com");


     VideoMaterialEnum(String message) {
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
               for (VideoMaterialEnum ms : VideoMaterialEnum.values()) {
                    if (url.indexOf(ms.getMessage())!=-1) {
                         return true;
                    }
               }
               return false;
          }
     }

}
