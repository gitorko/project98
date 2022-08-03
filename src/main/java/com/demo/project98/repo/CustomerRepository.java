package com.demo.project98.repo;

import com.demo.project98.domain.Customer;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    @Cacheable("customer")
    Iterable<Customer> getCustomerById(Long id);
}
