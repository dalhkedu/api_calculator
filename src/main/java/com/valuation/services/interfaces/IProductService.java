package com.valuation.services.interfaces;

import com.valuation.models.products.ProductPricingModel;
import com.valuation.parameter.products.MarkupParameter;
import com.valuation.presenters.products.MarkupPresenter;

public interface IProductService {

    ProductPricingModel profitMarginCalculate(ProductPricingModel model);

    ProductPricingModel toModel(MarkupParameter parameter);

    MarkupPresenter toPresenter(ProductPricingModel model);


}
