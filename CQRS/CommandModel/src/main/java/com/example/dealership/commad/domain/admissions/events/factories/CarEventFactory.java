package com.example.dealership.commad.domain.admissions.events.factories;


import com.example.dealership.commad.domain.admissions.repository.entites.snapshots.CarOfferSnapshot;

public interface CarEventFactory {
    String createCarAdmitedEvent(CarOfferSnapshot carSnapshot);

    String createCarSoldEvent(CarOfferSnapshot snapshot);
}
