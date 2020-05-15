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
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public final class SpringIntegrationJdbcDemo {

	public static void main(final String... args) {

		final AbstractApplicationContext context =
				new ClassPathXmlApplicationContext("classpath:META-INF/spring/integration/*-context.xml");

		context.registerShutdownHook();

		final TrainingService trainingService = context.getBean(TrainingService.class);
		final InputUtil util = context.getBean(InputUtil.class);
		util.startIntegrationToHandleTrainings(trainingService);

		context.close();

	}


}
