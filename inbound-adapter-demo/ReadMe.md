# Inbound Adapter Demo (Spring Integration)

## Steps to setup

- Download code and build it. This is simple `Maven/Spring Boot` project with `Spring Integration` capabilities.

## Understanding of this demo
### Let's understand this example file by file

- `pom.xml` - contains 2 dependencies for spring boot starter and 2 dependencies for Spring Integration as below:
```
 <!--Spring Boot starter pack dependencies -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>
         
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!--Spring Boot starter pack dependencies ends here -->

<!--Spring Integration starter pack dependencies -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-integration</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.integration</groupId>
            <artifactId>spring-integration-http</artifactId>
        </dependency>
        <!--Spring Integration starter pack dependencies ends here -->
```

- `InboundAdapterDemo.java` - This file contains `main()` method. This is entrypoint to our demo application. We are importing 2 `Spring Integration` resources in this file(package: com.kodtodya.practice.spring.integration).
```
@ImportResource({"classpath:http-inbound-adapter.xml", "classpath:http-inbound-gateway.xml"})
```
- `Training.java` - This is simple POJO class and will be used for accepting data, retrieving data from application.
- `TrainingService.java` - This class will provide us the different services of the `Training`. We can write business logic inside this class. We are storing training data inside one map, so we are performing respective operation on this map in different methods. For example, `insert()` method stores `Training` object, `getall()` method retrieves entire data present inside map.
- `InboundEnpoint.java` - This class provides all the webservice operation methods mapping with business operations. For example, `post()` method here is mapped with `insert()` method of `TrainingService`.

- `http-inbound-gateway.xml` - This file has configuration for http-inbound-gateway. This File contains 3 steps shown in frame:
```
    <!-- STEP-1: CHANNEL CREATION -->
    <int:channel id="requestChannel" />
    <int:channel id="outputChannel" />

    <!-- STEP-2: INBOUND GATEWAY CONFIGURATION FOR CREATED CHANNEL -->
    <int-http:inbound-gateway request-channel="requestChannel" reply-channel="outputChannel" supported-methods="GET" path="/getall">
         <int-http:request-mapping consumes="application/json" produces="application/json" />
    </int-http:inbound-gateway>

    <!-- STEP-3:SERVICE ACTIVATOR CONFIGURATION TO REGISTER/ACTIVATE IT -->
    <int:service-activator ref="inboundEnpoint" method="get" input-channel="requestChannel" output-channel="outputChannel" />
```
- `http-inbound-adapter.xml` - This file contains the `inbound adapter` configuration. 'http-inbound-adapter' should b done in 3 steps as shown in below frame:
```
  <!-- STEP-1 : CREATE CHANNEL --> 
  <int:channel id="routeRequest"/>

    <!-- STEP-2 : INBOUND CHANNEL ADAPTER CONFIGURATION FOR CREATED CHANNEL -->
    <int-http:inbound-channel-adapter channel="routeRequest" status-code-expression="T(org.springframework.http.HttpStatus).NO_CONTENT"
            supported-methods="POST, PUT" path="/training" request-payload-type="com.kodtodya.practice.spring.integration.model.Training">
        <int-http:request-mapping consumes="application/json"/>
    </int-http:inbound-channel-adapter>

    <!-- STEP-3 : REGISTER CREATED SERVICE ACTIVATOR -->
    <int:service-activator ref="inboundEnpoint" method="put" input-channel="httpPutChannel"/>
```

Also, we have 2 consumer endpoint for the router implementation that serves as an adapter for invoking a method on any Spring-managed object as specified by the `ref` and `method` attributes. In simple words, we are mapping the `service activator method` with configured `input-channel`.