package com.inditex.priceapi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ItemPriceByDateResponse {
    @JsonProperty("brand_id")
    private int brandId;
    @JsonProperty("product_id")
    private int productId;
    @JsonProperty("price_list")
    private int priceList;
    @JsonProperty("price")
    private BigDecimal price;
    @JsonProperty("from_date")
    private LocalDateTime fromDate;
    @JsonProperty("to_date")
    private LocalDateTime toDate;

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setPriceList(int priceList) {
        this.priceList = priceList;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setFromDate(LocalDateTime fromDate) {
        this.fromDate = fromDate;
    }

    public void setToDate(LocalDateTime toDate) {
        this.toDate = toDate;
    }

    public ItemPriceByDateResponse(int brandId, int productId, int priceList, BigDecimal price, LocalDateTime fromDate, LocalDateTime toDate) {
        this.brandId = brandId;
        this.productId = productId;
        this.priceList = priceList;
        this.price = price;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public ItemPriceByDateResponse() {
    }
}
