package com.demo.project98.listener;

import com.demo.project98.domain.Country;
import lombok.extern.slf4j.Slf4j;
import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CountryCacheListener implements CacheEventListener<String, Country> {
    @Override
    public void onEvent(CacheEvent<? extends String, ? extends Country> cacheEvent) {
        log.info("Cache event = {}, Key = {},  Old value = {}, New value = {}", cacheEvent.getType(), cacheEvent.getKey(), cacheEvent.getOldValue(), cacheEvent.getNewValue());
    }
}
