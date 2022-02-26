package com.inditex.priceapi.repositories;

import com.inditex.priceapi.entities.Prices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PricesRepository extends JpaRepository<Prices, Long> {

    @Query("select p from Prices p where p.start_date < :datetime and p.end_date > :datetime" +
            " and p.brand_id = :brand_id and p.product_id = :product_id" +
            " order by p.priority")
    List<Prices> findPricesByDatetime(@Param("datetime") LocalDateTime datetime,
                                      @Param("product_id") String productId,
                                      @Param("brand_id") String brandId);
}
