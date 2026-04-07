package com.sdh.ecommerce.kafka.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Validated
public class Customer {

    private String id;

    private String firstName;

    private String lastName;

    private String email;
}
