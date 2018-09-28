package com.example.dealership.commad.domain.sales;

import com.example.dealership.commad.datamodel.BuyOfferDTO;
import com.example.dealership.commad.datamodel.OfferIdDTO;

public interface BuyOfferService {
    OfferIdDTO admitOrRejectNewOffer(BuyOfferDTO newOffer);
}