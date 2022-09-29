package com.example.demo.resource;

import com.example.demo.Customer;
import com.example.demo.CustomerRepository;
import com.example.demo.request.dto.CustomerRequestDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2")
@Api( tags = "Customers")
public class CustomerController {

	@Autowired
	CustomerRepository customerRepository;

	@GetMapping("/customers")
	@ApiOperation(value = "This method is used to get the customers.")
	public List<Customer> getCustomers() {
		return Streamable.of(customerRepository.findAll()).toList().stream()
				.filter(customer -> customer.getId() != null).toList();
	}

	@GetMapping("/customer/{id}")
	@ApiOperation(value = "This method is used to get the customers by id.")
	public Customer getCustomer(@PathVariable("id") String id) {
		return customerRepository.findById(Long.parseLong(id));
	}

	@GetMapping("/customersStartsWithJa")
	@ApiOperation(value = "This method is used to get the customers starts with Ja.")
	public List<Customer> getCustomersStartsJa() {
		return Streamable.of(customerRepository.findAll()).toList().stream()
				.filter(customer -> customer.getFirstName().startsWith("Ja")).toList();
	}

	@PostMapping("/addAll")
	@ApiOperation(value = "This method is used to save the customers.")
	public List<Customer> saveCustomers(@RequestBody List<CustomerRequestDTO> customers) {
		return Streamable.of(customerRepository.saveAll(customers.stream().map(customerRequestDTO -> {
			Customer customer = new Customer();
			customer.setFirstName(customerRequestDTO.getFirstName());
			customer.setLastName(customerRequestDTO.getLastName());
			return customer;
		}).toList())).toList();

	}
}