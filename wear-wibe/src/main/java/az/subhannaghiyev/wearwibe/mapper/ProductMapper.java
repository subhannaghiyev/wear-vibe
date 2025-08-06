package az.subhannaghiyev.wearwibe.mapper;

import az.subhannaghiyev.wearwibe.dto.ProductResponseDto;
import az.subhannaghiyev.wearwibe.entity.Product;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductResponseDto toDto(Product product);
    Product toEntity(ProductResponseDto productDto);

    List<ProductResponseDto> toDtoList(List<Product> products);
    List<Product> toEntityList(List<ProductResponseDto> productDtos);

}
