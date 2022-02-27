package com.inditex.priceapi.controller;

import com.inditex.priceapi.dtos.ItemPriceByDateResponse;
import com.inditex.priceapi.services.PricesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@RestController
@RequestMapping("/inditex/prices")
public class PricesController {

    private static final Logger logger = LoggerFactory.getLogger(PricesService.class);

    final PricesService pricesService;

    public PricesController(PricesService pricesService) {
        this.pricesService = pricesService;
    }

    @GetMapping(path = "/getItemPriceByDate/{brandId}/{productId}/{datetime}", produces = "application/json")
    public ResponseEntity<?> getItemPriceByDate(@PathVariable int brandId, @PathVariable int productId,
                                                @PathVariable String datetime) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime datetimeFormatted = LocalDateTime.parse(datetime, formatter);
        logger.info("Processing request for [productID={}, brandID={}, date={}]", productId, brandId, datetimeFormatted);
        Optional<ItemPriceByDateResponse> item = pricesService.getItemPriceByDatetime(brandId, productId, datetimeFormatted);
        if (item.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(item);
    }

}
