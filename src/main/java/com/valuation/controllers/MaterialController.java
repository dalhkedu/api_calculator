package com.valuation.controllers;

import com.valuation.parameter.materials.MaterialProductionParameter;
import com.valuation.presenters.materials.CostPresenter;
import com.valuation.services.interfaces.IMaterialService;
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

@Api(tags = {"MATERIALS"})
@ApiResponses({
        @ApiResponse(code = 401, message = "Unauthorized", response = ResponseEntity.class),
        @ApiResponse(code = 403, message = "Forbidden", response = ResponseEntity.class),
        @ApiResponse(code = 404, message = "Not Found", response = ResponseEntity.class)
})
@RestController
@RequestMapping(value = "/materials")
public class MaterialController {

    @Autowired
    private IMaterialService service;

    @PostMapping(value = "/costs")
    public CostPresenter materialCostProduct(@RequestBody @Valid MaterialProductionParameter parameter) {
        return this.service.toPresenter(
                this.service.materialProduction(
                        this.service.toModel(parameter)));
    }
}
