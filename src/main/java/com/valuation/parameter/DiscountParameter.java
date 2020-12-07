package com.valuation.parameter;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
public class DiscountParameter {

    @NotNull
    private BigDecimal discount;
    @NotNull
    private Integer quantity;
}
