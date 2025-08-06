package az.subhannaghiyev.wearwibe.mapper;

import az.subhannaghiyev.wearwibe.dto.OrderResponseDto;
import az.subhannaghiyev.wearwibe.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserMapper.class, OrderItemMapper.class})
public interface OrderMapper {

    OrderResponseDto toDto(Order order);
    Order toEntity(OrderResponseDto orderDto);

}
