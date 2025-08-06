package az.subhannaghiyev.wearwibe.service.impl;

import az.subhannaghiyev.wearwibe.dto.OrderItemResponseDto;
import az.subhannaghiyev.wearwibe.entity.OrderItem;
import az.subhannaghiyev.wearwibe.entity.enums.Size;
import az.subhannaghiyev.wearwibe.mapper.OrderItemMapper;
import az.subhannaghiyev.wearwibe.repository.OrderItemRepository;
import az.subhannaghiyev.wearwibe.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;

    @Override
    public OrderItemResponseDto create(OrderItem orderItem) {
        OrderItem saved = orderItemRepository.save(orderItem);
        return orderItemMapper.toDto(saved);
    }

    @Override
    public OrderItemResponseDto getById(Long id) {
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderItem not found with id: " + id));
        return orderItemMapper.toDto(orderItem);
    }

    @Override
    public List<OrderItemResponseDto> getByOrderId(Long orderId) {
        List<OrderItem> list = orderItemRepository.findByOrderId(orderId);
        return list.stream().map(orderItemMapper::toDto).toList();
    }

    @Override
    public List<OrderItemResponseDto> getByProductId(Long productId) {
        List<OrderItem> list = orderItemRepository.findByProductId(productId);
        return list.stream().map(orderItemMapper::toDto).toList();
    }

    @Override
    public List<OrderItemResponseDto> getBySize(Size size) {
        List<OrderItem> list = orderItemRepository.findBySize(size);
        return list.stream().map(orderItemMapper::toDto).toList();
    }

    @Override
    public OrderItemResponseDto update(Long id, OrderItem updatedOrderItem) {
        OrderItem existing = orderItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderItem not found with id: " + id));
        existing.setProduct(updatedOrderItem.getProduct());
        existing.setQuantity(updatedOrderItem.getQuantity());
        existing.setSize(updatedOrderItem.getSize());
        existing.setOrder(updatedOrderItem.getOrder());
        OrderItem saved = orderItemRepository.save(existing);
        return orderItemMapper.toDto(saved);
    }

    @Override
    public void delete(Long id) {
        orderItemRepository.deleteById(id);
    }
}
