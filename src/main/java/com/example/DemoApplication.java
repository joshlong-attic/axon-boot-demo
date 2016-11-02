package com.example;

import org.axonframework.amqp.eventhandling.spring.SpringAMQPPublisher;
import org.axonframework.common.jpa.ContainerManagedEntityManagerProvider;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine;
import org.axonframework.eventsourcing.eventstore.jpa.JpaEventStorageEngine;
import org.axonframework.spring.config.EnableAxon;
import org.axonframework.spring.messaging.unitofwork.SpringTransactionManager;
import org.springframework.amqp.core.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.PlatformTransactionManager;

@EntityScan(basePackageClasses = DemoApplication.class)
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean(initMethod = "start")
	public SpringAMQPPublisher amqpBridge(EventBus eventBus) {
		return new SpringAMQPPublisher(eventBus);
	}

	@Bean
	public Exchange exchange() {
		return ExchangeBuilder.fanoutExchange("Axon.EventBus").build();
	}

	@Bean
	public Queue queue() {
		return QueueBuilder.durable("Events").build();
	}

	@Bean
	public Binding binding(Queue queue, Exchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("").noargs();
	}

}
