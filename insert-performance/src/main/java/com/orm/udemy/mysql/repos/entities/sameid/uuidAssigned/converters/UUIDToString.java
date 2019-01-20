package com.orm.udemy.mysql.repos.entities.sameid.uuidAssigned.converters;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.util.UUID;

public class UUIDToString extends StdConverter<UUID, String> {
    @Override
    public String convert(UUID value) {
        return value.toString();
    }
}
