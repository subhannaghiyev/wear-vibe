package az.subhannaghiyev.wearwibe.service;

import az.subhannaghiyev.wearwibe.entity.Product;
import az.subhannaghiyev.wearwibe.entity.enums.Category;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product saveProduct(Product product);
    Optional<Product> getProductById(Long id);
    List<Product> getAllProducts();
    Product updateProduct(Long id, Product productDetails);
    void deleteProduct(Long id);
    Page<Product> search(String keyword,
                         Category category,
                         String color,
                         BigDecimal minPrice,
                         BigDecimal maxPrice,
                         Pageable pageable);

    List<Product> getSimilarProducts(Long productId);

}
