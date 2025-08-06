package az.subhannaghiyev.wearwibe.service.impl;

import az.subhannaghiyev.wearwibe.dto.ProductResponseDto;
import az.subhannaghiyev.wearwibe.entity.Product;
import az.subhannaghiyev.wearwibe.entity.enums.Category;
import az.subhannaghiyev.wearwibe.mapper.ProductMapper;
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
    private final ProductMapper productMapper;

    @Override
    public ProductResponseDto saveProduct(Product productRequestDto) {
        Product product = productRepository.save(productRequestDto);
        return productMapper.toDto(product);
    }

    @Override
    public Optional<ProductResponseDto> getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(productMapper::toDto);
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return productMapper.toDtoList(products);
    }

    @Override
    public ProductResponseDto updateProduct(Long id, Product productDetails) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(productDetails.getName());
        product.setPrice(productDetails.getPrice());
        product.setCategory(productDetails.getCategory());
        product.setSizes(productDetails.getSizes());
        product.setImageUrl(productDetails.getImageUrl());

        productRepository.save(product);

        return productMapper.toDto(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Page<ProductResponseDto> search(String keyword, Category category, String color, BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable) {
        ProductSpecification spec = new ProductSpecification(keyword, category, color, minPrice, maxPrice);
        Page<Product> products = productRepository.findAll(spec, pageable);
        return products.map(productMapper::toDto);
    }

    @Override
    public List<ProductResponseDto> getSimilarProducts(Long productId) {
        Product currentProduct = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        BigDecimal currentPrice = currentProduct.getPrice();

        BigDecimal minPrice = currentPrice.multiply(BigDecimal.valueOf(0.8));
        BigDecimal maxPrice = currentPrice.multiply(BigDecimal.valueOf(1.2));

        List<Product> products = productRepository.findTop5ByCategoryAndColorAndIdNotAndPriceBetween(
                currentProduct.getCategory(),
                currentProduct.getColor(),
                currentProduct.getId(),
                minPrice,
                maxPrice
        );

        return productMapper.toDtoList(products);
    }
}
