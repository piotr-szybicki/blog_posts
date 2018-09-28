package com.example.dealership.commad.domain.admissions.exceptions;

import com.example.dealership.commad.datamodel.exceptions.OfferInvalidException;

public class CarOfferInvalidException extends OfferInvalidException {

    public CarOfferInvalidException(String message) {
        super(message);
    }
}
