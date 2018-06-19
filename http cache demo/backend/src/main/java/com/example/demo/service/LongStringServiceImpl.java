package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@CacheConfig(cacheNames = "regularly-changed-value-cache")
public class LongStringServiceImpl implements LongStringService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LongStringServiceImpl.class.getName());

    @CacheEvict(allEntries = true)
    public void clearCache(){}

    @Override
    @Cacheable
    public String changingIrregularly() {
        LOGGER.info("this should be called every time the modthod clear Cache is called");
        return "big string, it rerly changes like a big dictionary so we put it in the cache. " + UUID.randomUUID().toString();
    }

}
