package ru.mastkey.ipopusk.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mastkey.ipopusk.controller.dto.CreateEmployeeDto;
import ru.mastkey.ipopusk.entity.Employee;
import ru.mastkey.ipopusk.repo.EmployeeRepo;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepo employeeRepo;

    @Transactional
    public Employee save(CreateEmployeeDto createEmployeeDto) {
        var employee = new Employee();
        employee.setName(createEmployeeDto.getName());
        return employeeRepo.save(employee);
    }
}
