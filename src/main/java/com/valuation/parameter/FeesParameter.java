package com.valuation.parameter;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
public class FeesParameter {

    @NotBlank
    private String name;
    @NotNull
    private BigDecimal tax;
}
