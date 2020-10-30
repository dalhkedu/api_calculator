package com.valuation.services;

import com.valuation.models.materials.MaterialByWeight;
import com.valuation.models.materials.MaterialPerMeter;
import com.valuation.models.materials.MaterialPerTime;
import com.valuation.models.materials.MaterialProductionModel;
import com.valuation.parameter.materials.MaterialProductionParameter;
import com.valuation.presenters.materials.CostPresenter;
import com.valuation.services.interfaces.IMaterialService;
import com.valuation.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class MaterialService implements IMaterialService {

    private ModelMapper modelmapper;

    public MaterialService() {
        this.modelmapper = new ModelMapper();
    }

    @Override
    public MaterialProductionModel toModel(MaterialProductionParameter parameter) {
        return modelmapper.map(parameter, MaterialProductionModel.class);
    }

    @Override
    public MaterialProductionModel materialProduction(MaterialProductionModel model) {
        var total = new AtomicReference<>(BigDecimal.ZERO);
        ValidationUtil.toList(
                model.getWeights(), model.getTimes(), model.getMeters()
        ).forEach((a, b) -> {
            if (a.toUpperCase().contains("METER")) {
                for (MaterialPerMeter material : ((List<MaterialPerMeter>) b)) {
                    total.set(total.get().add(ValidationUtil.calculate(
                            material.getPrice(), material.getQuantity(), BigDecimal.valueOf(100))));
                }
            } else if (a.toUpperCase().contains("WEIGHT")) {
                for (MaterialByWeight material : ((List<MaterialByWeight>) b)) {
                    total.set(total.get().add(ValidationUtil.calculate(
                            material.getPrice(), material.getQuantity(), BigDecimal.valueOf(100))));
                }
            } else if (a.toUpperCase().contains("TIME")) {
                for (MaterialPerTime material : ((List<MaterialPerTime>) b)) {
                    total.set(total.get().add(ValidationUtil.calculate(
                            material.getPrice(), material.getQuantity(), BigDecimal.valueOf(100))));
                }
            }
        });
        model.setResult(total.get());
        return model;
    }

    @Override
    public CostPresenter toPresenter(MaterialProductionModel model) {
        return modelmapper.map(model, CostPresenter.class);
    }
}
