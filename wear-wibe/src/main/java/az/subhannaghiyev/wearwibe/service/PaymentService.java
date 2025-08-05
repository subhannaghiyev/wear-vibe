package az.subhannaghiyev.wearwibe.service;

import az.subhannaghiyev.wearwibe.entity.Payment;
import az.subhannaghiyev.wearwibe.entity.enums.PaymentStatus;

import java.util.List;

public interface PaymentService {

    Payment createPayment(Payment payment);
    Payment getPaymentById(Long id);
    Payment getPaymentByOrderId(Long orderId);
    List<Payment> getAllPayments();
    Payment updatePaymentStatus(Long id, PaymentStatus status);
    void deletePayment(Long id);

}
