package az.subhannaghiyev.wearwibe.service;

import az.subhannaghiyev.wearwibe.dto.ProductResponseDto;
import az.subhannaghiyev.wearwibe.entity.Product;
import az.subhannaghiyev.wearwibe.entity.enums.Category;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductService {

    ProductResponseDto saveProduct(Product product);
    Optional<ProductResponseDto> getProductById(Long id);
    List<ProductResponseDto> getAllProducts();
    ProductResponseDto updateProduct(Long id, Product productDetails);
    void deleteProduct(Long id);
    Page<ProductResponseDto> search(String keyword,
                         Category category,
                         String color,
                         BigDecimal minPrice,
                         BigDecimal maxPrice,
                         Pageable pageable);

    List<ProductResponseDto> getSimilarProducts(Long productId);

}
