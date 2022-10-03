package com.example.demo;

import com.example.demo.entity.Address;
import com.example.demo.entity.Customer;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.util.Streamable;

import java.util.List;

@Slf4j
@SpringBootApplication
public class Demo5Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo5Application.class, args);
    }


    @Bean
    public CommandLineRunner demo(CustomerRepository repository, AddressRepository addressRepository) {
        return (args) -> {

            // save a few customers

            Address address1 = new Address("123 Main St", "Anytown", "CA", "12345");
            Address address2 = new Address("456 Main St", "Anytown", "CA", "12345");
            Address address3 = new Address("789 Main St", "Anytown", "CA", "12345");
            Address address4 = new Address("101 Main St", "Anytown", "CA", "12345");
            Address address5 = new Address("102 Main St", "Anytown", "CA", "12345");
            Address address6 = new Address("103 Main St", "Anytown", "CA", "12345");

            List<Address> addresses = Streamable.of(
                    addressRepository.saveAll(List.of(address1, address2, address3, address4, address5, address6))).toList();
            log.info("addresses: {}", addresses);
            repository.save(new Customer("Jack", "Bauer", addresses.get(0)));
            repository.save(new Customer("Chloe", "O'Brian", addresses.get(1)));
            repository.save(new Customer("Kim", "Bauer", addresses.get(2)));
            repository.save(new Customer("David", "Palmer", addresses.get(3)));
            repository.save(new Customer("Michelle", "Dessler", addresses.get(4)));
            repository.save(new Customer("Jack", "Bauer", addresses.get(5)));
            repository.save(new Customer("Jack", "Bauer", addresses.get(0)));
            repository.save(new Customer("Chloe", "O'Brian", addresses.get(1)));
            repository.save(new Customer("Kim", "Bauer", addresses.get(2)));
            repository.save(new Customer("David", "Palmer", addresses.get(3)));
            repository.save(new Customer("Michelle", "Dessler", addresses.get(4)));
            repository.save(new Customer("Jack", "Bauer", addresses.get(5)));
            repository.save(new Customer("Jack", "Bauer", addresses.get(0)));
            repository.save(new Customer("Chloe", "O'Brian", addresses.get(1)));
            repository.save(new Customer("Kim", "Bauer", addresses.get(2)));
            repository.save(new Customer("David", "Palmer", addresses.get(3)));
            repository.save(new Customer("Michelle", "Dessler", addresses.get(4)));
            repository.save(new Customer("Jack", "Bauer", addresses.get(5)));

            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Customer customer : repository.findAll()) {
                log.info(customer.toString());
            }
            log.info("");

            // fetch all addresses
            log.info("Addresses found with findAll():");
            log.info("-------------------------------");
            for (Address address : addressRepository.findAll()) {
                log.info(address.toString());
            }
            log.info("");


            // fetch an individual customer by ID
            Customer customer = repository.findById(1L);
            log.info("Customer found with findById(1L):");
            log.info("--------------------------------");
            log.info(customer.getAddress().toString());
            log.info("");

            // fetch customers by last name
            log.info("Customer found with findByLastName('Bauer'):");
            log.info("--------------------------------------------");
            repository.findByLastName("Bauer").forEach(bauer -> log.info(bauer.toString()));
            // for (Customer bauer : repository.findByLastName("Bauer")) {
            // 	log.info(bauer.toString());
            // }
            log.info("");
        };
    }

}
