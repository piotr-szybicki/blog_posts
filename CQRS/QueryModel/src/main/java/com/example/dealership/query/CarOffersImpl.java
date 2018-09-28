package com.example.dealership.query;

import com.example.dealership.query.datamodel.CarOfferDetailsDTO;
import com.example.dealership.query.datamodel.CarOfferQuickDescriptionDTO;
import com.example.dealership.query.repo.CarOffersDetailsRepo;
import com.example.dealership.query.repo.CarOffersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class CarOffersImpl implements CarOffers {

    @Autowired
    CarOffersRepo carOffersRepo;

    @Autowired
    CarOffersDetailsRepo carOffersDetailsRepo;

    @Override
    public Flux<CarOfferQuickDescriptionDTO> availableOffers() {
        return carOffersRepo.findAll();
    }

    @Override
    public Mono<CarOfferDetailsDTO> carDetails(String id) {
        return carOffersDetailsRepo.findById(id);
    }

}
