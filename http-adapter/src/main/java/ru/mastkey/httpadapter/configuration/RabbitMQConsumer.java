package ru.mastkey.httpadapter.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.mastkey.httpadapter.dto.EpsEmployeeEntity;
import ru.mastkey.httpadapter.service.SendEmployeeToIpropusk;

import java.io.IOException;

@Component
@EnableRabbit
@RequiredArgsConstructor
@Slf4j
public class RabbitMQConsumer {
    private final SendEmployeeToIpropusk sendEmployeeToIpropusk;
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = "queue1")
    public void processMyQueue(String message) {
        try {
            var entity = objectMapper.readValue(message, EpsEmployeeEntity.class);
            System.out.println("Deserialized entity: " + entity);
            sendEmployeeToIpropusk.sendEmployeeToIpropusk(entity);
        } catch (IOException e) {
            System.err.println("Failed to deserialize message: " + e.getMessage());
            e.printStackTrace();
        }
        log.info("Received from myQueue : {} ", new String(message.getBytes()));
    }
}