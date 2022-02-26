package com.inditex.priceapi.repositories;

import com.inditex.priceapi.entities.Prices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PricesRepository extends JpaRepository<Prices, Long> {

    @Query("select p from Prices p where p.start_date <= :datetime and p.end_date >= :datetime" +
            " and p.brand_id = :brand_id and p.product_id = :product_id" +
            " order by p.priority desc")
    List<Prices> findPricesByDatetime(@Param("datetime") LocalDateTime datetime,
                                      @Param("product_id") int productId,
                                      @Param("brand_id") int brandId);

    @Query("select p from Prices p where p.brand_id = :brand_id and p.product_id = :product_id" +
            " and p.priority > :priority and p.start_date <= :end_date and p.start_date >= :datetime" +
            " order by p.start_date asc, p.priority desc")
    List<Prices> findEndDate(@Param("datetime") LocalDateTime datetime,
                             @Param("product_id") int productId,
                             @Param("brand_id") int brandId,
                             @Param("end_date") LocalDateTime endDate,
                             @Param("priority") int priority);

    @Query("select p from Prices p where p.brand_id = :brand_id and p.product_id = :product_id" +
            " and p.priority > :priority and p.end_date >= :start_date and p.end_date <= :datetime" +
            " and p.start_date <= :datetime and p.end_date >= :datetime" +
            " order by p.end_date desc , p.priority desc")
    List<Prices> findStartDate(@Param("datetime") LocalDateTime datetime,
                             @Param("product_id") int productId,
                             @Param("brand_id") int brandId,
                             @Param("start_date") LocalDateTime startDate,
                             @Param("priority") int priority);

}
