package com.sdh.ecommerce.models;

import com.sdh.ecommerce.entity.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentNotificationRequest {
    private String orderReference;

    private BigDecimal amount;

    private PaymentMethod paymentMethod;

    private String customerFirstName;

    private String customerLastName;

    private String customerEmail;
}
