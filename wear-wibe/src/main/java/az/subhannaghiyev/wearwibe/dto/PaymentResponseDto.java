package az.subhannaghiyev.wearwibe.dto;

import az.subhannaghiyev.wearwibe.entity.enums.PaymentMethod;
import az.subhannaghiyev.wearwibe.entity.enums.PaymentStatus;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PaymentResponseDto {

    private Long id;
    private OrderResponseDto order;
    private BigDecimal amount;
    private PaymentStatus status;
    private PaymentMethod paymentMethod;
    private LocalDateTime paymentDate;
    private String transactionId;
    private String cardLast4Digits;
    private UserResponseDto user;
    private String description;

}
