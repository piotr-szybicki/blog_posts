package com.example.dealership.commad.domain.admissions.events.factories;

import com.example.dealership.commad.domain.admissions.events.events.CarOfferAcceptedEvent;
import com.example.dealership.commad.domain.admissions.events.events.CarOfferAdmittedEvent;
import com.example.dealership.commad.domain.admissions.repository.entites.snapshots.CarOfferSnapshot;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

@Component
public class CarEventFactoryImpl implements CarEventFactory {

    @Override
    public String createCarAdmitedEvent(CarOfferSnapshot snapshot) {
        Gson gson = new Gson();
        return gson.toJson(new CarOfferAdmittedEvent(snapshot));
    }

    @Override
    public String createCarSoldEvent(CarOfferSnapshot snapshot) {
        Gson gson = new Gson();
        return gson.toJson(new CarOfferAcceptedEvent(snapshot));
    }
}
