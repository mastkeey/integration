package ru.mastkey.httpadapter.configuration;

public interface RabbitMQProducerService {

    void sendMessage(String message, String routingKey);
}