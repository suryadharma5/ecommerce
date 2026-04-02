package com.sdh.ecommerce.controller;

import com.sdh.ecommerce.models.ProductPurchaseRequest;
import com.sdh.ecommerce.models.ProductPurchaseResponse;
import com.sdh.ecommerce.models.ProductRequest;
import com.sdh.ecommerce.models.ProductResponse;
import com.sdh.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Integer> createProduct(@RequestBody @Valid ProductRequest request) {
        return ResponseEntity.ok(productService.create(request));
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProducts(
            @RequestBody List<ProductPurchaseRequest> request) {
        return ResponseEntity.ok(productService.purchase(request));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> findProductById(@PathVariable("productId") Integer productId) {
        return ResponseEntity.ok(productService.getProduct(productId));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }
}
