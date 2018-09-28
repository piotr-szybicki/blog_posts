package com.example.dealership.commad.domain.admissions;

import com.example.dealership.commad.datamodel.OfferIdDTO;
import com.example.dealership.commad.datamodel.NewCarOfferDTO;
import com.example.dealership.commad.domain.admissions.exceptions.CarOfferInvalidException;
import com.example.dealership.commad.domain.admissions.exceptions.CarOfferNonExistentException;
import com.example.dealership.commad.domain.admissions.repository.entites.snapshots.CarOfferSnapshot;

import java.util.UUID;

public interface CarOfferAdmissionsService {
    OfferIdDTO admitOrRejectNewOffer(NewCarOfferDTO newCar) throws CarOfferInvalidException;

    CarOfferSnapshot snapshot(UUID id) throws CarOfferNonExistentException;

    void sellCar(UUID carOfferId);
}
