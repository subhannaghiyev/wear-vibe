package az.subhannaghiyev.wearwibe.repository;

import az.subhannaghiyev.wearwibe.entity.Product;
import az.subhannaghiyev.wearwibe.entity.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    List<Product> findTop5ByCategoryAndColorAndIdNotAndPriceBetween(
            Category category,
            String color,
            Long excludedId,
            BigDecimal minPrice,
            BigDecimal maxPrice
    );


}