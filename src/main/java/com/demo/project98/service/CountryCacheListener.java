package com.demo.project98.service;

import com.demo.project98.domain.Country;
import lombok.extern.slf4j.Slf4j;
import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;

@Slf4j
public class CountryCacheListener implements CacheEventListener<String, Country> {
    @Override
    public void onEvent(CacheEvent<? extends String, ? extends Country> event) {
        log.info("Event '{}' fired for key '{}' with value {}", event.getType(), event.getKey(), event.getNewValue());
    }
}
