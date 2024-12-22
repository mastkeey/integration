package ru.mastkey.httpadapter.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.mastkey.httpadapter.configuration.RabbitMQProducerServiceImpl;
import ru.mastkey.httpadapter.dto.EmployeeIpropuskRequest;
import ru.mastkey.httpadapter.dto.EmployeeIpropuskResponse;
import ru.mastkey.httpadapter.dto.EpsEmployeeEntity;

@Service
@RequiredArgsConstructor
@Slf4j
public class SendEmployeeToIpropusk {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final RabbitMQProducerServiceImpl rabbitMQProducerService;

    @SneakyThrows
    public void sendEmployeeToIpropusk(EpsEmployeeEntity epsEmployeeEntity, String message) {
        try {
            var ipropuskRequest = new EmployeeIpropuskRequest(epsEmployeeEntity);
            var iPropukEmployee = restTemplate.postForObject("http://localhost:8090/employee", ipropuskRequest, EmployeeIpropuskResponse.class);
            epsEmployeeEntity.setIpropuskId(iPropukEmployee.getId());
            log.info("updated entity : {}", epsEmployeeEntity);
            rabbitMQProducerService.sendMessage(objectMapper.writeValueAsString(epsEmployeeEntity), "testKey2");
        } catch (Exception e) {
            log.info("message publish to failed queue");
            rabbitMQProducerService.sendFailedMessage(message, "failed");
        }
    }
}
