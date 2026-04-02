package com.sdh.ecommerce.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductPurchaseResponse {

    private Integer productId;
    private String name;
    private String description;
    private BigDecimal price;
    double quantity;
}
