package com.sdh.ecommerce.controller;

import com.sdh.ecommerce.models.OrderRequest;
import com.sdh.ecommerce.models.OrderResponse;
import com.sdh.ecommerce.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Integer> createOrder(@RequestBody @Valid OrderRequest order) {
        return ResponseEntity.ok(orderService.create(order));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> findById(@PathVariable("orderId") Integer orderId) {
        return ResponseEntity.ok(orderService.findById(orderId));
    }
}
