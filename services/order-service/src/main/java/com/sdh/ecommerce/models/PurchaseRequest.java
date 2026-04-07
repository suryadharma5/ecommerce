package com.sdh.ecommerce.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseRequest {

    @NotNull(message = "Product is mandatory")
    private Integer productId;

    @Positive(message = "Quantity is mandatory")
    private double quantity;
}
