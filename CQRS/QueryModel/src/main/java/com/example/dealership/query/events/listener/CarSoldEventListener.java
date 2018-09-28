package com.example.dealership.query.events.listener;

import com.example.dealership.query.events.CarOfferAcceptedEvent;
import com.example.dealership.query.events.CarOfferAdmittedEvent;
import com.example.dealership.query.repo.CarOffersDetailsRepo;
import com.example.dealership.query.repo.CarOffersRepo;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class CarSoldEventListener {
    @Autowired
    CarOffersRepo carOffersRepo;

    @Autowired
    CarOffersDetailsRepo carOffersDetailsRepo;

    @JmsListener(destination = "${domain.events.offer.sold}")
    public void receiveMessage(String carOfferAdmittedEvent) {
        Gson gson = new Gson();
        final CarOfferAcceptedEvent offer = gson.fromJson(carOfferAdmittedEvent, CarOfferAcceptedEvent.class);

        carOffersDetailsRepo
                .deleteById(offer.payload.id)
                .and(carOffersRepo.deleteById(offer.payload.id))
                .subscribe();
    }
}
