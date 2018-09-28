package com.example.dealership.handlers;

import com.example.dealership.commad.datamodel.BuyOfferDTO;
import com.example.dealership.commad.datamodel.OfferIdDTO;
import com.example.dealership.commad.datamodel.exceptions.OfferInvalidException;
import com.example.dealership.commad.domain.admissions.CarOfferAdmissionsService;
import com.example.dealership.commad.datamodel.NewCarOfferDTO;
import com.example.dealership.commad.domain.sales.BuyOfferService;
import com.example.dealership.commad.domain.sales.exceptions.BuyOfferInvalidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;
import static org.springframework.web.reactive.function.server.ServerResponse.badRequest;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class CommandHandler {

    @Autowired
    CarOfferAdmissionsService carOfferAdmissionsService;

    @Autowired
    BuyOfferService buyOfferService;

    public Mono<ServerResponse> addNewCarOffer(ServerRequest serverRequest) {
        return serverRequest
                .bodyToMono(NewCarOfferDTO.class)
                .map((newCar) -> carOfferAdmissionsService.admitOrRejectNewOffer(newCar))
                .flatMap((offerID) -> createResponseWithOfferID(offerID))
                .onErrorResume(exception -> createExceptionResponse(exception));
    }

    public Mono<ServerResponse> buyThatCar(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(BuyOfferDTO.class)
                .map((newOffer) -> buyOfferService.admitOrRejectNewOffer(newOffer))
                .flatMap((offerID) -> createResponseWithOfferID(offerID))
                .onErrorResume(exception -> createExceptionResponse(exception));
    }

    private static Mono<ServerResponse> createExceptionResponse(Throwable exception) {
        return badRequest()
                .body(fromObject(((OfferInvalidException) exception).jsonException));
    }

    private static Mono<ServerResponse> createResponseWithOfferID(OfferIdDTO offerID){
        return ok()
                .contentType(APPLICATION_JSON)
                .body(fromObject(offerID));
    }

}
