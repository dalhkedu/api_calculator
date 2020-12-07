package com.valuation.services;

import com.valuation.models.products.ProductPricingModel;
import com.valuation.parameter.products.MarkupParameter;
import com.valuation.presenters.products.MarkupPresenter;
import com.valuation.services.interfaces.IProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class ProductServiceTest {

    BigDecimal PERCENT = BigDecimal.valueOf(35);
    BigDecimal COST = BigDecimal.valueOf(100);
    BigDecimal RESULT = BigDecimal.valueOf(153.8462);

    private IProductService service;

    @MockBean
    ModelMapper mapper;

    @BeforeEach
    public void setUp() {
        this.service = new ProductService();
    }

    private MarkupParameter createParameter() {
        return MarkupParameter.builder().percent(PERCENT).cost(COST).build();
    }

    private ProductPricingModel createModelNoResult() {
        return ProductPricingModel.builder().percent(PERCENT).cost(COST).build();
    }

    private ProductPricingModel createModelWithResult() {
        return ProductPricingModel.builder().percent(PERCENT).cost(COST).result(RESULT).build();
    }

    private MarkupPresenter createPresenter() {
        return MarkupPresenter.builder().result(RESULT).build();
    }

    @Test
    @DisplayName("Deve realizar o calculo da margem de lucro desejada para um produto")
    public void profitMarginCalculatorTest() {
        var model = createModelNoResult();

        var modelWithResult = service.profitMarginCalculate(model);

        assertThat(modelWithResult.getCost()).isEqualTo(COST);
        assertThat(modelWithResult.getPercent()).isEqualTo(PERCENT);
        assertThat(modelWithResult.getResult()).isEqualTo(RESULT);
    }

    @Test
    @DisplayName("Deve passar modelo para apresentação")
    public void toPresenterTest() {
        var model = createModelWithResult();
        var presenter = createPresenter();
        when(mapper.map(any(), any())).thenReturn(presenter);

        var toPresenter = service.toPresenter(model);

        assertThat(toPresenter.getResult()).isEqualTo(RESULT);
    }

    @Test
    @DisplayName("Deve passar parametros para modelo")
    public void toModelTest() {
        var parameter = createParameter();
        var model = createModelNoResult();
        when(mapper.map(any(), any())).thenReturn(model);

        var toModel = service.toModel(parameter);

        assertThat(toModel.getResult()).isNull();
        assertThat(toModel.getCost()).isEqualTo(COST);
        assertThat(toModel.getPercent()).isEqualTo(PERCENT);
    }
}
