package com.sdh.ecommerce.models;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductPurchaseRequest {
    @NotNull(message = "Product is mandatory")
    private Integer productId;
    @NotNull(message = "Quantity is mandatory")
    private double quantity;
}
