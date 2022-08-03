package com.demo.project98.service;

import com.demo.project98.domain.Country;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CountryCacheEvict {

    @CacheEvict(cacheNames = "countryCache", key = "#country.code")
    public void evictSingleCacheValue(Country country) {
        log.info("Evicting from cache: {}", country);
    }
}
