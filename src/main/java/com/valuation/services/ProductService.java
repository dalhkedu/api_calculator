package com.valuation.services;

import com.valuation.models.products.ProductPricingModel;
import com.valuation.parameter.products.MarkupParameter;
import com.valuation.presenters.products.MarkupPresenter;
import com.valuation.services.interfaces.IProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;

@Service
public class ProductService implements IProductService {

    private ModelMapper modelmapper;

    public ProductService() {
        this.modelmapper = new ModelMapper();
    }

    @Override
    public ProductPricingModel profitMarginCalculate(ProductPricingModel model) {
        model.setResult(
                model.getCost().divide(
                        BigDecimal.valueOf(100)
                                .subtract(model.getPercent())
                                .divide(BigDecimal.valueOf(100)), MathContext.DECIMAL32));
        return model;
    }

    @Override
    public ProductPricingModel toModel(MarkupParameter parameter) {
        return modelmapper.map(parameter, ProductPricingModel.class);
    }

    @Override
    public MarkupPresenter toPresenter(ProductPricingModel model) {
        return modelmapper.map(model, MarkupPresenter.class);
    }
}
