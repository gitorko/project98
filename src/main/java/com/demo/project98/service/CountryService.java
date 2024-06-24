package com.demo.project98.service;

import javax.cache.Cache;
import javax.cache.CacheManager;

import com.demo.project98.domain.Country;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CountryService {

    /**
     * Interact directly with CacheManager
     */
    private final CacheManager cacheManager;
    private Cache<String, Country> cache;

    @PostConstruct
    public void postInit() {
        cache = cacheManager.getCache("countryCache");
    }

    public Country get(String code) {
        log.info("Getting country code: {}", code);
        return cache.get(code);
    }

    public void put(Country country) {
        log.info("Adding country: {}", country);
        cache.put(country.getCode(), country);
    }

    public void evict(String code) {
        log.info("Evicting country code: {}", code);
        cache.remove(code);
    }

}
