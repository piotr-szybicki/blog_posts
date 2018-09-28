package com.example.dealership.commad.datamodel;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class NewCarOfferDTO {

    public String make;
    public String model;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public Date dateOfConstruction;
    public BigDecimal price;
    public String currency;

    public NewCarOfferDTO() {
    }

    @Override
    public String toString() {
        return "NewCarOfferDTO{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", dateOfConstruction=" + dateOfConstruction +
                ", price=" + price +
                ", currency='" + currency + '\'' +
                '}';
    }
}
