package com.demo.project98.service;

import java.math.BigDecimal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NumberService {

    /**
     * @Cacheable results would be stored
     * Condition is that result will be stored only for numbers > 10
     */
    @Cacheable(value = "squareCache", key = "#number", condition = "#number>10")
    public BigDecimal square(Long number) {
        BigDecimal square = BigDecimal.valueOf(number).multiply(BigDecimal.valueOf(number));
        log.info("Square of {} is {}", number, square);
        return square;
    }
}

