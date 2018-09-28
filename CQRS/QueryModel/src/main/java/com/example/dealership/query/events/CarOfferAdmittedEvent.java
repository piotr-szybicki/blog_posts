package com.example.dealership.query.events;

public class CarOfferAdmittedEvent {

    public CarOfferSnapshot payload;

    public CarOfferAdmittedEvent() {
    }

    public CarOfferAdmittedEvent(CarOfferSnapshot payload) {
        this.payload = payload;
    }


}
