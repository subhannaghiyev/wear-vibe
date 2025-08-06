package az.subhannaghiyev.wearwibe.service;

import az.subhannaghiyev.wearwibe.dto.OrderResponseDto;
import az.subhannaghiyev.wearwibe.entity.Order;
import az.subhannaghiyev.wearwibe.entity.Product;
import az.subhannaghiyev.wearwibe.entity.enums.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface OrderService {

    OrderResponseDto createOrder(Order order);
    OrderResponseDto findById(Long id);
    Page<OrderResponseDto> findAll(Pageable pageable);
    List<OrderResponseDto> findByUserId(Long userId);
    void updateStatus(Long id, OrderStatus status);
    void delete(Long id);
    List<OrderResponseDto> findByOrderDateBetween(LocalDateTime start, LocalDateTime end);
    OrderResponseDto updateOrderStatus(Long orderId, Map<String, String> status);
    Map<OrderStatus, Long> countOrdersByStatus();
    List<OrderResponseDto> findOrdersByProductId(Long productId);
    BigDecimal getTotalSpentByUser(Long userId);
    List<Product> getMostOrderedProducts(int topN);
    BigDecimal getAverageOrderValue();
}
