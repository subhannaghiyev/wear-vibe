package az.subhannaghiyev.wearwibe.dto;

import lombok.Data;
import java.util.List;

@Data
public class CartResponseDto {

    private Long id;
    private UserResponseDto user;
    private List<CartItemResponseDto> cartItems;

}
