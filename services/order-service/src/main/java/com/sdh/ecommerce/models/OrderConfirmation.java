package com.sdh.ecommerce.models;

import com.sdh.ecommerce.entity.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderConfirmation {

    private String orderReference;

    private BigDecimal totalAmount;

    private PaymentMethod paymentMethod;

    private CustomerResponse customer;

    List<PurchaseResponse> products;
}
