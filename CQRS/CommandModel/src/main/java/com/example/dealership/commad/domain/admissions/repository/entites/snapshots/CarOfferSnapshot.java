package com.example.dealership.commad.domain.admissions.repository.entites.snapshots;

import java.math.BigDecimal;

import java.util.Currency;
import java.util.Date;

public class CarOfferSnapshot {

    public String id;
    public String make;
    public String model;
    public String dateOfConstruction;
    public BigDecimal price;
    public String currency;
    public Boolean sold;

    public CarOfferSnapshot(String id, String make, String model, Date dateOfConstruction, BigDecimal price, Currency currency, Boolean sold) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.dateOfConstruction = dateOfConstruction.toString();
        this.price = price;
        this.currency = currency.getCurrencyCode();
        this.sold = sold;
    }
}
