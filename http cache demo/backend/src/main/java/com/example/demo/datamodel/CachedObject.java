package com.example.demo.datamodel;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import static java.time.LocalDateTime.now;
import static java.time.temporal.ChronoUnit.SECONDS;

public class CachedObject implements Serializable {
    private final String uuid;
    private final LocalDateTime expirationTime;

    public CachedObject(){
        this.uuid = UUID.randomUUID().toString();
        expirationTime = now().plusSeconds(10);
    }

    public long validInSeconds() {
        return SECONDS.between(now(), expirationTime);
    }

    public String payload() {
        return uuid;
    }
}
