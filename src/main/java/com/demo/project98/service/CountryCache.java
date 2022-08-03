package com.demo.project98.service;

import com.demo.project98.domain.Country;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CountryCache {

    /**
     * NOTE: Never create a local HashMap to key those values, as it can cause memory overflow without eviction.
     *
     * @Cacheable - If key is present returns the object, no update happens.
     * @CachePut - If key is present it will update and then return object.
     */
    @Cacheable(cacheNames = "countryCache", key = "#country.code")
    public Country get(Country country) {
        log.info("Getting country name: {}", country.getCode());
        return country;
    }

    @CachePut(cacheNames = "countryCache", key = "#country.code")
    public Country put(Country country) {
        log.info("Adding country: {}", country);
        return country;
    }

}
