package com.inditex.priceapi.controller;

import com.inditex.priceapi.dtos.ItemPriceByDateResponse;
import com.inditex.priceapi.entities.Prices;
import com.inditex.priceapi.services.PricesService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@RestController
@RequestMapping("/inditex/prices")
public class PricesController {

    @Autowired
    PricesService pricesService;

    @GetMapping(path = "/getItemPriceByDate/{brandId}/{productId}/{datetime}", produces = "application/json")
    public ResponseEntity<?> getItemPriceByDate(@PathVariable int brandId, @PathVariable int productId,
                                                @PathVariable String datetime) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime datetimeFormatted = LocalDateTime.parse(datetime, formatter);
        Optional<ItemPriceByDateResponse> item = pricesService.getItemPriceByDatetime(brandId, productId, datetimeFormatted);
        if (item.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(item);
    }

}
