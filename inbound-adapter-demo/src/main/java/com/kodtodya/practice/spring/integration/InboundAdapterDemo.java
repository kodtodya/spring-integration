package com.kodtodya.practice.spring.integration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath:http-inbound-adapter.xml", "classpath:http-inbound-gateway.xml"})
public class InboundAdapterDemo {

	public static void main(String[] args) {
		SpringApplication.run(InboundAdapterDemo.class, args);
	}
}
