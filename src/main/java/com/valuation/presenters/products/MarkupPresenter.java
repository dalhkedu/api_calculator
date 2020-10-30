package com.valuation.presenters.products;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Apresentacao do valor final do produto com a procentagem de ganho")
public class MarkupPresenter {

    @ApiModelProperty(name = "Resultado margem de lucro", example = "34.89")
    private BigDecimal result;
}
