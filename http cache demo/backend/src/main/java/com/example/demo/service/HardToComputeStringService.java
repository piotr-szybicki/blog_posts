package com.example.demo.service;

import com.example.demo.datamodel.CachedObject;

public interface HardToComputeStringService {

    public CachedObject hardToCompute();

    void clearCache();
}
