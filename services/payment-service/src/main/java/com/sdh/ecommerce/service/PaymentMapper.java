package com.sdh.ecommerce.service;

import com.sdh.ecommerce.entity.Payment;
import com.sdh.ecommerce.models.PaymentRequest;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {
    public Payment toPayment(PaymentRequest request) {
        return Payment.builder()
                .id(request.getId())
                .amount(request.getAmount())
                .paymentMethod(request.getPaymentMethod())
                .orderId(request.getOrderId())
                .build();
    }
}
