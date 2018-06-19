package com.example.demo.service;

import com.example.demo.datamodel.CachedObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "irregularly-changed-value-cache")
public class HardToComputeStringServiceImpl implements HardToComputeStringService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LongStringServiceImpl.class.getName());

    @CacheEvict(allEntries = true)
    public void clearCache(){}

    @Override
    @Cacheable
    public CachedObject hardToCompute() {
        CachedObject cachedObject = new CachedObject();
        LOGGER.info("this is called every time the resource changes: " + cachedObject.payload());
        return cachedObject;
    }

}
