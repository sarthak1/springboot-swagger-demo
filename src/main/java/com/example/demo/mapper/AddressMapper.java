package com.example.demo.mapper;

import com.example.demo.dto.AddressDto;
import com.example.demo.entity.Address;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AddressMapper {
    Address addressDtoToAddress(AddressDto addressDto1);

    AddressDto addressToAddressDto(Address address);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Address updateAddressFromAddressDto(AddressDto addressDto, @MappingTarget Address address);

    @org.mapstruct.AfterMapping
    default void linkCustomers(@org.mapstruct.MappingTarget Address address) {
        address.getCustomers().forEach(customer -> customer.setAddress(address));
    }
}
