package az.subhannaghiyev.wearwibe.service;

import az.subhannaghiyev.wearwibe.dto.OrderItemResponseDto;
import az.subhannaghiyev.wearwibe.entity.OrderItem;
import az.subhannaghiyev.wearwibe.entity.enums.Size;

import java.util.List;

public interface OrderItemService {
    OrderItemResponseDto create(OrderItem orderItem);
    OrderItemResponseDto getById(Long id);
    List<OrderItemResponseDto> getByOrderId(Long orderId);
    List<OrderItemResponseDto> getByProductId(Long productId);
    List<OrderItemResponseDto> getBySize(Size size);
    OrderItemResponseDto update(Long id, OrderItem updatedOrderItem);
    void delete(Long id);
}
