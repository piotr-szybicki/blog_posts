package com.example.dealership;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
public class Application {

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
