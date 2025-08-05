package az.subhannaghiyev.wearwibe.entity;

import az.subhannaghiyev.wearwibe.entity.enums.PaymentMethod;
import az.subhannaghiyev.wearwibe.entity.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "payments")
public class Payment extends BaseEntity{

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false, unique = true)
    private Order order;

    @Column(nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethod paymentMethod;

    @Column(name = "payment_date", nullable = false)
    private LocalDateTime paymentDate;

    private String transactionId;

    private String cardLast4Digits;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String description;

}
