package com.kamindo.propertymanagement.config;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.SimpleEventBus;
import org.axonframework.spring.messaging.unitofwork.SpringTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class AxonConfig {
    @Bean
    public CommandBus commandBus(PlatformTransactionManager transactionManager) {
        return SimpleCommandBus.builder().transactionManager(new SpringTransactionManager(transactionManager)).build();
    }

    @Bean
    public EventBus eventBus() {
        return SimpleEventBus.builder().build();
    }
}
