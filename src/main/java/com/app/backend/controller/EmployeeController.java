package com.app.backend.controller;

import com.app.backend.DTO.ResponseUtilDTO;
import com.app.backend.entity.EmployeeEntity;
import com.app.backend.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<ResponseUtilDTO.Response<Object>> getEmployeeById(@PathVariable Long employeeId) {
        try {
            logger.info("Fetching employee by ID: {}", employeeId);
            Optional<EmployeeEntity> employee = employeeService.getEmployeeById(employeeId);

            if (employee.isEmpty()) {
                logger.warn("Employee not found for ID: {}", employeeId);
                return ResponseUtilDTO.generateErrorResponse("Empleado no encontrado");
            }

            logger.info("Employee found for ID: {}", employeeId);
            return ResponseUtilDTO.generateSuccessResponse(employee.get());

        } catch (Exception e) {
            logger.error("Error occurred while fetching employee by ID: {}", employeeId, e);
            return ResponseUtilDTO.generateErrorResponse( e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseUtilDTO.Response<Object>> getAllEmployees() {
        try {
            logger.info("Fetching all employees");
            List<EmployeeEntity> employees = employeeService.getAllEmployees();

            if (employees.isEmpty()) {
                logger.warn("No employees available");
                return ResponseUtilDTO.generateErrorResponse("No hay empleados");
            }

            logger.info("Fetched {} employees", employees.size());
            return ResponseUtilDTO.generateSuccessResponse(employees);

        } catch (Exception e) {
            logger.error("Error occurred while fetching all employees", e);
            return ResponseUtilDTO.generateErrorResponse( e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseUtilDTO.Response<Object>> addEmployee(@RequestBody EmployeeEntity employee) {
        try {
            logger.info("Adding new employee: {}", employee);
            EmployeeEntity addedEmployee = employeeService.addEmployee(employee);
            logger.info("Employee added successfully with ID: {}", addedEmployee.getEmployeeId());
            return ResponseUtilDTO.generateSuccessResponse(addedEmployee);
        } catch (Exception e) {
            logger.error("Error occurred while adding new employee", e);
            return ResponseUtilDTO.generateErrorResponse(e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseUtilDTO.Response<Object>> updateEmployee(@RequestBody EmployeeEntity employee) {
        try {
            logger.info("Updating employee with ID: {}", employee.getEmployeeId());
            EmployeeEntity updatedEmployee = employeeService.updateEmployee(employee);
            logger.info("Employee updated successfully with ID: {}", updatedEmployee.getEmployeeId());
            return ResponseUtilDTO.generateSuccessResponse("Se actualizó correctamente el empleado # " + updatedEmployee.getEmployeeId());
        } catch (Exception e) {
            logger.error("Error occurred while updating employee", e);
            return ResponseUtilDTO.generateErrorResponse( e.getMessage());
        }
    }

    @DeleteMapping("/delete/{employeeId}")
    public ResponseEntity<ResponseUtilDTO.Response<Object>> deleteEmployeeById(@PathVariable Long employeeId) {
        try {
            logger.info("Deleting employee with ID: {}", employeeId);
            employeeService.deleteEmployeeById(employeeId);
            logger.info("Employee deleted successfully with ID: {}", employeeId);
            return ResponseUtilDTO.generateSuccessResponse("Se eliminó correctamente el empleado # " + employeeId);
        } catch (Exception e) {
            logger.error("Error occurred while deleting employee with ID: {}", employeeId, e);
            if(e.getClass().getName().equals("org.springframework.dao.DataIntegrityViolationException")){
                return ResponseUtilDTO.generateErrorResponse("Error al intentar un item con una clave foranea");
            }
            return ResponseUtilDTO.generateErrorResponse( e.getMessage());
        }
    }
}
