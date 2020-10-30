package com.valuation.controllers;

import com.valuation.parameter.products.MarkupParameter;
import com.valuation.presenters.products.MarkupPresenter;
import com.valuation.services.interfaces.IProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@Api(tags = {"PRODUCTS"})
@ApiResponses({
        @ApiResponse(code = 401, message = "Unauthorized", response = ResponseEntity.class),
        @ApiResponse(code = 403, message = "Forbidden", response = ResponseEntity.class),
        @ApiResponse(code = 404, message = "Not Found", response = ResponseEntity.class)
})
@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private IProductService service;

    @PostMapping(value = "/markups")
    public MarkupPresenter profitMarginCalculate(@RequestBody @Valid MarkupParameter parameter) {
        return this.service.toPresenter(
                this.service.profitMarginCalculate(
                        this.service.toModel(parameter)));
    }
}
