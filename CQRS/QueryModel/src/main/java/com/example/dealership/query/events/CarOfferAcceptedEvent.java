package com.example.dealership.query.events;

public class CarOfferAcceptedEvent {

    public CarOfferSnapshot payload;

    public CarOfferAcceptedEvent(CarOfferSnapshot payload) {
        this.payload = payload;
    }
}
