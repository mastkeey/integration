package ru.mastkey.eps.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mastkey.eps.entity.Employee;

import java.util.UUID;

public interface EmployeeRepo extends JpaRepository<Employee, UUID> {
}
