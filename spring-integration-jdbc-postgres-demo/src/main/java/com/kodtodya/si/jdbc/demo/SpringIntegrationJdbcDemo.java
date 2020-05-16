/*
 * Copyright 2002-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.kodtodya.si.jdbc.demo;

import com.kodtodya.si.jdbc.demo.service.TrainingService;
import com.kodtodya.si.jdbc.demo.util.InputUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;

@IntegrationComponentScan("com.kodtodya.si.jdbc.demo")
@EnableIntegration
@SpringBootApplication
@ImportResource({"META-INF/spring/integration/spring-integration-context.xml"})
public class SpringIntegrationJdbcDemo implements CommandLineRunner{

	@Autowired
	private ApplicationContext context;

	public static void main(String... args) {
		SpringApplication.run(SpringIntegrationJdbcDemo.class);
	}

	@Override
	public void run(String... args) throws Exception {
		InputUtil util = context.getBean(InputUtil.class);
		util.startIntegrationToHandleTrainings(context.getBean(TrainingService.class));
	}
}
