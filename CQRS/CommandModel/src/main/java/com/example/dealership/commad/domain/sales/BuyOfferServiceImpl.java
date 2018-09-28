package com.example.dealership.commad.domain.sales;

import com.example.dealership.commad.datamodel.BuyOfferDTO;
import com.example.dealership.commad.datamodel.OfferIdDTO;
import com.example.dealership.commad.domain.admissions.CarOfferAdmissionsService;
import com.example.dealership.commad.domain.admissions.repository.entites.snapshots.CarOfferSnapshot;
import com.example.dealership.commad.domain.sales.factory.JPABuyOfferFactory;
import com.example.dealership.commad.domain.sales.repository.entities.BuyOffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BuyOfferServiceImpl implements BuyOfferService {

    @Autowired
    private CarOfferAdmissionsService carOfferAdmissionsService;

    @Autowired
    private JPABuyOfferFactory jpaBuyOfferFactory;

    @Override
    public OfferIdDTO admitOrRejectNewOffer(BuyOfferDTO buyOfferDTO) {
        final CarOfferSnapshot snapshot = carOfferAdmissionsService.snapshot(buyOfferDTO.carOfferId);
        final BuyOffer buyOffer = jpaBuyOfferFactory.createBuyOfferOrThrowException(buyOfferDTO, snapshot);
        if(buyOffer.isAccepted()){
            carOfferAdmissionsService.sellCar(buyOffer.carOfferId);
            return new OfferIdDTO(buyOffer.id);
        } else {
            return new OfferIdDTO(buyOffer.id, "the price was too low, however we notify the seller about your intrest");
        }
    }
}
