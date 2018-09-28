package com.example.dealership.handlers;

import com.example.dealership.query.CarOffers;
import com.example.dealership.query.datamodel.CarOfferDetailsDTO;
import com.example.dealership.query.datamodel.CarOfferQuickDescriptionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class QueryHandler {

    @Autowired
    CarOffers carsOffers;

    public Mono<ServerResponse> getAllCarsOffers(ServerRequest request) {
        return  ok()
                .contentType(APPLICATION_JSON)
                .body(fromPublisher(carsOffers.availableOffers(), CarOfferQuickDescriptionDTO.class));
    }

    public Mono<ServerResponse> getDetailsCarOffer(ServerRequest serverRequest) {
        final Mono<CarOfferDetailsDTO> carDetails = carsOffers.carDetails(serverRequest.pathVariable("id"));
        return ok()
                .contentType(APPLICATION_JSON)
                .body(fromPublisher(carDetails, CarOfferDetailsDTO.class));
    }
}
