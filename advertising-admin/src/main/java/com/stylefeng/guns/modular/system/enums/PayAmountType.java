package com.stylefeng.guns.modular.system.enums;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;

import java.math.BigDecimal;

/**
 * 预设平台付款项
 */
@Getter
public enum PayAmountType {
    VIP_MONTH(new BigDecimal(0.01), "月度vip付费金额", 1, 0, null),
    VIP_YEARS(new BigDecimal(0.02), "年度vip付费金额", 2, 1, null),
    AGENT_1(new BigDecimal(0.1), "一级代理付费金额", 2, 0, AgentRebate.AGENT_1),
    AGENT_2(new BigDecimal(0.2), "二级代理付费金额", 2, 1, AgentRebate.AGENT_2),
    AGENT_3(new BigDecimal(0.3), "三级代理付费金额", 2, 2, AgentRebate.AGENT_3);

    BigDecimal amount;
    String message;
    /**
     * 1-vip付费  2-代理付费
     */
    int type;

    /**
     * 等级
     */
    int level;

    /**
     * 代理返佣
     */
    AgentRebate agentRebate;

    PayAmountType(BigDecimal amount, String message, int type, int level, AgentRebate agentRebate) {
        this.amount = amount;
        this.message = message;
        this.type = type;
        this.level = level;
        this.agentRebate = agentRebate;
    }

    public static JSONArray toJsonObjects() {
        JSONArray jsonArray = new JSONArray();
        for (PayAmountType num : PayAmountType.values()) {
            JSONObject obj = new JSONObject();
            obj.put("amount", num.getAmount());
            obj.put("message", num.getMessage());
            obj.put("type", num.getType());
            obj.put("level", num.getLevel());
            obj.put("agentRebate", num.getAgentRebate() != null ? num.getAgentRebate().toJsonObject() : null);
            jsonArray.add(obj);
        }
        return jsonArray;
    }

    public JSONObject toJsonObject() {
        JSONObject obj = new JSONObject();
        obj.put("amount", this.getAmount());
        obj.put("message", this.getMessage());
        obj.put("type", this.getType());
        obj.put("level", this.getLevel());
        return obj;
    }
}
