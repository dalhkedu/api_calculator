package com.valuation.services.interfaces;

import com.valuation.models.materials.MaterialProductionModel;
import com.valuation.parameter.materials.MaterialProductionParameter;
import com.valuation.presenters.materials.CostPresenter;

public interface IMaterialService {

    MaterialProductionModel toModel(MaterialProductionParameter parameter);

    MaterialProductionModel materialProduction(MaterialProductionModel model);

    CostPresenter toPresenter(MaterialProductionModel model);
}
