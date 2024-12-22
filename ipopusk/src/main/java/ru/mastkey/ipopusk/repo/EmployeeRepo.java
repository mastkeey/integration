package ru.mastkey.ipopusk.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mastkey.ipopusk.entity.Employee;

import java.util.UUID;

public interface EmployeeRepo extends JpaRepository<Employee, UUID> {
}
