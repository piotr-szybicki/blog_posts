package com.example.dealership.query;

import com.example.dealership.query.datamodel.CarOfferDetailsDTO;
import com.example.dealership.query.datamodel.CarOfferQuickDescriptionDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CarOffers {

    Flux<CarOfferQuickDescriptionDTO> availableOffers();

    Mono<CarOfferDetailsDTO> carDetails(String id);

}

