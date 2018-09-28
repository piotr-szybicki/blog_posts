package com.example.dealership.query.repo;

import com.example.dealership.query.datamodel.CarOfferDetailsDTO;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

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
public class CarOffersDetailsRepoITest {

    public static final CarOfferDetailsDTO CAR_OFFER_DETAILS_DTO = new CarOfferDetailsDTO("1", "BMW",
                                "360d", "2000-01-01", new BigDecimal(1), "USD");
    @Autowired
    CarOffersDetailsRepo carOffersDetailsRepo;

    @Test
    public void canaryTest() {
        assertThat(carOffersDetailsRepo).isNotNull();
    }

    @Test
    public void save_checkIfValuedSavedCorrectly(){
        final Flux<CarOfferDetailsDTO> car = carOffersDetailsRepo.findAll();

        StepVerifier.create(car)
                .expectSubscription()
                .assertNext((carFromDB) -> Assertions.assertThat(carFromDB.id).isEqualTo("1"))
                .verifyComplete();
    }

    @Before
    public void saveSomeValues() {
        carOffersDetailsRepo.save(CAR_OFFER_DETAILS_DTO).subscribe();
    }

    @SpringBootApplication
    public static class DummyStarter {
    }

}
