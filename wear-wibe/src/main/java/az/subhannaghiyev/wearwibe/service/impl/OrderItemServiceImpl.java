package az.subhannaghiyev.wearwibe.service.impl;

import az.subhannaghiyev.wearwibe.entity.OrderItem;
import az.subhannaghiyev.wearwibe.entity.enums.Size;
import az.subhannaghiyev.wearwibe.repository.OrderItemRepository;
import az.subhannaghiyev.wearwibe.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;

    @Override
    public OrderItem create(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @Override
    public OrderItem getById(Long id) {
        return orderItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderItem not found with id: " + id));
    }

    @Override
    public List<OrderItem> getByOrderId(Long orderId) {
        return orderItemRepository.findByOrderId(orderId);
    }

    @Override
    public List<OrderItem> getByProductId(Long productId) {
        return orderItemRepository.findByProductId(productId);
    }

    @Override
    public List<OrderItem> getBySize(Size size) {
        return orderItemRepository.findBySize(size);
    }

    @Override
    public OrderItem update(Long id, OrderItem updatedOrderItem) {
        OrderItem existing = getById(id);
        existing.setProduct(updatedOrderItem.getProduct());
        existing.setQuantity(updatedOrderItem.getQuantity());
        existing.setSize(updatedOrderItem.getSize());
        existing.setOrder(updatedOrderItem.getOrder());
        return orderItemRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        orderItemRepository.deleteById(id);
    }
}
