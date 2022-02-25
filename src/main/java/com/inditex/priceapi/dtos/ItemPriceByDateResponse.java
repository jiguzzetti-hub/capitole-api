package com.inditex.priceapi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class ItemPriceByDateResponse {
    @JsonProperty("brand_id")
    private int brandId;
    @JsonProperty("product_id")
    private String productId;
    @JsonProperty("price_list")
    private int priceList;
    private float price;
    @JsonProperty("from_date")
    private String fromDate;
    @JsonProperty("to_date")
    private String toDate;
}
