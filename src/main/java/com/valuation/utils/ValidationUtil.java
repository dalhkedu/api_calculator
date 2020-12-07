package com.valuation.utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;
import java.util.stream.Collectors;

public class ValidationUtil {

    public static Map<String, List<?>> toList(Object... args) {
        var m = new HashMap<String, List<?>>();
        var t = Arrays.stream(args).collect(Collectors.toList());
        t.removeIf(Objects::isNull);
        for (Object arg : t) {
            var map = new HashMap<String, List<?>>();
            var list = (List) arg;
            map.put(list.get(0).getClass().getSimpleName(), list);
            m.putAll(map);
        }
        return m;
    }

    public static BigDecimal calculate(BigDecimal price, BigDecimal quantity, BigDecimal multiple) {
        return price.divide(multiple, MathContext.DECIMAL32).multiply(quantity);
    }
}
