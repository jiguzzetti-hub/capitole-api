package com.inditex.priceapi.services;

import com.inditex.priceapi.dtos.ItemPriceByDateResponse;
import com.inditex.priceapi.entities.Prices;
import com.inditex.priceapi.repositories.PricesRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PricesService {

    private final PricesRepository pricesRepository;

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
        response.setPriceList(itemList.get(0).getPriceList());
        response.setPrice(itemList.get(0).getPrice());
        List<Prices> itemListForEndDate = pricesRepository.findEndDate(datetime, productId, brandId, itemList.get(0).getEndDate(),
                itemList.get(0).getPriority());
        if (!itemListForEndDate.isEmpty()) {
            response.setToDate(itemListForEndDate.get(0).getStartDate());
            List<Prices> itemListForStartDate = pricesRepository.findStartDate(datetime, productId, brandId,
                    itemList.get(0).getStartDate(), itemList.get(0).getPriority());
            if (!itemListForStartDate.isEmpty()) {
                response.setFromDate(itemListForStartDate.get(0).getEndDate());
                return Optional.of(response);
            }
            response.setFromDate(itemList.get(0).getStartDate());
            return Optional.of(response);
        }
        response.setFromDate(itemList.get(0).getStartDate());
        response.setToDate(itemList.get(0).getEndDate());
        return Optional.of(response);


    }
}
