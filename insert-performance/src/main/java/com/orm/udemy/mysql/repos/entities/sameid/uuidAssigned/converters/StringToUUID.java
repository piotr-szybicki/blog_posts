package com.orm.udemy.mysql.repos.entities.sameid.uuidAssigned.converters;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.util.UUID;

public class StringToUUID extends StdConverter<String, UUID> {

    @Override
    public UUID convert(String value) {
        return UUID.fromString(value);
    }
}
