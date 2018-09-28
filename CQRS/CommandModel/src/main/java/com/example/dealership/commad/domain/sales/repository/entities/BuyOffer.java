package com.example.dealership.commad.domain.sales.repository.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.UUID;

@Entity
public class BuyOffer {

    @Id
    public final UUID id;

    @NotNull
    public final UUID carOfferId;

    @NotNull
    private BigDecimal priceOffered;

    @NotNull
    private Currency currency;

    @NotNull
    private Boolean accepted;

    public BuyOffer() {
        this.id = null;
        this.carOfferId = null;
    }

    public BuyOffer(UUID id, @NotNull UUID carOfferId, @NotNull BigDecimal priceOffered, @NotNull Currency currency, @NotNull Boolean accepted) {
        this.id = id;
        this.carOfferId = carOfferId;
        this.priceOffered = priceOffered;
        this.currency = currency;
        this.accepted = accepted;
    }

    public @NotNull boolean isAccepted() {
        return accepted;
    }
}
