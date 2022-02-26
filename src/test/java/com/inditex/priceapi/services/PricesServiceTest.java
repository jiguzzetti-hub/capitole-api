package com.inditex.priceapi.services;

import com.inditex.priceapi.dtos.ItemPriceByDateResponse;
import com.inditex.priceapi.entities.Prices;
import com.inditex.priceapi.repositories.PricesRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

@ExtendWith(MockitoExtension.class)
public class PricesServiceTest {

    @InjectMocks
    private PricesService pricesService;

    @Mock
    private PricesRepository pricesRepository;

    @Test
    public void getItemPriceByDatetimeReturnsEmptyList(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime datetimeFormatted = LocalDateTime.parse("2020-06-14 10:00:00", formatter);
        List<Prices> itemList = new ArrayList<>();
        Object expected = Optional.empty();
        Mockito.when(pricesRepository.findPricesByDatetime(datetimeFormatted,
                35455, 1)).thenReturn(itemList);
        Object actual= pricesService.getItemPriceByDatetime(1, 35455, datetimeFormatted);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getItemPriceByDatetimeFindEndDateReturnsNull(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime datetimeFormatted = LocalDateTime.parse("2020-06-14 10:00:00", formatter);
        LocalDateTime startDate = LocalDateTime.parse("2020-06-14 00:00:00", formatter);
        LocalDateTime endDate = LocalDateTime.parse("2020-12-31 23:59:59", formatter);
        List<Prices> itemList = new ArrayList<>();
        itemList.add(new Prices(Long.valueOf(1), 1, startDate, endDate, 1, 35455, 0, BigDecimal.valueOf(35.50), "EUR" ));
        List<Prices> itemListForEndDate = new ArrayList<>();
        ItemPriceByDateResponse response = new ItemPriceByDateResponse(1, 35455,1,BigDecimal.valueOf(35.50), startDate, endDate);
        Object expected = Optional.of(response);
        Mockito.when(pricesRepository.findPricesByDatetime(datetimeFormatted,
                35455, 1)).thenReturn(itemList);
        Mockito.when(pricesRepository.findEndDate(datetimeFormatted,
                35455, 1, endDate, 0)).thenReturn(itemListForEndDate);
        Object actual= pricesService.getItemPriceByDatetime(1, 35455, datetimeFormatted);
        assertReflectionEquals(expected, actual);
    }

    @Test
    public void getItemPriceByDatetimeFindStartDateReturnsNull(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime datetimeFormatted = LocalDateTime.parse("2020-06-14 10:00:00", formatter);
        LocalDateTime startDate = LocalDateTime.parse("2020-06-14 00:00:00", formatter);
        LocalDateTime endDate = LocalDateTime.parse("2020-12-31 23:59:59", formatter);
        List<Prices> itemList = new ArrayList<>();
        itemList.add(new Prices(Long.valueOf(1), 1, startDate, endDate, 1, 35455, 0, BigDecimal.valueOf(35.50), "EUR" ));
        List<Prices> itemListForEndDate = new ArrayList<>();
        itemListForEndDate.add(new Prices(Long.valueOf(2), 1, LocalDateTime.parse("2020-06-14 15:00:00", formatter), endDate, 2, 35455, 1, BigDecimal.valueOf(25.45), "EUR" ));
        List<Prices> itemListForStartDate = new ArrayList<>();
        ItemPriceByDateResponse response = new ItemPriceByDateResponse(1, 35455,1,BigDecimal.valueOf(35.50), startDate, LocalDateTime.parse("2020-06-14 15:00:00", formatter));
        Object expected = Optional.of(response);
        Mockito.when(pricesRepository.findPricesByDatetime(datetimeFormatted,
                35455, 1)).thenReturn(itemList);
        Mockito.when(pricesRepository.findEndDate(datetimeFormatted,
                35455, 1, endDate, 0)).thenReturn(itemListForEndDate);
        Mockito.when(pricesRepository.findStartDate(datetimeFormatted,
                35455, 1, startDate, 0)).thenReturn(itemListForStartDate);
        Object actual= pricesService.getItemPriceByDatetime(1, 35455, datetimeFormatted);
        assertReflectionEquals(expected, actual);
    }

    @Test
    public void getItemPriceByDatetimeFindPriceOk(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime datetimeFormatted = LocalDateTime.parse("2020-06-14 10:00:00", formatter);
        LocalDateTime startDate = LocalDateTime.parse("2020-06-14 00:00:00", formatter);
        LocalDateTime endDate = LocalDateTime.parse("2020-12-31 23:59:59", formatter);
        List<Prices> itemList = new ArrayList<>();
        itemList.add(new Prices(Long.valueOf(1), 1, startDate, endDate, 1, 35455, 0, BigDecimal.valueOf(35.50), "EUR" ));
        List<Prices> itemListForEndDate = new ArrayList<>();
        itemListForEndDate.add(new Prices(Long.valueOf(2), 1, LocalDateTime.parse("2020-06-14 15:00:00", formatter), endDate, 2, 35455, 1, BigDecimal.valueOf(25.45), "EUR" ));
        List<Prices> itemListForStartDate = new ArrayList<>();
        itemListForStartDate.add(new Prices(Long.valueOf(2), 1, LocalDateTime.parse("2020-06-14 15:00:00", formatter), startDate, 2, 35455, 1, BigDecimal.valueOf(25.45), "EUR" ));
        ItemPriceByDateResponse response = new ItemPriceByDateResponse(1, 35455,1,BigDecimal.valueOf(35.50), startDate, LocalDateTime.parse("2020-06-14 15:00:00", formatter));
        Object expected = Optional.of(response);
        Mockito.when(pricesRepository.findPricesByDatetime(datetimeFormatted,
                35455, 1)).thenReturn(itemList);
        Mockito.when(pricesRepository.findEndDate(datetimeFormatted,
                35455, 1, endDate, 0)).thenReturn(itemListForEndDate);
        Mockito.when(pricesRepository.findStartDate(datetimeFormatted,
                35455, 1, startDate, 0)).thenReturn(itemListForStartDate);
        Object actual= pricesService.getItemPriceByDatetime(1, 35455, datetimeFormatted);
        assertReflectionEquals(expected, actual);
    }
}
