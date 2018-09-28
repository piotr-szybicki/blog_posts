package com.example.dealership.commad.domain.admissions.events.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

@EnableJms
@Configuration
public class MQConfig {
    @Bean
    public JmsListenerContainerFactory<?> queueListenerFactory() {
        return new DefaultJmsListenerContainerFactory();
    }

}
