package com.demo.project98.service;

import java.util.Optional;

import com.demo.project98.domain.Customer;
import com.demo.project98.repo.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    final CustomerRepository customerRepository;

    /**
     * If return type is Optional<Customer> it will hit the db each time. As the cache is not configured for Optional<Customer>
     */
    @Cacheable(cacheNames = "customerCache", key = "#id", unless = "#result == null")
    public Customer getCustomerById(Long id) {
        log.info("Getting customer {} from db!", id);
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            return customer.get();
        } else {
            return null;
        }
    }

    /**
     * Don't put @Cacheable here as it will load everything into cache
     */
    public Iterable<Customer> getCustomers() {
        log.info("Getting all customers from db!");
        return customerRepository.findAll();
    }

    @CachePut(cacheNames = "customerCache", key = "#result.id")
    public Customer save(Customer customer) {
        log.info("Saving customer {} to db!", customer);
        return customerRepository.save(customer);
    }

    @CacheEvict(cacheNames = "customerCache", key = "#id")
    public void deleteById(Long id) {
        log.info("Deleting customer {} from db!", id);
        customerRepository.deleteById(id);
    }

    /**
     * Will evict all entries in cache
     */
    @CacheEvict(cacheNames = "customerCache", allEntries = true)
    public void evictAll() {
        log.info("evicting all customers from cache");
    }
}
