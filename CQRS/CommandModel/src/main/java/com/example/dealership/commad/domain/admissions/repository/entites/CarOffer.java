package com.example.dealership.commad.domain.admissions.repository.entites;

import com.example.dealership.commad.datamodel.exceptions.OfferInvalidException;
import com.example.dealership.commad.domain.admissions.repository.entites.snapshots.CarOfferSnapshot;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;
import java.util.UUID;

import static java.lang.Boolean.TRUE;
import static javax.persistence.TemporalType.DATE;

@Entity
public class CarOffer {

    @Id
    public final UUID id;

    @NotNull
    private String make;

    @NotNull
    private String model;

    @NotNull
    @Temporal(DATE)
    private Date dateOfConstruction;

    @NotNull
    private BigDecimal price;

    @NotNull
    private Currency currency;

    @NotNull
    private Boolean sold;

    public CarOffer() {
        this.id = null;
    }

    public CarOffer(UUID id) {
        this.id = id;
    }

    public CarOffer(UUID id, @NotNull String make, @NotNull String model, @NotNull Date dateOfConstruction, @NotNull BigDecimal price, @NotNull Currency currency, Boolean sold) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.dateOfConstruction = dateOfConstruction;
        this.price = price;
        this.currency = currency;
        this.sold = sold;
    }

    public CarOfferSnapshot snapshot() {
        return new CarOfferSnapshot(id.toString(), make, model, dateOfConstruction, price, currency, sold );
    }

    @Override
    public String toString() {
        return "CarOffer{" +
                "id=" + id +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", dateOfConstruction=" + dateOfConstruction +
                ", price=" + price +
                ", currency=" + currency +
                ", sold=" + sold +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarOffer car = (CarOffer) o;

        return id.equals(car.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public void sell() {
        if(this.sold){
            throw new OfferInvalidException("the car has been sold");
        }
        this.sold = TRUE;
    }
}
