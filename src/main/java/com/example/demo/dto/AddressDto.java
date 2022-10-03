package com.example.demo.dto;

import com.example.demo.entity.Address;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link Address} entity
 */
@Data
public class AddressDto implements Serializable {
    private final Long id;
    private final String street;
    private final String city;
    private final String state;
    private final String zip;
    private List<CustomerDto> customers;
}