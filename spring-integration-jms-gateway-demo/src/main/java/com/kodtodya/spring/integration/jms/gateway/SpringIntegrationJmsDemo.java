package com.kodtodya.spring.integration.jms.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.config.EnableIntegration;

@EnableIntegration
@SpringBootApplication
@ImportResource({"/META-INF/spring/integration/common.xml",
        "/META-INF/spring/integration/inboundGateway.xml",
        "/META-INF/spring/integration/outboundGateway.xml"})
public class SpringIntegrationJmsDemo {

    public static void main(String[] args) {
        SpringApplication.run(SpringIntegrationJmsDemo.class, args);

        System.out.println("Starting Spring Integration Gateway Demo >>");
    }

}
