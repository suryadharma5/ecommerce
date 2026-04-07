package com.sdh.ecommerce.service;

import com.sdh.ecommerce.client.CustomerClient;
import com.sdh.ecommerce.client.PaymentClient;
import com.sdh.ecommerce.client.ProductClient;
import com.sdh.ecommerce.exception.OrderException;
import com.sdh.ecommerce.mapper.OrderMapper;
import com.sdh.ecommerce.models.*;
import com.sdh.ecommerce.producer.OrderProducer;
import com.sdh.ecommerce.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;

    public Integer create(OrderRequest request) {
        // check the customer -> openfeign
        var customer = customerClient.findCustomerById(request.getCustomerId())
                .orElseThrow(() -> new OrderException("Cannot create order:: No customer exists with provided Id"));

        // purchase the products -> via product svc
        List<PurchaseResponse> purchasedProducts = this.productClient.purchaseProducts(request.getProducts());

        // persist order
        var order = orderRepository.save(mapper.toOrder(request));

        // persist order lines
        for (PurchaseRequest purchaseRequest: request.getProducts()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.getProductId(),
                            purchaseRequest.getQuantity()
                    )
            );
        }

        // start payment process
        PaymentRequest paymentRequest = PaymentRequest.builder()
                .amount(request.getAmount())
                .paymentMethod(request.getPaymentMethod())
                .orderId(order.getId())
                .orderReference(order.getReference())
                .customer(customer)
                .build();

        paymentClient.requestPayment(paymentRequest);

        // send order confirmation -> via notification svc
        OrderConfirmation orderConfirmation = OrderConfirmation.builder()
                .orderReference(request.getReference())
                .totalAmount(request.getAmount())
                .paymentMethod(request.getPaymentMethod())
                .customer(customer)
                .products(purchasedProducts)
                .build();

        orderProducer.sendOrderConfirmation(orderConfirmation);

        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(mapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer orderId) {
        return orderRepository.findById(orderId)
                .map(mapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find order:: No order exists with provided Id"));
    }
}
