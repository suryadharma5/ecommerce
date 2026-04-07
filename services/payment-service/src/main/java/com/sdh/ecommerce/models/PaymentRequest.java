package com.sdh.ecommerce.models;

import com.sdh.ecommerce.entity.Customer;
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
public class PaymentRequest {

    private Integer id;

    private BigDecimal amount;

    private PaymentMethod paymentMethod;

    private Integer orderId;

    private String orderReference;

    private Customer customer;
}
