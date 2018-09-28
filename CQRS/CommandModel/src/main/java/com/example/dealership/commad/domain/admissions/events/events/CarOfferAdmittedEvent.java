package com.example.dealership.commad.domain.admissions.events.events;

import com.example.dealership.commad.domain.admissions.repository.entites.snapshots.CarOfferSnapshot;

public class CarOfferAdmittedEvent {

    public CarOfferSnapshot payload;

    public CarOfferAdmittedEvent() {
    }

    public CarOfferAdmittedEvent(CarOfferSnapshot payload) {
        this.payload = payload;
    }


}
