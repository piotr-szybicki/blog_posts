package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class WeCantCacheThisStringServiceImpl implements WeCantCacheThisStringService {


    @Override
    public String nonCachableString() {
        return UUID.randomUUID().toString();
    }
}
