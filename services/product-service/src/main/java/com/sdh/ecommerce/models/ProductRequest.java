package com.sdh.ecommerce.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {
    private Integer id;

    @NotNull(message = "Product name is required")
    private String name;

    @NotNull(message = "Product description is required")
    private String description;

    @Positive(message = "Product quantity should be positive")
    private double availableQuantity;

    @Positive(message = "Product price should be positive")
    private BigDecimal price;

    @Positive(message = "Product category is required")
    private Integer categoryId;
}
