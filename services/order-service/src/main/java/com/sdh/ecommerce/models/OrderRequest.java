package com.sdh.ecommerce.models;

import com.sdh.ecommerce.entity.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
public class OrderRequest {

    private Integer id;

    private String reference;

    @Positive(message = "Order amount should be positive")
    private BigDecimal amount;

    @NotNull(message = "Payment method is mandatory")
    private PaymentMethod paymentMethod;

    @NotNull(message = "Customer id is mandatory")
    @NotEmpty(message = "Customer id is mandatory")
    @NotBlank(message = "Customer id is mandatory")
    private String customerId;

    @NotEmpty(message = "Product is required")
    List<PurchaseRequest> products;
}
