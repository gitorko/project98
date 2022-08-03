package com.demo.project98.controller;

import com.demo.project98.domain.Country;
import com.demo.project98.domain.Customer;
import com.demo.project98.repo.CustomerRepository;
import com.demo.project98.service.CountryCache;
import com.demo.project98.service.CountryCacheEvict;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HomeController {

    private final CountryCache countryCache;
    private final CountryCacheEvict countryCacheEvict;

    private final CustomerRepository customerRepository;

    @GetMapping("/country-get/{code}")
    public Country getCountryCode(@PathVariable String code) {
        //Here only fetch happens, so capital value doesnt matter.
        return countryCache.get(Country.builder().code(code).build());
    }

    @GetMapping("/country-put/{code}")
    public Country putCountryCode(@PathVariable String code) {
        //Here update & fetch happens, so capital value matters.
        return countryCache.put(Country.builder().code(code).capital("UNKNOWN").build());
    }

    @DeleteMapping("/country/{code}")
    public void evictCountry(@PathVariable String code) {
        countryCacheEvict.evictSingleCacheValue(Country.builder().code(code).build());
    }

    @GetMapping("/customer/{id}")
    public Iterable<Customer> getCustomerByPhone(@PathVariable Long id) {
        return customerRepository.getCustomerById(id);
    }
}
