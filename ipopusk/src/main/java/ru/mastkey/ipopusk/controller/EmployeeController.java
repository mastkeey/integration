package ru.mastkey.ipopusk.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.mastkey.ipopusk.controller.dto.CreateEmployeeDto;
import ru.mastkey.ipopusk.entity.Employee;
import ru.mastkey.ipopusk.service.EmployeeService;

@RequiredArgsConstructor
@RestController
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping("/employee")
    public Employee createEmployee(@RequestBody CreateEmployeeDto createEmployeeDto) {
        return employeeService.save(createEmployeeDto);
    }
}
