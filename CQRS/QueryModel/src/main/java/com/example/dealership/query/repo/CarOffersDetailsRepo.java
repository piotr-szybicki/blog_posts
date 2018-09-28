package com.example.dealership.query.repo;

import com.example.dealership.query.datamodel.CarOfferDetailsDTO;
import com.example.dealership.query.datamodel.CarOfferQuickDescriptionDTO;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarOffersDetailsRepo extends ReactiveMongoRepository<CarOfferDetailsDTO, String> {}
