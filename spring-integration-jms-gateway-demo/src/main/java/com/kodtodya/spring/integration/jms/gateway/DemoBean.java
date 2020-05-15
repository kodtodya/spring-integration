package com.kodtodya.spring.integration.jms.gateway;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;

@MessageEndpoint
public class DemoBean {

	@ServiceActivator
	public String process(String input) {
		System.out.println("JMS response: " + input);
		return "JMS response: " + input;
	}
}
