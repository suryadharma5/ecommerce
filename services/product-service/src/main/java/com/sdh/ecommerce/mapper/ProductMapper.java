package com.sdh.ecommerce.mapper;

import com.sdh.ecommerce.entity.Category;
import com.sdh.ecommerce.entity.Product;
import com.sdh.ecommerce.models.ProductPurchaseResponse;
import com.sdh.ecommerce.models.ProductRequest;
import com.sdh.ecommerce.models.ProductResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public Product toProduct(ProductRequest request) {
        return Product.builder()
                .id(request.getId())
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .availableQuantity(request.getAvailableQuantity())
                .category(
                        Category.builder()
                                .id(request.getCategoryId())
                                .build()
                )
                .build();
    }

    public ProductResponse toProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .availableQuantity(product.getAvailableQuantity())
                .categoryId(product.getCategory().getId())
                .categoryName(product.getCategory().getName())
                .categoryDescription(product.getCategory().getDescription())
                .build();
    }

    public ProductPurchaseResponse toProductPurchaseResponse(Product product,
                                                            double quantity) {
        return ProductPurchaseResponse.builder()
                .productId(product.getId())
                .quantity(quantity)
                .price(product.getPrice())
                .name(product.getName())
                .description(product.getDescription())
                .build();
    }
}
