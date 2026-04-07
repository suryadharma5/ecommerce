package com.sdh.ecommerce.mapper;

import com.sdh.ecommerce.entity.Order;
import com.sdh.ecommerce.models.OrderRequest;
import com.sdh.ecommerce.models.OrderResponse;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {

    public Order toOrder(OrderRequest request) {

        return Order.builder()
                .id(request.getId())
                .customerId(request.getCustomerId())
                .reference(request.getReference())
                .totalAmount(request.getAmount())
                .paymentMethod(request.getPaymentMethod())
                .build();
    }

    public OrderResponse fromOrder(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .reference(order.getReference())
                .customerId(order.getCustomerId())
                .amount(order.getTotalAmount())
                .paymentMethod(order.getPaymentMethod())
                .build();
    }
}
