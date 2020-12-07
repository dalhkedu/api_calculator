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
@ApiModel(value = "Material por metros")
public class MaterialPerMeter {

    @NotNull
    @ApiModelProperty(name = "Preco do Metro", example = "25.67")
    private BigDecimal price;
    @NotNull
    @ApiModelProperty(name = "Quanto em Metro", example = "0.3")
    private BigDecimal quantity;
}
