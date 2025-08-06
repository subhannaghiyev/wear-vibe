package az.subhannaghiyev.wearwibe.dto;

import az.subhannaghiyev.wearwibe.entity.enums.Category;
import az.subhannaghiyev.wearwibe.entity.enums.Size;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductResponseDto {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private List<Size> sizes;
    private String color;
    private String imageUrl;
    private Category category;

}
