package az.subhannaghiyev.wearwibe.dto;

import az.subhannaghiyev.wearwibe.entity.enums.Size;
import lombok.Data;

@Data
public class OrderItemResponseDto {

    private Long id;
    private ProductResponseDto product;
    private int quantity;
    private Size size;

}
