package com.valuation.models.materials;

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
@ApiModel(value = "Material com medida de peso, KG")
public class MaterialByWeight {

    @NotNull
    @ApiModelProperty(name = "Valor do Kilo", example = "25.67")
    private BigDecimal price;
    @NotNull
    @ApiModelProperty(name = "Quantidade em Kilo", example = "0.2")
    private BigDecimal quantity;
}
