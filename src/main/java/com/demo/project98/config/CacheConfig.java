package com.demo.project98.config;

import static org.ehcache.config.builders.CacheEventListenerConfigurationBuilder.newEventListenerConfiguration;
import static org.ehcache.event.EventType.CREATED;
import static org.ehcache.event.EventType.EXPIRED;
import static org.ehcache.event.EventType.REMOVED;
import static org.ehcache.event.EventType.UPDATED;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Arrays;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.spi.CachingProvider;

import com.demo.project98.domain.Country;
import com.demo.project98.domain.Customer;
import com.demo.project98.listener.CountryCacheListener;
import lombok.RequiredArgsConstructor;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.impl.config.event.DefaultCacheEventListenerConfiguration;
import org.ehcache.jsr107.Eh107Configuration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
@RequiredArgsConstructor
public class CacheConfig {

    private final CountryCacheListener listener;

    @Bean
    public CacheManager echCacheManager() {
        CachingProvider cachingProvider = Caching.getCachingProvider();
        CacheManager cacheManager = cachingProvider.getCacheManager();
        cacheManager.createCache("customerCache", customerCacheConfig());
        cacheManager.createCache("countryCache", countryCacheConfig());
        cacheManager.createCache("squareCache", squareCacheConfig());
        return cacheManager;
    }

    private javax.cache.configuration.Configuration<Long, Customer> customerCacheConfig() {
        CacheConfiguration<Long, Customer> cacheConfig = CacheConfigurationBuilder
                .newCacheConfigurationBuilder(Long.class, Customer.class,
                        ResourcePoolsBuilder.newResourcePoolsBuilder()
                                .heap(10)
                                .offheap(10, MemoryUnit.MB)
                                .build())
                .withExpiry(ExpiryPolicyBuilder.timeToIdleExpiration(Duration.ofSeconds(10)))
                .build();
        javax.cache.configuration.Configuration<Long, Customer> configuration = Eh107Configuration.fromEhcacheCacheConfiguration(cacheConfig);
        return configuration;
    }

    private javax.cache.configuration.Configuration<String, Country> countryCacheConfig() {
        CacheConfiguration<String, Country> cacheConfig = CacheConfigurationBuilder
                .newCacheConfigurationBuilder(String.class, Country.class,
                        ResourcePoolsBuilder.newResourcePoolsBuilder()
                                .heap(10)
                                .offheap(10, MemoryUnit.MB)
                                .build())
                .withExpiry(ExpiryPolicyBuilder.timeToIdleExpiration(Duration.ofSeconds(10)))
                .withService(getCacheEventListener())
                .build();
        javax.cache.configuration.Configuration<String, Country> configuration = Eh107Configuration.fromEhcacheCacheConfiguration(cacheConfig);
        return configuration;
    }

    private javax.cache.configuration.Configuration<Long, BigDecimal> squareCacheConfig() {
        CacheConfiguration<Long, BigDecimal> cacheConfig = CacheConfigurationBuilder
                .newCacheConfigurationBuilder(Long.class, BigDecimal.class,
                        ResourcePoolsBuilder.newResourcePoolsBuilder()
                                .heap(10)
                                .offheap(10, MemoryUnit.MB)
                                .build())
                .build();
        javax.cache.configuration.Configuration<Long, BigDecimal> configuration = Eh107Configuration.fromEhcacheCacheConfiguration(cacheConfig);
        return configuration;
    }

    private DefaultCacheEventListenerConfiguration getCacheEventListener() {
        return newEventListenerConfiguration(listener, CREATED, UPDATED, EXPIRED, REMOVED)
                .asynchronous()
                .unordered()
                .build();
    }

    /**
     * Use when no configurations.
     */
    public SimpleCacheManager simpleEhCacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(new ConcurrentMapCache("customerCache"), new ConcurrentMapCache("countryCache")));
        return cacheManager;
    }

}