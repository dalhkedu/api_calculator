package com.valuation.models.products;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductPricingModel {

    private BigDecimal cost;
    private BigDecimal percent;
    private BigDecimal result;
}
