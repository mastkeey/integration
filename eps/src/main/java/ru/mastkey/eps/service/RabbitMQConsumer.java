package ru.mastkey.eps.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.mastkey.eps.repo.EmployeeRepo;

import java.io.IOException;
import java.util.UUID;

@Component
@EnableRabbit
@RequiredArgsConstructor
@Slf4j
public class RabbitMQConsumer {
    private final ObjectMapper objectMapper;
    private final EmployeeService employeeService;

    @RabbitListener(queues = "queue2")
    public void employeeForSetIpropuskIdListener(String message) {
        try {
            JsonNode rootNode = objectMapper.readTree(message);
            var employeeId = UUID.fromString(rootNode.get("id").asText());
            var employeeIpropuskId = UUID.fromString(rootNode.get("ipropuskId").asText());
            employeeService.setIpropuskId(employeeId, employeeIpropuskId);
        } catch (IOException e) {
            System.err.println("Failed to deserialize message: " + e.getMessage());
            e.printStackTrace();
        }
    }
}