package com.stylefeng.guns.modular.system.enums;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;

import java.math.BigDecimal;

/**
 * 代理层级返佣
 */
@Getter
public enum AgentRebate {

    AGENT_1(new BigDecimal(0.4),new BigDecimal(0),new BigDecimal(0)),
    AGENT_2(new BigDecimal(0.6),new BigDecimal(0),new BigDecimal(0)),
    AGENT_3(new BigDecimal(0.8),new BigDecimal(0.1),new BigDecimal(0.05));

    //代理层级返佣
    BigDecimal rate1;//直属下级返佣比例
    BigDecimal rate2;//二级返佣比例
    BigDecimal rate3;//三级返佣比例

    AgentRebate(BigDecimal rate1,BigDecimal rate2,BigDecimal rate3){
        this.rate1=rate1;
        this.rate2=rate2;
        this.rate3=rate3;
    }

    public JSONObject toJsonObject(){
        JSONObject obj = new JSONObject();
        obj.put("rate1", this.getRate1());
        obj.put("rate2", this.getRate2());
        obj.put("rate3", this.getRate3());
        return obj;
    }
}
