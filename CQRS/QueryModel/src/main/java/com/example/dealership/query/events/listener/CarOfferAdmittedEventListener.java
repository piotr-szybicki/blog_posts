package com.example.dealership.query.events.listener;

import com.example.dealership.query.CarOffers;
import com.example.dealership.query.datamodel.CarOfferDetailsDTO;
import com.example.dealership.query.datamodel.CarOfferQuickDescriptionDTO;
import com.example.dealership.query.events.CarOfferAdmittedEvent;
import com.example.dealership.query.repo.CarOffersDetailsRepo;
import com.example.dealership.query.repo.CarOffersRepo;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CarOfferAdmittedEventListener {

    @Autowired
    CarOffersRepo carOffersRepo;

    @Autowired
    CarOffersDetailsRepo carOffersDetailsRepo;


    @JmsListener(destination = "${domain.events.offer.admitted}")
    public void receiveMessage(String carOfferAdmittedEvent) {
        Gson gson = new Gson();
        final CarOfferAdmittedEvent offer = gson.fromJson(carOfferAdmittedEvent, CarOfferAdmittedEvent.class);

         carOffersDetailsRepo
                .save(new CarOfferDetailsDTO(offer.payload.id, offer.payload.make, offer.payload.model,
                        offer.payload.dateOfConstruction, offer.payload.price,
                        offer.payload.currency))
                .and(carOffersRepo
                        .save(new CarOfferQuickDescriptionDTO(offer.payload.id, offer.payload.make,
                        offer.payload.model)))
                .subscribe();
    }
}
