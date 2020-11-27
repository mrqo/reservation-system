package com.dotissoft.gateway;

import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GatewayApplication implements CommandLineRunner {

	private static Logger logger = LoggerFactory.getLogger(GatewayApplication.class);

	@Autowired
	public Dispatcher dispatcher;
	
	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Override
	public void run(String... args) {
		logger.info("Gateway application running");
		dispatcher.countFreeSpots(Instant.now(), Instant.now());
	}
}
