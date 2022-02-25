package com.inditex.priceapi.controller;

import com.inditex.priceapi.dtos.ItemPriceByDateResponse;
import com.inditex.priceapi.services.PricesService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/inditex/prices")
public class PricesController {

    @GetMapping(path = "/getItemPriceByDate{brandId}/{productId}/{datetime}", produces = "application/json")
    @ApiOperation(value = "Obtener precio de producto para determinada fecha.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ItemPriceByDateResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error"),
            @ApiResponse(code = 400, message = "Error en petición", response = ItemPriceByDateResponse.class) })
    @Transactional
    public ResponseEntity<?> newUserRegistration(@PathVariable String brandId, @PathVariable String productId,
                                                 @PathVariable String datetime) {

        return PricesService.getItemPriceByDatetime(brandId, productId, datetime);
    }

}
