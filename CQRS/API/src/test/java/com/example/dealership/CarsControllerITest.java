package com.example.dealership;

import com.example.dealership.commad.datamodel.NewCarOfferDTO;
import com.example.dealership.commad.datamodel.OfferIdDTO;
import com.example.dealership.commad.datamodel.exceptions.OfferInvalidException;
import com.example.dealership.commad.domain.admissions.CarOfferAdmissionsService;
import com.example.dealership.commad.domain.admissions.exceptions.CarOfferInvalidException;
import com.example.dealership.commad.domain.sales.BuyOfferService;
import com.example.dealership.endpoints.CarsController;
import com.example.dealership.handlers.CommandHandler;
import com.example.dealership.handlers.QueryHandler;
import com.example.dealership.query.CarOffers;
import com.example.dealership.query.datamodel.CarOfferQuickDescriptionDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static java.util.Arrays.asList;
import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.reactive.server.WebTestClient.bindToApplicationContext;
import static reactor.core.publisher.Flux.fromIterable;
import static reactor.core.publisher.Mono.just;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {CarsController.class, QueryHandler.class, CommandHandler.class})
@WebFluxTest
public class CarsControllerITest {

    @Autowired
    private ApplicationContext context;

    @MockBean
    private CarOffers carOffers;

    @MockBean
    private CarOfferAdmissionsService carOfferAdmissionsService;

    @MockBean
    private BuyOfferService buyOfferService;

    private WebTestClient webClient;

    @Test
    public void testEndpoint_fetchAllAvailableCars() throws Exception {
        webClient = bindToApplicationContext(context).build();

        when(carOffers.availableOffers())
                .thenReturn(createQuickDescryptionDTO());

        webClient
                .get().uri("/cars")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(CarOfferQuickDescriptionDTO.class)
                .hasSize(1);
    }

    private Flux<CarOfferQuickDescriptionDTO> createQuickDescryptionDTO() {
        return fromIterable(asList(new CarOfferQuickDescriptionDTO("1", "audi", "Q6")));
    }

    @Test
    public void testEndpoint_postNewOffer(){
        webClient = bindToApplicationContext(context).build();

        when(carOfferAdmissionsService.admitOrRejectNewOffer(any(NewCarOfferDTO.class)))
                .thenReturn(createOfferID());

        webClient.post()
                .uri("/car")
                .body(createCarOfferDTO(), NewCarOfferDTO.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(OfferIdDTO.class)
                .consumeWith((t)-> assertThat(t.getResponseBody().offerId).isNotNull())
        ;
    }

    @Test
    public void testEndpoint_postNewOfferWithError(){
        webClient = bindToApplicationContext(context).build();

        when(carOfferAdmissionsService.admitOrRejectNewOffer(any(NewCarOfferDTO.class)))
                .thenThrow(new CarOfferInvalidException("invalid something"));

        webClient.post()
                .uri("/car")
                .body(createCarOfferDTO(), NewCarOfferDTO.class)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(OfferInvalidException.class)
                .consumeWith((t)-> {
                    assertThat(t.getResponseBody()).isNotNull();}
                )
        ;
    }

    private OfferIdDTO createOfferID() {
        return new OfferIdDTO(randomUUID());
    }

    private Mono<NewCarOfferDTO> createCarOfferDTO() {
        return just(new NewCarOfferDTO());
    }

}
