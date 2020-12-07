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
@ApiModel(value = "Material por tempo")
public class MaterialPerTime {

    @NotNull
    @ApiModelProperty(name = "Preco da Hora", example = "25")
    private BigDecimal price;
    @NotNull
    @ApiModelProperty(name = "Quantidade em horas", example = "0.30")
    private BigDecimal quantity;
}
