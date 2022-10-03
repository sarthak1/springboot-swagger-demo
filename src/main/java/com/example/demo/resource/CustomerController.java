package com.example.demo.resource;

import com.example.demo.dto.AddressDto;
import com.example.demo.dto.CustomerDto;
import com.example.demo.entity.Customer;
import com.example.demo.mapper.impl.AddressMapperImpl;
import com.example.demo.mapper.impl.CustomerMapperImpl;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.CustomerRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v2")
@Api(tags = "Customers")
@Slf4j
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    AddressRepository addressRepository;


    @GetMapping("/customers")
    @ApiOperation(value = "This method is used to get all the customers.")
    public @ResponseBody List<CustomerDto> getCustomers() {
        CustomerMapperImpl customerMapper = new CustomerMapperImpl();
        List<CustomerDto> customers = Streamable.of(customerRepository.findAll()).stream().map(customerMapper::customerToCustomerDto).toList();

        customers.forEach(customer -> log.info("customer$: {}", customer.toString()));
        return customers;
    }

    @GetMapping("/addresses")
    @ApiOperation(value = "This method is used to get all the addresses.")
    public @ResponseBody List<AddressDto> getAddresses() {
        return Streamable.of(addressRepository.findAll()).stream().map(address -> {
            log.info("address$: {}", address.getCustomers().get(0).getFirstName());
            return new AddressMapperImpl().addressToAddressDtoWithoutCustomerAddress(address);
        }).toList();

    }

    @GetMapping("/customer/{id}")
    @ApiOperation(value = "This method is used to get the customers by id.")
    public CustomerDto getCustomer(@PathVariable("id") String id) {
        Optional<Customer> isCustomer = customerRepository.findById(Long.valueOf(id));
        return isCustomer.map(customer -> new CustomerMapperImpl().customerToCustomerDto(customer)).orElse(null);
    }

    @GetMapping("/customersStartsWithJa")
    @ApiOperation(value = "This method is used to get the customers starts with Ja.")
    public List<CustomerDto> getCustomersStartsJa() {
        return new CustomerMapperImpl().customerListToCustomerDtoList(customerRepository.findByFirstNameStartsWith("Ja"));

    }

    @PostMapping("/addAll")
    @ApiOperation(value = "This method is used to save the customers.")
    public List<Customer> saveCustomers(@RequestBody List<CustomerDto> customers) {
        return Streamable.of(customerRepository.saveAll(customers.stream().map(customerDto -> {
            Customer customer = new Customer();
            customer.setFirstName(customerDto.getFirstName());
            customer.setLastName(customerDto.getLastName());

            return customer;
        }).toList())).toList();

    }
}