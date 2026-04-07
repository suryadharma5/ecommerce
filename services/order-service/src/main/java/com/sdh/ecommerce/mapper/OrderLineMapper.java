package com.sdh.ecommerce.mapper;

import com.sdh.ecommerce.entity.Order;
import com.sdh.ecommerce.entity.OrderLine;
import com.sdh.ecommerce.models.OrderLineRequest;
import com.sdh.ecommerce.models.OrderLineResponse;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequest request) {
        return OrderLine.builder()
                .id(request.getId())
                .quantity(request.getQuantity())
                .order(
                        Order.builder()
                                .id(request.getOrderId())
                                .build()
                )
                .productId(request.getProductId())
                .build();
    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return OrderLineResponse.builder()
                .id(orderLine.getId())
                .quantity(orderLine.getQuantity())
                .build();
    }
}
