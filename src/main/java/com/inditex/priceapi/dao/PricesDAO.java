package com.inditex.priceapi.dao;

import com.inditex.priceapi.entities.Prices;
import com.inditex.priceapi.repositories.PricesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PricesDAO {
    @Autowired
    private PricesRepository pricesRepository;

    public List<Prices> getPricesListByDatetime (String brandId, String productId, LocalDateTime datetime) {
    List<Prices> pricesList = new ArrayList<>();
    pricesList = pricesRepository.findPricesByDatetime(datetime, productId, brandId);
    return pricesList;
    }
}
