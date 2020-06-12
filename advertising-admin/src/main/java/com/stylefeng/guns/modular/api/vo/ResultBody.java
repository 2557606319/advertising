package com.stylefeng.guns.modular.api.vo;

import lombok.Data;

@Data
public class ResultBody {
    private int code;
    private Object message;
    private Long times=System.currentTimeMillis();

    public ResultBody(Object message) {
        this.code=200;
        this.message = message;
    }
}
