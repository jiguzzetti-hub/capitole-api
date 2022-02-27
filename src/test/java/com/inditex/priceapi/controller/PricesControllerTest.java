package com.inditex.priceapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inditex.priceapi.dtos.ItemPriceByDateResponse;
import com.inditex.priceapi.services.PricesService;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;



@ExtendWith(SpringExtension.class)
public class PricesControllerTest {

    @InjectMocks
    private PricesController pricesController;

    @Mock
    private PricesService pricesService;

    @Test
    public void getItemPriceByDateSuccess() throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime datetimeFormatted = LocalDateTime.parse("2020-06-14 10:00:00", formatter);
        String body = IOUtils.toString(Objects.requireNonNull(this.getClass().getResourceAsStream("/mocks/outputSuccess.json")), StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        ItemPriceByDateResponse bodyResponse = mapper.readValue(body, ItemPriceByDateResponse.class);
        Object expected = ResponseEntity.ok(java.util.Optional.ofNullable(bodyResponse));
        Mockito.when(pricesService.getItemPriceByDatetime(1, 35455, datetimeFormatted)).thenReturn(java.util.Optional.ofNullable(bodyResponse));
        Object actual = pricesController.getItemPriceByDate(1, 35455, "2020-06-14 10:00:00");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getItemPriceByDateNotFound() throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime datetimeFormatted = LocalDateTime.parse("2020-06-14 10:00:00", formatter);
        ItemPriceByDateResponse bodyResponse = null;
        Object expected = ResponseEntity.notFound().build();
        Mockito.when(pricesService.getItemPriceByDatetime(1, 35455, datetimeFormatted)).thenReturn(java.util.Optional.ofNullable(bodyResponse));
        Object actual = pricesController.getItemPriceByDate(1, 35455, "2020-06-14 10:00:00");
        Assertions.assertEquals(expected, actual);
    }

}
