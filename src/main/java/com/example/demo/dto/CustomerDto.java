package com.example.demo.dto;

import com.example.demo.entity.Customer;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link Customer} entity
 */
@Data
public class CustomerDto implements Serializable {
    private final Long id;
    private final String firstName;
    private final String lastName;
    private final AddressDto address;
}