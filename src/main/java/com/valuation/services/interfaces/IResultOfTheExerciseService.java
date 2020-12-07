package com.valuation.services.interfaces;

import java.math.BigDecimal;
import java.util.Map;

public interface IResultOfTheExerciseService {

    BigDecimal grossProfit(BigDecimal price, Integer quantity, BigDecimal cost);

    BigDecimal saleTax(Map<String, BigDecimal> fees);

    BigDecimal applyDiscount(BigDecimal price, BigDecimal discount, Integer quantity);
}
