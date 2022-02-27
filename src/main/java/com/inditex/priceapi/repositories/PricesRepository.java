package com.inditex.priceapi.repositories;

import com.inditex.priceapi.entities.Prices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PricesRepository extends JpaRepository<Prices, Long> {

    @Query("select p from Prices p where p.startDate <= :datetime and p.endDate >= :datetime" +
            " and p.brandId = :brand_id and p.productId = :product_id" +
            " order by p.priority desc")
    List<Prices> findPricesByDatetime(@Param("datetime") LocalDateTime datetime,
                                      @Param("product_id") int productId,
                                      @Param("brand_id") int brandId);

    @Query("select p from Prices p where p.brandId = :brand_id and p.productId = :product_id" +
            " and p.priority > :priority and p.startDate <= :end_date and p.startDate >= :datetime" +
            " order by p.startDate asc, p.priority desc")
    List<Prices> findEndDate(@Param("datetime") LocalDateTime datetime,
                             @Param("product_id") int productId,
                             @Param("brand_id") int brandId,
                             @Param("end_date") LocalDateTime endDate,
                             @Param("priority") int priority);

    @Query("select p from Prices p where p.brandId = :brand_id and p.productId = :product_id" +
            " and p.priority > :priority and p.endDate >= :start_date and p.endDate <= :datetime" +
            " and p.startDate <= :datetime and p.endDate >= :datetime" +
            " order by p.endDate desc , p.priority desc")
    List<Prices> findStartDate(@Param("datetime") LocalDateTime datetime,
                             @Param("product_id") int productId,
                             @Param("brand_id") int brandId,
                             @Param("start_date") LocalDateTime startDate,
                             @Param("priority") int priority);

}
