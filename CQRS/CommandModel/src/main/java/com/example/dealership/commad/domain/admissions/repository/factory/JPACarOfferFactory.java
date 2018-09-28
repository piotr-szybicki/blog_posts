package com.example.dealership.commad.domain.admissions.repository.factory;

import com.example.dealership.commad.datamodel.NewCarOfferDTO;
import com.example.dealership.commad.domain.admissions.exceptions.CarOfferInvalidException;
import com.example.dealership.commad.domain.admissions.repository.entites.CarOffer;

public interface JPACarOfferFactory {
    CarOffer createCarOfThrowExceptionIfSubmissonInvalid(NewCarOfferDTO newCar) throws CarOfferInvalidException;
}
