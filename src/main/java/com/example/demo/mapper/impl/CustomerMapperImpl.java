package com.example.demo.mapper.impl;

import com.example.demo.dto.AddressDto;
import com.example.demo.dto.CustomerDto;
import com.example.demo.entity.Address;
import com.example.demo.entity.Customer;
import com.example.demo.mapper.CustomerMapper;

import java.util.ArrayList;
import java.util.List;

public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public Customer customerDtoToCustomer(CustomerDto customerDto) {
        if (customerDto == null) {
            return null;
        }

        Customer customer = new Customer();

        customer.setId(customerDto.getId());
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setAddress(addressDtoToAddress(customerDto.getAddress()));

        return customer;
    }

    public List<Customer> customerDtoListToCustomerList(List<CustomerDto> customers) {
        if (customers == null) {
            return null;
        }
        CustomerMapperImpl customerMapper = new CustomerMapperImpl();
        List<Customer> list = new ArrayList<>(customers.size());
        for (CustomerDto customerDto : customers) {
            list.add(customerMapper.customerDtoToCustomer(customerDto));
        }
        return list;
    }

    public List<CustomerDto> customerListToCustomerDtoList(List<Customer> customers) {
        if (customers == null) {
            return null;
        }
        CustomerMapperImpl customerMapper = new CustomerMapperImpl();
        List<CustomerDto> list = new ArrayList<>(customers.size());
        for (Customer customer : customers) {
            list.add(customerMapper.customerToCustomerDto(customer));
        }
        return list;
    }

    public List<CustomerDto> customerListToCustomerDtoListWithoutAddress(List<Customer> customers) {
        if (customers == null) {
            return null;
        }
        CustomerMapperImpl customerMapper = new CustomerMapperImpl();
        List<CustomerDto> list = new ArrayList<>(customers.size());
        for (Customer customer : customers) {
            list.add(customerMapper.customerToCustomerDtoWithoutAddress(customer));
        }
        return list;
    }

    @Override
    public CustomerDto customerToCustomerDto(Customer customer) {
        if (customer == null) {
            return null;
        }
        AddressMapperImpl addressMapper = new AddressMapperImpl();

        return new CustomerDto(customer.getId(),
                customer.getFirstName(), customer.getLastName(),
                addressMapper.addressToAddressDto(customer.getAddress()));
    }

    public CustomerDto customerToCustomerDtoWithoutAddress(Customer customer) {
        if (customer == null) {
            return null;
        }


        return new CustomerDto(customer.getId(),
                customer.getFirstName(), customer.getLastName(),
                null);
    }

    @Override
    public Customer updateCustomerFromCustomerDto(CustomerDto customerDto, Customer customer) {
        if (customerDto == null) {
            return customer;
        }

        customer.setId(customerDto.getId());
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setAddress(addressDtoToAddress(customerDto.getAddress()));

        return customer;
    }

    protected Address addressDtoToAddress(AddressDto addressDto) {
        if (addressDto == null) {
            return null;
        }

        Address address = new Address();

        address.setId(addressDto.getId());
        address.setStreet(addressDto.getStreet());
        address.setCity(addressDto.getCity());
        address.setState(addressDto.getState());
        address.setZip(addressDto.getZip());
        address.setCustomers(customerDtoListToCustomerList(addressDto.getCustomers()));

        return address;
    }

}
