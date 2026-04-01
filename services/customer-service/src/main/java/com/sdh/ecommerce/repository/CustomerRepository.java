package com.sdh.ecommerce.repository;

import com.sdh.ecommerce.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
    Object findFirstById(String id);
}
