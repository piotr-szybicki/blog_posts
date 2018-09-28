package com.example.dealership.commad.domain.admissions.repository.factory;

import com.example.dealership.commad.datamodel.NewCarOfferDTO;
import com.example.dealership.commad.domain.admissions.exceptions.CarOfferInvalidException;
import com.example.dealership.commad.domain.admissions.repository.entites.CarOffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static java.util.Currency.getAvailableCurrencies;
import static java.util.Currency.getInstance;
import static java.util.UUID.randomUUID;

@Component
public class JPACarOfferFactoryImpl implements JPACarOfferFactory {

    Logger logger = LoggerFactory.getLogger(JPACarOfferFactoryImpl.class);

    @Override
    public CarOffer createCarOfThrowExceptionIfSubmissonInvalid(NewCarOfferDTO newCar) throws CarOfferInvalidException {

        if(isCurrencyCodeUnavailable(newCar.currency)){
            logger.error("Error creating car offer (no such currency): " + newCar);
            throw new CarOfferInvalidException("Currency code not supported");
        }

        if(newCar.make.equals("BMW")){
            logger.error("Error creating car offer: (We do not accept BMW): " + newCar);
            throw new CarOfferInvalidException("We do not accept BMW");
        }


        final CarOffer carOffer = new CarOffer(randomUUID(), newCar.make, newCar.model, newCar.dateOfConstruction, newCar.price, getInstance(newCar.currency), Boolean.FALSE);
        logger.info("Car offer created: " + carOffer);

        return carOffer;
    }

    private static boolean isCurrencyCodeUnavailable(String currency) {
        return !getAvailableCurrencies()
                .stream()
                .anyMatch((currencyCode)-> currencyCode.getCurrencyCode()
                                                .equals(currency));
    }

}
