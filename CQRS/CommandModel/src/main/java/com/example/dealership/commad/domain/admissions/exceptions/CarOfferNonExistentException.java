package com.example.dealership.commad.domain.admissions.exceptions;

import com.example.dealership.commad.datamodel.exceptions.OfferInvalidException;

public class CarOfferNonExistentException extends OfferInvalidException {

    public CarOfferNonExistentException(String message) {
        super(message);
    }
}
