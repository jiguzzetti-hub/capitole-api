package com.inditex.priceapi.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@SequenceGenerator(name = "SEQ_PRICES", initialValue = 1, allocationSize = 1, sequenceName = "SEQ_PRICES")
@Table(name= "PRICES")
public class Prices {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PRICES")
    private Long id;

    @JoinColumn(nullable = false, foreignKey = @ForeignKey(name = "FK_BRAND_PRICES"))
    private int brand_id;
    @Column(name = "start_date")
    private LocalDateTime start_date;
    @Column(name = "end_date")
    private LocalDateTime end_date;
    @Column(name = "price_list")
    private int price_list;
    @Column(name = "product_id")
    private int product_id;
    @Column(name = "priority")
    private int priority;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "curr")
    private String curr;

    public Prices(int brand_id, LocalDateTime start_date, LocalDateTime end_date, int price_list, int product_id, int priority, BigDecimal price, String curr) {
        this.brand_id = brand_id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.price_list = price_list;
        this.product_id = product_id;
        this.priority = priority;
        this.price = price;
        this.curr = curr;
    }

    public Prices() {
    }

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

    public int getProduct_id() {
        return product_id;
    }

    public int getPriority() {
        return priority;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCurr() {
        return curr;
    }
}
