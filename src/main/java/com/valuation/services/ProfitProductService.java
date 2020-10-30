package com.valuation.services;

import com.valuation.services.interfaces.IResultOfTheExerciseService;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Map;

public class ProfitProductService implements IResultOfTheExerciseService {

    @Override
    public BigDecimal grossProfit(
            BigDecimal price, Integer quantity, BigDecimal cost) {
        return price.subtract(cost).multiply(BigDecimal.valueOf(quantity));
    }

    @Override
    public BigDecimal saleTax(Map<String, BigDecimal> fees) {
        return fees.values().stream().reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public BigDecimal applyDiscount(BigDecimal price, BigDecimal discount, Integer quantity) {
        return price.divide(discount, MathContext.DECIMAL32).multiply(BigDecimal.valueOf(quantity));
    }
}