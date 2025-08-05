package az.subhannaghiyev.wearwibe.service;

import az.subhannaghiyev.wearwibe.entity.OrderItem;
import az.subhannaghiyev.wearwibe.entity.enums.Size;

import java.util.List;

public interface OrderItemService {
    OrderItem create(OrderItem orderItem);
    OrderItem getById(Long id);
    List<OrderItem> getByOrderId(Long orderId);
    List<OrderItem> getByProductId(Long productId);
    List<OrderItem> getBySize(Size size);
    OrderItem update(Long id, OrderItem updatedOrderItem);
    void delete(Long id);
}
