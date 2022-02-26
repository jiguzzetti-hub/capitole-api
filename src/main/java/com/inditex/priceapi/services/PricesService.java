package com.inditex.priceapi.services;

import com.inditex.priceapi.dtos.ItemPriceByDateResponse;
import com.inditex.priceapi.entities.Prices;
import com.inditex.priceapi.repositories.PricesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PricesService {

    private final PricesRepository pricesRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(PricesService.class);

    public PricesService(PricesRepository pricesRepository) {
        this.pricesRepository = pricesRepository;
    }


    public Optional<ItemPriceByDateResponse> getItemPriceByDatetime(int brandId, int productId, LocalDateTime datetime) {
        ItemPriceByDateResponse response = new ItemPriceByDateResponse();
        List<Prices> itemList = pricesRepository.findPricesByDatetime(datetime, productId, brandId);
        if (itemList.isEmpty()) {
            return Optional.empty();
        }
        response.setBrandId(brandId);
        response.setProductId(productId);
        response.setPriceList(itemList.get(0).getPrice_list());
        response.setPrice(itemList.get(0).getPrice());
        List<Prices> itemListForEndDate = pricesRepository.findEndDate(datetime, productId, brandId, itemList.get(0).getEnd_date(),
                itemList.get(0).getPriority());
        if (!itemListForEndDate.isEmpty()) {
            response.setToDate(itemListForEndDate.get(0).getStart_date());
            List<Prices> itemListForStartDate = pricesRepository.findStartDate(datetime, productId, brandId,
                    itemList.get(0).getStart_date(), itemList.get(0).getPriority());
            if (!itemListForStartDate.isEmpty()) {
                response.setFromDate(itemListForStartDate.get(0).getEnd_date());
                return Optional.of(response);
            }
            response.setFromDate(itemList.get(0).getStart_date());
            return Optional.of(response);
        }
        response.setFromDate(itemList.get(0).getStart_date());
        response.setToDate(itemList.get(0).getEnd_date());
        return Optional.of(response);


    }
}
