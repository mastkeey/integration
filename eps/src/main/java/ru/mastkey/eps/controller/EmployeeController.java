package ru.mastkey.eps.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.mastkey.eps.controller.dto.CreateEmployeeDto;
import ru.mastkey.eps.service.EmployeeService;

@RequiredArgsConstructor
@RestController
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping("/employee")
    public void createEmployee(@RequestBody CreateEmployeeDto createEmployeeDto) {
        employeeService.save(createEmployeeDto);
    }
}
