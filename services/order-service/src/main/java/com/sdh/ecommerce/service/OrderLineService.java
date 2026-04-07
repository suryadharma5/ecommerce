package com.sdh.ecommerce.service;

import com.sdh.ecommerce.mapper.OrderLineMapper;
import com.sdh.ecommerce.models.OrderLineRequest;
import com.sdh.ecommerce.repository.OrderLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderLineService {
    private final OrderLineRepository repository;
    private final OrderLineMapper mapper;

    public Integer saveOrderLine(OrderLineRequest request) {
        var order = mapper.toOrderLine(request);
        return repository.save(order).getId();
    }
}
