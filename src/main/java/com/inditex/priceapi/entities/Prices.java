package com.inditex.priceapi.entities;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@SequenceGenerator(name = "SEQ_PRICES", allocationSize = 1, sequenceName = "SEQ_PRICES")
@Table(name= "PRICES")
public class Prices {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PRICES")
    private Long id;

    @JoinColumn(nullable = false, foreignKey = @ForeignKey(name = "FK_BRAND_PRICES"))
    private int brand_id;
    private LocalDateTime start_date;
    private LocalDateTime end_date;
    private int price_list;
    private String product_id;
    private int priority;
    private float price;
    private String curr;

    public Long getId() {
        return id;
    }

    public int getBrand_id() {
        return brand_id;
    }

    public LocalDateTime getStart_date() {
        return start_date;
    }

    public LocalDateTime getEnd_date() {
        return end_date;
    }

    public int getPrice_list() {
        return price_list;
    }

    public String getProduct_id() {
        return product_id;
    }

    public int getPriority() {
        return priority;
    }

    public float getPrice() {
        return price;
    }

    public String getCurr() {
        return curr;
    }
}
