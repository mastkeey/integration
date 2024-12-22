package ru.mastkey.eps.rabbit;

public interface RabbitMQProducerService {

    void sendMessage(String message, String routingKey);
}