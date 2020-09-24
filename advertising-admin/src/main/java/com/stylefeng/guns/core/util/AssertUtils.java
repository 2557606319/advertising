package com.stylefeng.guns.core.util;

import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.List;

public class AssertUtils extends Assert {


    public static void lengthIsZero(List<Object> list, String message) {
        if (list.size() == 0) {
            throw new IllegalStateException(message);
        }

    }

    public static void isLtOne(BigDecimal val, String message) {
        if (val.compareTo(new BigDecimal(1)) == -1) {
            throw new IllegalStateException(message);
        }
    }

    public static void isLtOne(Integer val, String message) {
        if (val < 1) {
            throw new IllegalStateException(message);
        }
    }

    public static void isLtOne(Long val, String message) {
        if (val < 1) {
            throw new IllegalStateException(message);
        }
    }

    public static void isLt(Long val1, Long val2, String message) {
        if (val1 < val2) {
            throw new IllegalStateException(message);
        }
    }

    public static void isLt(Integer val1, Integer val2, String message) {
        if (val1 < val2) {
            throw new IllegalStateException(message);
        }
    }


}
