package az.subhannaghiyev.wearwibe.mapper;

import az.subhannaghiyev.wearwibe.dto.OrderItemResponseDto;
import az.subhannaghiyev.wearwibe.entity.OrderItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface OrderItemMapper {

    OrderItemResponseDto toDto(OrderItem orderItem);
    OrderItem toEntity(OrderItemResponseDto orderItemDto);

}
