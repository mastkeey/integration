package ru.mastkey.eps.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mastkey.eps.controller.dto.CreateEmployeeDto;
import ru.mastkey.eps.entity.Employee;
import ru.mastkey.eps.rabbit.RabbitMQProducerServiceImpl;
import ru.mastkey.eps.repo.EmployeeRepo;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {
    private final EmployeeRepo employeeRepo;
    private final RabbitMQProducerServiceImpl rabbitMQProducerService;
    private final ObjectMapper objectMapper;

    @Transactional
    @SneakyThrows
    public void save(CreateEmployeeDto createEmployeeDto) {
        var employee = new Employee();
        employee.setName(createEmployeeDto.getName());
        var savedEmployee = employeeRepo.save(employee);
        rabbitMQProducerService.sendMessage(objectMapper.writeValueAsString(savedEmployee), "testKey");
    }

    @Transactional
    public void setIpropuskId(UUID employeeId, UUID ipropuskEmployeeId) {
        var employee = employeeRepo.findById(employeeId).orElseThrow(
                () -> new RuntimeException("asdasdasd")
        );
        employee.setIPropuskId(ipropuskEmployeeId);
        var updatedEmployee = employeeRepo.save(employee);
        log.info("Updated employee: {}", updatedEmployee);
    }
}
