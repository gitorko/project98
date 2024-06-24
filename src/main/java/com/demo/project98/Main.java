package com.demo.project98;

import javax.cache.Cache;
import javax.cache.CacheManager;

import com.demo.project98.domain.Country;
import com.demo.project98.domain.Customer;
import com.demo.project98.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class Main {

    final CacheManager cacheManager;
    final CustomerService customerService;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner sendData() {
        return (args) -> {
            log.info("Seeding test data!");
            Cache cache = cacheManager.getCache("countryCache");
            cache.put("FR", new Country("FR", "France", "Paris"));
            cache.put("US", new Country("US", "United States Of America", "Washington DC"));
            cache.put("IN", new Country("IN", "India", "Delhi"));
            for (int i = 0; i < 200; i++) {
                customerService.save(Customer.builder()
                        .name("customer_" + i)
                        .city("city_" + i)
                        .build());
            }
            log.info("Seeding completed!");
        };
    }

}
