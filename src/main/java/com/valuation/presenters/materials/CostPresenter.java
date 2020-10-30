package com.valuation.presenters.materials;

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
@ApiModel(value = "Apresentacao do Custo dos materiais")
public class CostPresenter {

    @ApiModelProperty(name = "Resultado do custo do produto com suas materia prima", example = "25.98")
    private BigDecimal result;
}
