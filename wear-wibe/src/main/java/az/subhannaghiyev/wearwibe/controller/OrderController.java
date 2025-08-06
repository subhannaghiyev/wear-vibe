package az.subhannaghiyev.wearwibe.controller;

import az.subhannaghiyev.wearwibe.dto.OrderResponseDto;
import az.subhannaghiyev.wearwibe.entity.Order;
import az.subhannaghiyev.wearwibe.entity.Product;
import az.subhannaghiyev.wearwibe.entity.enums.OrderStatus;
import az.subhannaghiyev.wearwibe.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody Order order) {
        OrderResponseDto createdOrder = orderService.createOrder(order);
        return ResponseEntity.ok(createdOrder);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable Long id) {
        OrderResponseDto order = orderService.findById(id);
        return ResponseEntity.ok(order);
    }

    @GetMapping
    public ResponseEntity<Page<OrderResponseDto>> getAllOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<OrderResponseDto> orders = orderService.findAll(pageable);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderResponseDto>> getOrdersByUserId(@PathVariable Long userId) {
        List<OrderResponseDto> orders = orderService.findByUserId(userId);
        return ResponseEntity.ok(orders);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateOrderStatus(
            @PathVariable Long id,
            @RequestParam OrderStatus status
    ) {
        orderService.updateStatus(id, status);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-date-range")
    public ResponseEntity<List<OrderResponseDto>> getOrdersByDateRange(
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end
    ) {
        List<OrderResponseDto> orders = orderService.findByOrderDateBetween(start, end);
        return ResponseEntity.ok(orders);
    }

    @PutMapping("/{orderId}/status")
    public ResponseEntity<OrderResponseDto> updateOrderStatus(
            @PathVariable Long orderId,
            @RequestBody Map<String, String> status) {

        OrderResponseDto updatedOrder = orderService.updateOrderStatus(orderId, status);
        return ResponseEntity.ok(updatedOrder);
    }

    @GetMapping("/count-by-status")
    public ResponseEntity<Map<OrderStatus, Long>> countOrdersByStatus() {
        Map<OrderStatus, Long> result = orderService.countOrdersByStatus();
        return ResponseEntity.ok(result);
    }

    // 2. Məhsula görə sifarişlərin siyahısı
    @GetMapping("/by-product/{productId}")
    public ResponseEntity<List<OrderResponseDto>> findOrdersByProductId(@PathVariable Long productId) {
        List<OrderResponseDto> orders = orderService.findOrdersByProductId(productId);
        return ResponseEntity.ok(orders);
    }

    // 3. İstifadəçinin ümumi xərcləməsi
    @GetMapping("/total-spent-by-user/{userId}")
    public ResponseEntity<BigDecimal> getTotalSpentByUser(@PathVariable Long userId) {
        BigDecimal total = orderService.getTotalSpentByUser(userId);
        return ResponseEntity.ok(total);
    }

    // 4. Ən çox sifariş olunan məhsullar
    @GetMapping("/top-products")
    public ResponseEntity<List<Product>> getMostOrderedProducts(@RequestParam(defaultValue = "5") int topN) {
        List<Product> products = orderService.getMostOrderedProducts(topN);
        return ResponseEntity.ok(products);
    }

    // 5. Sifarişlərin orta məbləği
    @GetMapping("/average-order-value")
    public ResponseEntity<BigDecimal> getAverageOrderValue() {
        BigDecimal avg = orderService.getAverageOrderValue();
        return ResponseEntity.ok(avg);
    }
}