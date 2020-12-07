package com.valuation.services;

import com.valuation.models.materials.MaterialByWeight;
import com.valuation.models.materials.MaterialPerMeter;
import com.valuation.models.materials.MaterialPerTime;
import com.valuation.models.materials.MaterialProductionModel;
import com.valuation.parameter.materials.MaterialProductionParameter;
import com.valuation.services.interfaces.IMaterialService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class MaterialServiceTest {

    BigDecimal WEIGHT = BigDecimal.valueOf(200);
    BigDecimal PRICE_WEIGHT = BigDecimal.valueOf(5.6);
    BigDecimal METER = BigDecimal.valueOf(10);
    BigDecimal PRICE_METER = BigDecimal.valueOf(12.5);
    BigDecimal TIME = BigDecimal.valueOf(60);
    BigDecimal PRICE_TIME = BigDecimal.valueOf(25);
    BigDecimal RESULT = BigDecimal.valueOf(28.7);

    private IMaterialService service;

    @MockBean
    private ModelMapper mapper;

    @BeforeEach
    public void setUp() {
        this.service = new MaterialService();
    }

    @Test
    @DisplayName("Deve realizar o calculo do custo de proução de um produto / servico com Metros, Peso")
    public void MaterialProductionTest() {
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

        var modelNoResult = MaterialProductionModel.builder()
                .meters(meters)
                .weights(weights)
                .times(times)
                .build();

        var result = service.materialProduction(modelNoResult);

        assertThat(result.getMeters().get(0).getQuantity()).isEqualTo(METER);
        assertThat(result.getMeters().get(0).getPrice()).isEqualTo(PRICE_METER);
        assertThat(result.getWeights().get(0).getQuantity()).isEqualTo(WEIGHT);
        assertThat(result.getWeights().get(0).getPrice()).isEqualTo(PRICE_WEIGHT);
        assertThat(result.getTimes().get(0).getQuantity()).isEqualTo(TIME);
        assertThat(result.getTimes().get(0).getPrice()).isEqualTo(PRICE_TIME);
        assertThat(result.getResult().doubleValue()).isEqualTo(RESULT.doubleValue());
    }

    @Test
    @DisplayName("Deve passar parametros para modelo")
    public void toModelTest() {
        var meters = Arrays.asList(
                MaterialPerMeter.builder()
                        .quantity(METER)
                        .price(PRICE_METER)
                        .build());

        var weights = Arrays.asList(
                MaterialByWeight.builder()
                        .quantity(WEIGHT)
                        .price(PRICE_WEIGHT)
                        .build());

        var times = Arrays.asList(
                MaterialPerTime.builder()
                        .quantity(TIME)
                        .price(PRICE_TIME)
                        .build());

        var parameter = MaterialProductionParameter.builder()
                .meters(meters)
                .times(times)
                .weights(weights)
                .build();

        var model = MaterialProductionModel.builder()
                .meters(parameter.getMeters())
                .times(parameter.getTimes())
                .weights(parameter.getWeights())
                .build();

        when(mapper.map(any(), any())).thenReturn(model);

        var toModel = service.toModel(parameter);

        assertThat(toModel.getResult()).isNull();
    }
}
