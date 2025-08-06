package az.subhannaghiyev.wearwibe.mapper;

import az.subhannaghiyev.wearwibe.dto.CartResponseDto;
import az.subhannaghiyev.wearwibe.entity.Cart;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface CartMapper {

    CartResponseDto toDto(Cart cart);
    Cart toEntity(CartResponseDto cartDto);

}
