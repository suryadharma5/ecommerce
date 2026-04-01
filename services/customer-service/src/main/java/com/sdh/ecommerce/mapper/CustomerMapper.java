package com.sdh.ecommerce.mapper;

import com.sdh.ecommerce.entity.Customer;
import com.sdh.ecommerce.models.CustomerRequest;
import com.sdh.ecommerce.models.CustomerResponse;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CustomerMapper {
    public Customer toCustomer(CustomerRequest request) {
        if(request == null) {
            return null;
        }

        return Customer.builder()
                .id(request.id())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .address(request.address())
                .build();
    }

    public CustomerResponse toCustomerResponse(Customer customer) {
        return CustomerResponse
                .builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .address(customer.getAddress())
                .build();
    }
}
