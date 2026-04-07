package com.sdh.ecommerce.client;

import com.sdh.ecommerce.exception.OrderException;
import com.sdh.ecommerce.models.PurchaseRequest;
import com.sdh.ecommerce.models.PurchaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductClient {

    @Value("${application.config.product-svc-url}")
    private String productUrl;

    private final RestTemplate restTemplate;

    public List<PurchaseResponse> purchaseProducts(List<PurchaseRequest> requestBody) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<List<PurchaseRequest>> entity = new HttpEntity<>(requestBody, headers);
        ParameterizedTypeReference<List<PurchaseResponse>> responseType =
                new ParameterizedTypeReference<>() {};

        ResponseEntity<List<PurchaseResponse>> response = restTemplate.exchange(
                productUrl,
                HttpMethod.POST,
                entity,
                responseType
        );

        if (response.getStatusCode().isError()) {
           throw new OrderException("An error occurred while processing the product purchase: "+ response.getStatusCode());
        }

        return response.getBody();
    }
}
