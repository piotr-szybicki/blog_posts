package com.example.dealership.endpoints;

import com.example.dealership.handlers.CommandHandler;
import com.example.dealership.handlers.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class CarsController {

    @Autowired
    QueryHandler queryHandler;

    @Autowired
    CommandHandler commandHandler;

    @Bean
    public RouterFunction<ServerResponse> route() {
        return RouterFunctions
                .route(GET("/cars") ,queryHandler::getAllCarsOffers)
                .andRoute(GET("/car/{id}") ,queryHandler::getDetailsCarOffer)
                .andRoute(POST("/car"), commandHandler::addNewCarOffer)
                .andRoute(POST("/order"), commandHandler::buyThatCar);
    }
}
