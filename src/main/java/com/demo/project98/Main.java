package com.demo.project98;

import com.demo.project98.domain.Country;
import com.demo.project98.domain.Customer;
import com.demo.project98.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class Main {

    @Autowired
    CacheManager cacheManager;

    @Autowired
    CustomerRepository customerRepository;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner sendData() {
        return args -> {
            Cache cache = cacheManager.getCache("countryCache");
            cache.put("FR", new Country("FR", "France", "Paris"));
            cache.put("US", new Country("US", "United State Of America", "Washington DC"));
            cache.put("IN", new Country("IN", "India", "Delhi"));
            //Since cache is configure to hold only 2 values, one element is auto evicted!

            for (int i = 0; i < 200; i++) {
                customerRepository.save(Customer.builder().name("customer_" + i).phone("phone_" + i).build());
            }
            System.out.println("Seeded!");
        };
    }

}
