package az.subhannaghiyev.wearwibe.service;

import az.subhannaghiyev.wearwibe.dto.PaymentResponseDto;
import az.subhannaghiyev.wearwibe.entity.Payment;
import az.subhannaghiyev.wearwibe.entity.enums.PaymentStatus;

import java.util.List;

public interface PaymentService {

    PaymentResponseDto createPayment(Payment payment);
    PaymentResponseDto getPaymentById(Long id);
    PaymentResponseDto getPaymentByOrderId(Long orderId);
    List<PaymentResponseDto> getAllPayments();
    PaymentResponseDto updatePaymentStatus(Long id, PaymentStatus status);
    void deletePayment(Long id);

}
