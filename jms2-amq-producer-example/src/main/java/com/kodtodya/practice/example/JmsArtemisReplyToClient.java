package com.kodtodya.practice.example;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.jms.*;

public class JmsArtemisReplyToClient {
    private static final Logger log = Logger.getLogger(JmsArtemisReplyToClient.class.getName());

    private static final String DEFAULT_BROKER_URL = "tcp://localhost:61616";

    private ConnectionFactory connectionFactory;

    private JmsTemplate jmsTemplate;

    public ConnectionFactory connectionFactory() {
        ConnectionFactory connectionFactory =
                new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_PASSWORD);
        return connectionFactory;
    }

    @PostConstruct
    public void init() {
        connectionFactory = this.connectionFactory();
        this.jmsTemplate = new JmsTemplate(connectionFactory);
    }

    public void sendMessage(String queueName, String message) {
        System.out.println("sending: " + message);

        jmsTemplate.send(queueName, session -> {
            Message msg = jmsTemplate.getMessageConverter().toMessage(message, session);
            msg.setJMSReplyTo(session.createQueue("spring-integration-jms-demo-reply"));
            return msg;
        });
    }

    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(JmsArtemisReplyToClient.class);
        JmsArtemisReplyToClient client = new JmsArtemisReplyToClient();

        String queueName = "spring-integration-jms-demo";
        client.sendMessage(queueName, "test message");

    }
}

