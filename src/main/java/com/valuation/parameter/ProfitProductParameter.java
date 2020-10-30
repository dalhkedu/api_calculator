package com.valuation.parameter;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class ProfitProductParameter {

    @NotNull
    private BigDecimal price;
    @NotNull
    private Integer quantity;
    @NotNull
    private BigDecimal cost;
    private List<FeesParameter> fees;
    private DiscountParameter discount;
}
