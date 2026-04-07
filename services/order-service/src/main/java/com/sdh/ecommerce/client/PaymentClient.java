package com.sdh.ecommerce.client;

import com.sdh.ecommerce.models.PaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "payment-service",
        url = "${application.config.payment-svc-url}"
)
public interface PaymentClient {

    @PostMapping
    Integer requestPayment(@RequestBody PaymentRequest payment);
}
