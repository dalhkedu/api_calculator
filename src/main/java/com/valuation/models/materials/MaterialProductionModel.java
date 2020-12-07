package com.valuation.models.materials;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MaterialProductionModel {

    private List<MaterialByWeight> weights;
    private List<MaterialPerMeter> meters;
    private List<MaterialPerTime> times;
    private BigDecimal result;
}
