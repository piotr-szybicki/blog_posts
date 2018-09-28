package com.example.dealership.commad.domain.admissions;

import com.example.dealership.commad.datamodel.NewCarOfferDTO;
import com.example.dealership.commad.datamodel.OfferIdDTO;
import com.example.dealership.commad.datamodel.exceptions.OfferInvalidException;
import com.example.dealership.commad.domain.admissions.events.factories.CarEventFactory;
import com.example.dealership.commad.domain.admissions.exceptions.CarOfferInvalidException;
import com.example.dealership.commad.domain.admissions.repository.CarOfferRepository;
import com.example.dealership.commad.domain.admissions.repository.entites.CarOffer;
import com.example.dealership.commad.domain.admissions.repository.entites.snapshots.CarOfferSnapshot;
import com.example.dealership.commad.domain.admissions.repository.factory.JPACarOfferFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class CarOfferAdmissionsServiceImpl implements CarOfferAdmissionsService {

    @Autowired
    CarOfferRepository carRepository;

    @Autowired
    JPACarOfferFactory jpaCarOfferFactory;

    @Autowired
    CarEventFactory carEventFactory;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${domain.events.offer.admitted}")
    private String carOfferAdmittedQueue;

    @Value("${domain.events.offer.sold}")
    private String carSoldQueue;

    @Override
    public OfferIdDTO admitOrRejectNewOffer(NewCarOfferDTO newCarOfferDTO) throws CarOfferInvalidException {
        CarOffer newCarOffer = jpaCarOfferFactory.createCarOfThrowExceptionIfSubmissonInvalid(newCarOfferDTO);
        carRepository.save(newCarOffer);
        jmsTemplate.convertAndSend(carOfferAdmittedQueue, carEventFactory.createCarAdmitedEvent(newCarOffer.snapshot()));
        return new OfferIdDTO(newCarOffer.id);
    }

    @Override
    public CarOfferSnapshot snapshot(UUID carOfferId) {
        final Optional<CarOffer> carOffer = carRepository.findById(carOfferId);
        if(carOffer.isPresent()){
            return carOffer.get().snapshot();
        } else {
            throw new OfferInvalidException("the car offer do not exist.");
        }
    }

    @Override
    public void sellCar(UUID carOfferId) {
        final CarOffer carOffer = carRepository.findById(carOfferId).get();
        carOffer.sell();
        carRepository.save(carOffer);
        jmsTemplate.convertAndSend(carSoldQueue, carEventFactory.createCarSoldEvent(carOffer.snapshot()));
    }
}
