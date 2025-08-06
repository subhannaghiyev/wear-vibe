package az.subhannaghiyev.wearwibe.mapper;

import az.subhannaghiyev.wearwibe.dto.CartItemResponseDto;
import az.subhannaghiyev.wearwibe.entity.CartItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CartMapper.class, ProductMapper.class})
public interface CartItemMapper {

    CartItemResponseDto toDto(CartItem cartItem);
    CartItem toEntity(CartItemResponseDto cartItemDto);

}
