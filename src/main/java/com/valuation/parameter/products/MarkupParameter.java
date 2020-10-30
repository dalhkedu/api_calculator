package com.valuation.parameter.products;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Parametro para definição de preço de um produto")
public class MarkupParameter {

    @NotNull
    @ApiModelProperty(name = "Custo do produto", example = "10.98")
    private BigDecimal cost;
    @NotNull
    @ApiModelProperty(name = "Porcentagem de ganho desejado", example = "55.60")
    private BigDecimal percent;
}
