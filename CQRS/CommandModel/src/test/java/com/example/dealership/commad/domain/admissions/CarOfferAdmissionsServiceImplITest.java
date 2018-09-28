package com.example.dealership.commad.domain.admissions;

import com.example.dealership.commad.datamodel.NewCarOfferDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;

import static java.time.LocalDateTime.now;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest({
        "spring.datasource.driver-class-name=org.h2.Driver",
        "spring.datasource.url=jdbc:h2:mem:db;DB_CLOSE_DELAY=-1",
        "spring.datasource.username=sa",
        "spring.datasource.password=sa",
        "spring.activemq.broker-url=vm://embedded?broker.persistent=false,useShutdownHook=false",
        "spring.activemq.in-memory=true",
        "domain.events.offer.admitted=offer_admitted_highway"
})
public class CarOfferAdmissionsServiceImplITest {

    @Autowired
    private CarOfferAdmissionsService carOfferAdmissionsService;

    @Test //--add-modules java.xml.bind
    public void givenGenericEntityRepository_whenSaveAndRetreiveEntity_thenOK() throws InterruptedException {
        assertThat(carOfferAdmissionsService).isNotNull();
        carOfferAdmissionsService.admitOrRejectNewOffer(createNewCar());
    }

    private NewCarOfferDTO createNewCar() {
        final NewCarOfferDTO newCarDTO = new NewCarOfferDTO();

        newCarDTO.price = new BigDecimal(100);
        newCarDTO.currency = "USD";
        newCarDTO.make = "Audi";
        newCarDTO.model = "R8";
        newCarDTO.dateOfConstruction = new Date();

        return newCarDTO;
    }

    @SpringBootApplication
    @EnableJms
    public static class DummyStarter {
    }

}