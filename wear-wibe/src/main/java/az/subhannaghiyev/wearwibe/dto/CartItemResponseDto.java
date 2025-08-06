package az.subhannaghiyev.wearwibe.dto;

import lombok.Data;

@Data
public class CartItemResponseDto {

    private Long id;
    private ProductResponseDto product;
    private int quantity;

}
