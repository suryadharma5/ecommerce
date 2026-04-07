package com.sdh.ecommerce.kafka.models;

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
public class PaymentConfirmation {

    private String orderReference;

    private BigDecimal amount;

    private PaymentMethod paymentMethod;

    private String customerFirstName;

    private String customerLastName;

    private String customerEmail;
}
