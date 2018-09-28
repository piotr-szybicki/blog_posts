package com.example.dealership.query.events.listener;

import com.example.dealership.query.CarOffers;
import com.example.dealership.query.datamodel.CarOfferDetailsDTO;
import com.example.dealership.query.datamodel.CarOfferQuickDescriptionDTO;
import com.example.dealership.query.events.CarOfferAdmittedEvent;
import com.example.dealership.query.events.CarOfferSnapshot;
import com.example.dealership.query.repo.CarOffersDetailsRepo;
import com.example.dealership.query.repo.CarOffersRepo;
import com.google.gson.Gson;
import org.assertj.core.api.Assertions;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest({"spring.datasource.driver-class-name=org.h2.Driver",
        "spring.datasource.url=jdbc:h2:mem:db;DB_CLOSE_DELAY=-1",
        "spring.datasource.username=sa",
        "spring.datasource.password=sa",
        "spring.activemq.broker-url=vm://embedded?broker.persistent=false,useShutdownHook=false",
        "spring.activemq.in-memory=true",
        "domain.events.offer.admitted=x1",
        "domain.events.offer.sold=x2"
})
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class CarOfferAdmittedEventListenerTest {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    CarOfferAdmittedEventListener carOfferAdmittedEventListener;

    @MockBean(name="carOffersDetailsRepo")
    private CarOffersDetailsRepo carOffersDetailsRepo;

    @MockBean(name = "carOffersRepo")
    private CarOffersRepo carOffersRepo;

    @Test
    public void canaryTest(){
        assertThat(carOfferAdmittedEventListener).isNotNull();
    }

    @Test
    public void receiveMessage_checkIfSaved(){
        ArgumentCaptor<CarOfferDetailsDTO> carOfferDetailsDTO = ArgumentCaptor.forClass(CarOfferDetailsDTO.class);
        when(carOffersDetailsRepo.save(any(CarOfferDetailsDTO.class))).thenReturn(Mono.just(createCarOfferDetailsDTO()));
        when(carOffersRepo.save(any(CarOfferQuickDescriptionDTO.class))).thenReturn(Mono.just(createCarOfferQuickjDescryption()));

        carOfferAdmittedEventListener.receiveMessage(createCarOfferAddmitedEvent());
        verify(carOffersDetailsRepo).save(carOfferDetailsDTO.capture());

        assertThat(carOfferDetailsDTO.getValue()).isNotNull();
        assertThat(carOfferDetailsDTO.getValue().model).isEqualTo("GLK");
    }

    private CarOfferQuickDescriptionDTO createCarOfferQuickjDescryption() {
        return new CarOfferQuickDescriptionDTO("1", "Mercedes", "CLS 500");
    }

    private CarOfferDetailsDTO createCarOfferDetailsDTO() {
        return new CarOfferDetailsDTO("1", "Mercedes", "CLS 500", null, new BigDecimal(100), "USD");
    }

    private static String createCarOfferAddmitedEvent() {
        CarOfferSnapshot offerSnapshot = new CarOfferSnapshot();
        offerSnapshot.make = "Mercedes";
        offerSnapshot.model = "GLK";
        offerSnapshot.currency = "USD";
        offerSnapshot.price = new BigDecimal(1000000);
        offerSnapshot.dateOfConstruction = "2000-01-01";

        return createJson(offerSnapshot);
    }

    private static String createJson(CarOfferSnapshot offerSnapshot) {
        return new Gson().toJson(new CarOfferAdmittedEvent(offerSnapshot));
    }

    @SpringBootApplication
    @ComponentScan("com.example.dealership.query")
    public static class DummyStarter {
    }

}