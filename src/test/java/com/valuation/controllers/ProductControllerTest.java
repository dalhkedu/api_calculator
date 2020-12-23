package com.valuation.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.valuation.models.products.ProductPricingModel;
import com.valuation.parameter.products.MarkupParameter;
import com.valuation.presenters.products.MarkupPresenter;
import com.valuation.services.interfaces.IMaterialService;
import com.valuation.services.interfaces.IProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(controllers = ProductController.class)
@AutoConfigureMockMvc
public class ProductControllerTest {

    static String PRICING_PRODUCT = "/products/markups";

    @Autowired
    MockMvc mvc;

    @MockBean
    IProductService service;

    @MockBean
    IMaterialService materialService;

    @Test
    @DisplayName("Adicionar a marge de lucro a um produto.")
    public void profitMarginCalculate() throws Exception {

        var parameter = MarkupParameter.builder()
                .cost(BigDecimal.valueOf(100))
                .percent(BigDecimal.valueOf(55))
                .build();

        var modelNoResult = ProductPricingModel.builder()
                .cost(parameter.getCost())
                .percent(parameter.getPercent())
                .build();

        var modelWithResult = ProductPricingModel.builder()
                .cost(parameter.getCost())
                .percent(parameter.getPercent())
                .result(BigDecimal.valueOf(222.2222))
                .build();

        var presenter = MarkupPresenter.builder()
                .result(modelWithResult.getResult())
                .build();

        given(service.toModel(parameter)).willReturn(modelNoResult);
        given(service.profitMarginCalculate(modelNoResult)).willReturn(modelWithResult);
        given(service.toPresenter(modelWithResult)).willReturn(presenter);

        var json = new ObjectMapper().writeValueAsString(parameter);

        var request = MockMvcRequestBuilders
                .post(PRICING_PRODUCT)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("result").value(222.2222));
    }
}
