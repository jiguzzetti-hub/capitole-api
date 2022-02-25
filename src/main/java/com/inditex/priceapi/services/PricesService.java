package com.inditex.priceapi.services;

import com.inditex.priceapi.dtos.ItemPriceByDateResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.inditex.priceapi.services.ProductIdUtil.validateProductId;

public class PricesService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PricesService.class);


    public static ResponseEntity<?> getItemPriceByDatetime(String brandId, String productId, String incomingDatetime) {
        ItemPriceByDateResponse response = new ItemPriceByDateResponse();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(incomingDatetime, formatter);
        if (validateProductId(productId)){


            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body(response);
    }
}
