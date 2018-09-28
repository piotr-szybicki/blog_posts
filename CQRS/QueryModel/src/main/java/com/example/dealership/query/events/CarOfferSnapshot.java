package com.example.dealership.query.events;

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

    public CarOfferSnapshot() {
    }

}
