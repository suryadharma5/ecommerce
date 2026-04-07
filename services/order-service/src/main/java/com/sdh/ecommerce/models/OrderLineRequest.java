package com.sdh.ecommerce.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderLineRequest {

    Integer id;
    Integer orderId;
    Integer productId;
    double quantity;
}
