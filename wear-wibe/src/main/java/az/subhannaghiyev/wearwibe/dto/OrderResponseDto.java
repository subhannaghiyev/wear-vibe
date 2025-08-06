package az.subhannaghiyev.wearwibe.dto;

import az.subhannaghiyev.wearwibe.entity.enums.OrderStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponseDto {

    private Long id;
    private UserResponseDto user;
    private List<OrderItemResponseDto> items;
    private BigDecimal totalPrice;
    private LocalDateTime orderDate;
    private OrderStatus status;

}
