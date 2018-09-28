package com.example.dealership.query.datamodel;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "CarOffersDetails")
public class CarOfferDetailsDTO {

    @Id
    public final String id;
    public final String make;
    public final String model;
    public final String yearOfProduction;
    public final BigDecimal price;
    public final String currency;

    public CarOfferDetailsDTO(String id, String make, String model, String yearOfProduction, BigDecimal price, String currency) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.yearOfProduction = yearOfProduction;
        this.price = price;
        this.currency = currency;
    }
}
