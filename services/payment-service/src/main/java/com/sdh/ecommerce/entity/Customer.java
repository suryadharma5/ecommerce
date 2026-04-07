package com.sdh.ecommerce.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "Firstname is required")
    private String firstName;

    @NotNull(message = "Lastname is required")
    private String lastName;

    @NotNull(message = "Email is required")
    @Email(message = "incorrect email format")
    private String email;
}
