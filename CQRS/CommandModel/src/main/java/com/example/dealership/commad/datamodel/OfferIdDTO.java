package com.example.dealership.commad.datamodel;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.UUID;

public class OfferIdDTO {

    @JsonInclude(JsonInclude.Include.ALWAYS)
    public UUID offerId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String message;

    public OfferIdDTO(UUID offerId) {
        this.offerId = offerId;
    }

    public OfferIdDTO(UUID offerId, String message) {
        this.offerId = offerId;
        this.message = message;
    }

    public OfferIdDTO() {
    }
}
