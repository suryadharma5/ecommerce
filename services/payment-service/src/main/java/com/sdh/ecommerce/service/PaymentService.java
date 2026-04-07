package com.sdh.ecommerce.service;

import com.sdh.ecommerce.models.PaymentNotificationRequest;
import com.sdh.ecommerce.models.PaymentRequest;
import com.sdh.ecommerce.producer.NotificationProducer;
import com.sdh.ecommerce.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository repository;
    private final PaymentMapper mapper;
    private final NotificationProducer notificationProducer;

    public Integer createPayment(PaymentRequest request) {
        var payment = repository.save(mapper.toPayment(request));
        PaymentNotificationRequest notificationRequest = PaymentNotificationRequest.builder()
                .orderReference(request.getOrderReference())
                .paymentMethod(request.getPaymentMethod())
                .amount(request.getAmount())
                .customerFirstName(request.getCustomer().getFirstName())
                .customerLastName(request.getCustomer().getLastName())
                .customerEmail(request.getCustomer().getEmail())
                .build();

        notificationProducer.sendNotification(notificationRequest);
        return payment.getId();
    }
}
