package com.sdh.ecommerce.repository;

import com.sdh.ecommerce.entity.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
}
