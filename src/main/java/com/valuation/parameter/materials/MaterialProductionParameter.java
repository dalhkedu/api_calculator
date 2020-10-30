package com.valuation.parameter.materials;

import com.valuation.models.materials.MaterialByWeight;
import com.valuation.models.materials.MaterialPerMeter;
import com.valuation.models.materials.MaterialPerTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Parametro para calcular custo de produto com obras primas")
public class MaterialProductionParameter {

    @ApiModelProperty(name = "Medida de Peso")
    private List<MaterialByWeight> weights;
    @ApiModelProperty(name = "Medida de Metro")
    private List<MaterialPerMeter> meters;
    @ApiModelProperty(name = "Medida de Tempo")
    private List<MaterialPerTime> times;
}
