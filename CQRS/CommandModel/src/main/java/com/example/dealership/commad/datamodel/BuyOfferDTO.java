package com.example.dealership.commad.datamodel;

import java.math.BigDecimal;
import java.util.UUID;

public class BuyOfferDTO {

    public UUID carOfferId;
    public String currency;
    public BigDecimal priceOffered;

    public BuyOfferDTO() {}

    public BuyOfferDTO(UUID carOfferId, String currency, BigDecimal priceOffered) {
        this.carOfferId = carOfferId;
        this.currency = currency;
        this.priceOffered = priceOffered;
    }
}
