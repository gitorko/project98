package com.demo.project98.controller;

import com.demo.project98.domain.Country;
import com.demo.project98.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;

    @GetMapping("/country/{code}")
    public Country getCountry(@PathVariable String code) {
        return countryService.get(code);
    }

    @PutMapping("/country")
    public void saveCountry(@RequestBody Country country) {
        countryService.put(country);
    }

    @DeleteMapping("/country/{code}")
    public void evictCountry(@PathVariable String code) {
        countryService.evict(code);
    }

}
