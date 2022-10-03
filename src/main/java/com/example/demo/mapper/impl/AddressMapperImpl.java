package com.example.demo.mapper.impl;

import com.example.demo.dto.AddressDto;
import com.example.demo.entity.Address;
import com.example.demo.mapper.AddressMapper;

public class AddressMapperImpl implements AddressMapper {

    @Override
    public Address addressDtoToAddress(AddressDto addressDto) {
        if (addressDto == null) {
            return null;
        }
        CustomerMapperImpl customerMapper = new CustomerMapperImpl();
        Address address = new Address(addressDto.getStreet(), addressDto.getCity(), addressDto.getState(),
                addressDto.getZip());

        address.setCustomers(customerMapper.customerDtoListToCustomerList(addressDto.getCustomers()));

        return address;
    }


    @Override
    public AddressDto addressToAddressDto(Address address) {
        if (address == null) {
            return null;
        }

        AddressDto addressDto = new AddressDto(address.getId(), address.getStreet(), address.getCity(),
                address.getState(),
                address.getZip());
        addressDto.setCustomers(new CustomerMapperImpl().customerListToCustomerDtoList(address.getCustomers()));

        return addressDto;
    }

    public AddressDto addressToAddressDtoWithoutCustomerAddress(Address address) {
        if (address == null) {
            return null;
        }

        AddressDto addressDto = new AddressDto(address.getId(), address.getStreet(), address.getCity(),
                address.getState(),
                address.getZip());
        addressDto.setCustomers(new CustomerMapperImpl().customerListToCustomerDtoListWithoutAddress(
                address.getCustomers()));

        return addressDto;
    }

    public AddressDto addressToAddressDtoWithoutCustomer(Address address) {
        if (address == null) {
            return null;
        }

        return new AddressDto(address.getId(), address.getStreet(), address.getCity(),
                address.getState(),
                address.getZip());
    }

    @Override
    public void linkCustomers(Address address) {
        AddressMapper.super.linkCustomers(address);
    }


    @Override
    public Address updateAddressFromAddressDto(AddressDto addressDto, Address address) {
        if (addressDto == null) {
            return address;
        }
        address.setId(addressDto.getId());
        address.setStreet(addressDto.getStreet());
        address.setCity(addressDto.getCity());
        address.setZip(addressDto.getZip());
        address.setState(addressDto.getState());
        return address;
    }
}

