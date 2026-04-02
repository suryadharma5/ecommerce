package com.sdh.ecommerce.service;

import com.sdh.ecommerce.entity.Product;
import com.sdh.ecommerce.exception.ProductPurchaseException;
import com.sdh.ecommerce.mapper.ProductMapper;
import com.sdh.ecommerce.models.ProductPurchaseRequest;
import com.sdh.ecommerce.models.ProductPurchaseResponse;
import com.sdh.ecommerce.models.ProductRequest;
import com.sdh.ecommerce.models.ProductResponse;
import com.sdh.ecommerce.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;
    private final ProductMapper mapper;

    public Integer create(ProductRequest request) {
        var product = mapper.toProduct(request);
        Product newProduct = repository.save(product);
        return newProduct.getId();
    }

    public List<ProductPurchaseResponse> purchase(List<ProductPurchaseRequest> request) {
        // get all product ids
        var productIds = request
                .stream()
                .map(ProductPurchaseRequest::getProductId)
                .toList();

        // validate productIds is available in DB
        var storedProducts = repository.findAllByIdInOrderById(productIds);
        if (productIds.size() != storedProducts.size()) {
            throw new ProductPurchaseException("One or more products does not exists");
        }

        var storedRequest = request
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::getProductId))
                .toList();

        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();

        for (int i = 0; i < storedProducts.size(); i++) {
            var product = storedProducts.get(i);
            var productRequest = storedRequest.get(i);

            if(product.getAvailableQuantity() < productRequest.getQuantity()) {
                throw new ProductPurchaseException("Insufficient quantity for " + product.getName());
            }

            product.setAvailableQuantity(product.getAvailableQuantity() - productRequest.getQuantity());
            repository.save(product);
            purchasedProducts.add(mapper.toProductPurchaseResponse(product, productRequest.getQuantity()));
        }
        
        return purchasedProducts;
    }

    public ProductResponse getProduct(Integer productId) {
        return repository.findById(productId)
                .map(product -> mapper.toProductResponse(product))
                .orElseThrow(() -> new EntityNotFoundException(("Product not found with the Id:: " + productId)));
    }

    public List<ProductResponse> getAllProducts() {
        return repository.findAll()
                .stream()
                .map(mapper::toProductResponse)
                .collect(Collectors.toList());
    }
}
