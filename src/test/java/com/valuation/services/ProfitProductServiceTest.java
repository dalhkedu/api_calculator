package com.valuation.services;

import com.valuation.parameter.DiscountParameter;
import com.valuation.parameter.FeesParameter;
import com.valuation.parameter.ProfitProductParameter;
import com.valuation.services.interfaces.IResultOfTheExerciseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class ProfitProductServiceTest {

    private IResultOfTheExerciseService service;

    @BeforeEach
    public void setUp() {
        this.service = new ProfitProductService();
    }

    @Test
    @DisplayName("Calcular lucro bruto da venda de um produto / serviço sem taxas")
    public void grossProfitTest() {

        var fees = Arrays.asList(
                FeesParameter.builder().name("ICMS").tax(BigDecimal.valueOf(18)).build());

        var discount = DiscountParameter.builder()
                .discount(BigDecimal.valueOf(10)).quantity(2).build();

        var parameter = ProfitProductParameter.builder()
                .price(BigDecimal.valueOf(100.00))
                .quantity(10)
                .fees(fees)
                .discount(discount)
                .cost(BigDecimal.valueOf(40.00))
                .build();

        var grossProfit = service.grossProfit(
                parameter.getPrice(),
                parameter.getQuantity(),
                parameter.getCost());

        assertThat(grossProfit).isEqualTo(BigDecimal.valueOf(600.0));
    }

    @Test
    @DisplayName("Taxas inclusas na venda do produto / Servico")
    public void saleTaxTest() {
        var fees = Arrays.asList(
                FeesParameter.builder().name("ICMS").tax(BigDecimal.valueOf(18)).build(),
                FeesParameter.builder().name("IPI").tax(BigDecimal.valueOf(1.5)).build());

        var discount = DiscountParameter.builder()
                .discount(BigDecimal.valueOf(10)).quantity(2).build();

        var parameter = ProfitProductParameter.builder()
                .price(BigDecimal.valueOf(100.00))
                .quantity(10)
                .fees(fees)
                .discount(discount)
                .cost(BigDecimal.valueOf(40.00))
                .build();

        var maps = new HashMap<String, BigDecimal>();
        parameter.getFees().forEach(fee -> maps.put(fee.getName(), fee.getTax()));
        var saleTax = service.saleTax(maps);

        assertThat(saleTax).isEqualTo(BigDecimal.valueOf(19.5));
    }

    @Test
    @DisplayName("Aplicar desconto em produto / Servico")
    public void applyDiscountTest() {

        var fees = Arrays.asList(
                FeesParameter.builder().name("ICMS").tax(BigDecimal.valueOf(18)).build());

        var discount = DiscountParameter.builder()
                .discount(BigDecimal.valueOf(10))
                .quantity(5)
                .build();

        var parameter = ProfitProductParameter.builder()
                .price(BigDecimal.valueOf(100.00))
                .quantity(10)
                .fees(fees)
                .discount(discount)
                .cost(BigDecimal.valueOf(40.00))
                .build();


        var saleTax = service.applyDiscount(
                parameter.getPrice(),
                parameter.getDiscount().getDiscount(),
                parameter.getDiscount().getQuantity());

        assertThat(saleTax).isEqualTo(BigDecimal.valueOf(50.0));
    }
}
