package com.example.dealership.commad.domain.admissions.events.events;

import com.example.dealership.commad.domain.admissions.repository.entites.snapshots.CarOfferSnapshot;

public class CarOfferAcceptedEvent {

    public CarOfferSnapshot payload;

    public CarOfferAcceptedEvent(CarOfferSnapshot payload) {
        this.payload = payload;
    }
}
