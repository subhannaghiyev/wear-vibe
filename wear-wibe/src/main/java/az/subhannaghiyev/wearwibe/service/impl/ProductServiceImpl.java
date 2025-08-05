package az.subhannaghiyev.wearwibe.service.impl;

import az.subhannaghiyev.wearwibe.entity.Product;
import az.subhannaghiyev.wearwibe.entity.enums.Category;
import az.subhannaghiyev.wearwibe.repository.ProductRepository;
import az.subhannaghiyev.wearwibe.service.ProductService;
import az.subhannaghiyev.wearwibe.specification.ProductSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(Long id, Product productDetails) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(productDetails.getName());
        product.setPrice(productDetails.getPrice());
        product.setCategory(productDetails.getCategory());
        product.setSizes(productDetails.getSizes());
        product.setImageUrl(productDetails.getImageUrl());

        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Page<Product> search(String keyword, Category category, String color, BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable) {
        ProductSpecification spec = new ProductSpecification(keyword, category, color, minPrice, maxPrice);
        return productRepository.findAll(spec, pageable);
    }

    @Override
    public List<Product> getSimilarProducts(Long productId) {
        Product currentProduct = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        BigDecimal currentPrice = currentProduct.getPrice();

        BigDecimal minPrice = currentPrice.multiply(BigDecimal.valueOf(0.8));
        BigDecimal maxPrice = currentPrice.multiply(BigDecimal.valueOf(1.2));

        return productRepository.findTop5ByCategoryAndColorAndIdNotAndPriceBetween(
                currentProduct.getCategory(),
                currentProduct.getColor(),
                currentProduct.getId(),
                minPrice,
                maxPrice
        );
    }
}
