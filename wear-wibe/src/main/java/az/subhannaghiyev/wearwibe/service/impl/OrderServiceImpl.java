package az.subhannaghiyev.wearwibe.service.impl;

import az.subhannaghiyev.wearwibe.dto.OrderResponseDto;
import az.subhannaghiyev.wearwibe.entity.Order;
import az.subhannaghiyev.wearwibe.entity.Product;
import az.subhannaghiyev.wearwibe.entity.enums.OrderStatus;
import az.subhannaghiyev.wearwibe.mapper.OrderMapper;
import az.subhannaghiyev.wearwibe.repository.OrderRepository;
import az.subhannaghiyev.wearwibe.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrderResponseDto createOrder(Order order) {
        Order savedOrder = orderRepository.save(order);
        return orderMapper.toDto(savedOrder);
    }

    @Override
    public OrderResponseDto findById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
        return orderMapper.toDto(order);
    }

    @Override
    public Page<OrderResponseDto> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable)
                .map(orderMapper::toDto);
    }

    @Override
    public List<OrderResponseDto> findByUserId(Long userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        return orders.stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    public void updateStatus(Long id, OrderStatus status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
        order.setStatus(status);
        orderRepository.save(order);
    }

    @Override
    public void delete(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new RuntimeException("Order not found with id: " + id);
        }
        orderRepository.deleteById(id);
    }

    @Override
    public List<OrderResponseDto> findByOrderDateBetween(LocalDateTime start, LocalDateTime end) {
        List<Order> orders = orderRepository.findByOrderDateBetween(start, end);
        return orders.stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    public OrderResponseDto updateOrderStatus(Long orderId, Map<String, String> status) {
        String statusStr = status.get("status");
        if (Objects.isNull(statusStr)) {
            throw new IllegalArgumentException("Status sahəsi göndərilməyib");
        }

        OrderStatus newStatus;
        try {
            newStatus = OrderStatus.valueOf(statusStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Düzgün status göndərilməyib: " + statusStr);
        }

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order tapılmadı: " + orderId));

        order.setStatus(newStatus);
        Order updatedOrder = orderRepository.save(order);
        return orderMapper.toDto(updatedOrder);
    }

    @Override
    public Map<OrderStatus, Long> countOrdersByStatus() {
        return orderRepository.countGroupedByStatus()
                .stream()
                .collect(Collectors.toMap(
                        row -> (OrderStatus) row[0],
                        row -> (Long) row[1]
                ));
    }

    @Override
    public List<OrderResponseDto> findOrdersByProductId(Long productId) {
        List<Order> orders = orderRepository.findByItemsProductId(productId);
        return orders.stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    public BigDecimal getTotalSpentByUser(Long userId) {
        return Optional.ofNullable(orderRepository.sumTotalByUserId(userId))
                .orElse(BigDecimal.ZERO);
    }

    @Override
    public List<Product> getMostOrderedProducts(int topN) {
        return orderRepository.findTopProducts(topN);
    }

    @Override
    public BigDecimal getAverageOrderValue() {
        List<Order> orders = orderRepository.findAllWithItems();
        if (orders.isEmpty()) return BigDecimal.ZERO;

        BigDecimal totalSum = orders.stream()
                .map(order -> order.getItems().stream()
                        .map(item -> item.getOrder().getTotalPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
                )
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return totalSum.divide(BigDecimal.valueOf(orders.size()), 2, RoundingMode.HALF_UP);
    }
}
