package ru.mastkey.httpadapter.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMQProducerServiceImpl implements RabbitMQProducerService {

    private final RabbitTemplate rabbitTemplate;


    public void sendMessage(String message, String routingKey) {
        rabbitTemplate.convertAndSend("employee", routingKey, message);
    }

    public void sendFailedMessage(String message, String routingKey) {
        rabbitTemplate.convertAndSend("employee", routingKey, message);
    }
}