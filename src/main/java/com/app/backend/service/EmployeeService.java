package com.app.backend.service;

import com.app.backend.entity.EmployeeEntity;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Optional<EmployeeEntity> getEmployeeById(Long employeeId);

    List<EmployeeEntity> getAllEmployees();

    EmployeeEntity addEmployee(EmployeeEntity employee);

    EmployeeEntity updateEmployee(EmployeeEntity employee);

    void deleteEmployeeById(Long employeeId);
}
