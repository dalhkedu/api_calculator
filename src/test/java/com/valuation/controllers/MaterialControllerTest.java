package com.valuation.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.valuation.models.materials.MaterialByWeight;
import com.valuation.models.materials.MaterialPerMeter;
import com.valuation.models.materials.MaterialPerTime;
import com.valuation.models.materials.MaterialProductionModel;
import com.valuation.parameter.materials.MaterialProductionParameter;
import com.valuation.presenters.materials.CostPresenter;
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
import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest
@AutoConfigureMockMvc
public class MaterialControllerTest {

    static String MATERIAL_PRODUCT = "/materials/costs";
    BigDecimal WEIGHT = BigDecimal.valueOf(200);
    BigDecimal PRICE_WEIGHT = BigDecimal.valueOf(5.6);
    BigDecimal METER = BigDecimal.valueOf(10);
    BigDecimal PRICE_METER = BigDecimal.valueOf(12.5);
    BigDecimal TIME = BigDecimal.valueOf(60);
    BigDecimal PRICE_TIME = BigDecimal.valueOf(25);
    BigDecimal RESULT = BigDecimal.valueOf(28.7);

    @Autowired
    MockMvc mvc;

    @MockBean
    IMaterialService service;

    @MockBean
    private IProductService productService;

    @Test
    @DisplayName("Inserir materiais para gerar custo de um produto.")
    public void materialCostProduct() throws Exception {

        var meters = Arrays.asList(MaterialPerMeter.builder()
                .quantity(METER)
                .price(PRICE_METER)
                .build(), MaterialPerMeter.builder()
                .quantity(METER)
                .price(PRICE_METER)
                .build());

        var weights = Arrays.asList(MaterialByWeight.builder()
                .quantity(WEIGHT)
                .price(PRICE_WEIGHT)
                .build());

        var times = Arrays.asList(MaterialPerTime.builder()
                .quantity(TIME)
                .price(PRICE_TIME)
                .build());

        var parameter = MaterialProductionParameter.builder()
                .meters(meters)
                .weights(weights)
                .times(times)
                .build();

        var modelNoResult = MaterialProductionModel.builder()
                .meters(meters)
                .weights(weights)
                .times(times)
                .build();

        var modelWithResult = MaterialProductionModel.builder()
                .meters(meters)
                .weights(weights)
                .times(times)
                .result(RESULT)
                .build();

        var presenter = CostPresenter.builder()
                .result(RESULT)
                .build();

        given(service.toModel(parameter)).willReturn(modelNoResult);
        given(service.materialProduction(modelNoResult)).willReturn(modelWithResult);
        given(service.toPresenter(modelWithResult)).willReturn(presenter);

        var json = new ObjectMapper().writeValueAsString(parameter);

        var request = MockMvcRequestBuilders
                .post(MATERIAL_PRODUCT)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("result").value(RESULT));
    }


}
