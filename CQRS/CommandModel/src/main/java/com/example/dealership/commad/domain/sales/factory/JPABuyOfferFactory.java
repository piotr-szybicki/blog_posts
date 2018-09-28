package com.example.dealership.commad.domain.sales.factory;

import com.example.dealership.commad.datamodel.BuyOfferDTO;
import com.example.dealership.commad.domain.admissions.repository.entites.snapshots.CarOfferSnapshot;
import com.example.dealership.commad.domain.sales.repository.entities.BuyOffer;

public interface JPABuyOfferFactory {

    BuyOffer createBuyOfferOrThrowException(BuyOfferDTO buyOfferDTO, CarOfferSnapshot snapshot);
}
