package ru.mastkey.eps.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory("localhost");
        cachingConnectionFactory.setUsername("user");
        cachingConnectionFactory.setPassword("password");
        cachingConnectionFactory.setVirtualHost("host");
        return cachingConnectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    @Bean
    public Queue myQueue1() {
        return new Queue("queue1");
    }

    public Queue myQueue2() {
        return new Queue("queue2");
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange("employee", true, false);
    }

    @Bean
    Binding bindingQueue1(DirectExchange exchange) {
        return BindingBuilder.bind(myQueue1()).to(exchange).with("testKey");
    }

    @Bean
    Binding bindingQueue2(DirectExchange exchange) {
        return BindingBuilder.bind(myQueue2()).to(exchange).with("testKey2");
    }

}