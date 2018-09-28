package com.example.dealership.commad.domain.sales.exceptions;

import com.example.dealership.commad.datamodel.exceptions.OfferInvalidException;

public class BuyOfferInvalidException extends OfferInvalidException {

    public BuyOfferInvalidException(String message) {
        super(message);
    }
}
