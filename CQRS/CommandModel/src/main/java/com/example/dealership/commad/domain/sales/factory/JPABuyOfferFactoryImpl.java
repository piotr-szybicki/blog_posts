package com.example.dealership.commad.domain.sales.factory;

import com.example.dealership.commad.datamodel.BuyOfferDTO;
import com.example.dealership.commad.domain.admissions.repository.entites.snapshots.CarOfferSnapshot;
import com.example.dealership.commad.domain.sales.exceptions.BuyOfferInvalidException;
import com.example.dealership.commad.domain.sales.repository.entities.BuyOffer;
import org.springframework.stereotype.Component;

import static java.util.Currency.getInstance;
import static java.util.UUID.randomUUID;

@Component
public class JPABuyOfferFactoryImpl implements JPABuyOfferFactory {

    @Override
    public BuyOffer createBuyOfferOrThrowException(BuyOfferDTO buyOfferDTO, CarOfferSnapshot snapshot) {


        if(snapshot.sold){
            throw new BuyOfferInvalidException("car has been sold");
        }

        if(areCurrenciesDifferent(buyOfferDTO, snapshot)){
            throw new BuyOfferInvalidException("offer placed in the wrong currency");
        }

        if(snapshot.price.compareTo(buyOfferDTO.priceOffered) <= 0){
            BuyOffer buyOffer = new BuyOffer(randomUUID(), buyOfferDTO.carOfferId, buyOfferDTO.priceOffered, getInstance(buyOfferDTO.currency), Boolean.TRUE);
            return buyOffer;
        } else {
            BuyOffer buyOffer = new BuyOffer(randomUUID(), buyOfferDTO.carOfferId, buyOfferDTO.priceOffered, getInstance(buyOfferDTO.currency), Boolean.FALSE);
            return buyOffer;
        }
    }

    private static boolean areCurrenciesDifferent(BuyOfferDTO buyOfferDTO, CarOfferSnapshot snapshot) {
        return !snapshot.currency.equals(buyOfferDTO.currency);
    }
}
