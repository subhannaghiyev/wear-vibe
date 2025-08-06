package az.subhannaghiyev.wearwibe.controller;

import az.subhannaghiyev.wearwibe.dto.OrderItemResponseDto;
import az.subhannaghiyev.wearwibe.entity.OrderItem;
import az.subhannaghiyev.wearwibe.entity.enums.Size;
import az.subhannaghiyev.wearwibe.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/order-items")
@RequiredArgsConstructor
public class OrderItemController {

    private final OrderItemService orderItemService;

    @PostMapping
    public ResponseEntity<OrderItemResponseDto> create(@RequestBody OrderItem orderItem) {
        return ResponseEntity.ok(orderItemService.create(orderItem));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItemResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(orderItemService.getById(id));
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderItemResponseDto>> getByOrderId(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderItemService.getByOrderId(orderId));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<OrderItemResponseDto>> getByProductId(@PathVariable Long productId) {
        return ResponseEntity.ok(orderItemService.getByProductId(productId));
    }

    @GetMapping("/size/{size}")
    public ResponseEntity<List<OrderItemResponseDto>> getBySize(@PathVariable Size size) {
        return ResponseEntity.ok(orderItemService.getBySize(size));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItemResponseDto> update(@PathVariable Long id, @RequestBody OrderItem orderItem) {
        return ResponseEntity.ok(orderItemService.update(id, orderItem));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        orderItemService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
