package com.example.dealership.commad.domain.admissions.repository;

import com.example.dealership.commad.domain.admissions.repository.entites.CarOffer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest({
        "spring.datasource.driver-class-name=org.h2.Driver",
        "spring.datasource.url=jdbc:h2:mem:db;DB_CLOSE_DELAY=-1",
        "spring.datasource.username=sa",
        "spring.datasource.password=sa"
})
public class CarOfferRepositoryITest {
    @Autowired
    private CarOfferRepository genericEntityRepository;

    @Test //--add-modules java.xml.bind
    public void givenGenericEntityRepository_whenSaveAndRetreiveEntity_thenOK() {
        //given
        final UUID id = UUID.randomUUID();
        final CarOffer car = new CarOffer(id);

        //when
        final CarOffer carSaved = genericEntityRepository.save(car);
        Optional<CarOffer> foundEntity = genericEntityRepository.findById(id);

        //then
        assertThat(foundEntity).isNotEmpty();
        assertThat(foundEntity.get()).isEqualTo(car);
    }

    @SpringBootApplication
    public static class DummyStarter {
    }
}