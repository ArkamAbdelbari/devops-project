package com.example.employeemanager.service;

import com.example.employeemanager.entity.Employee;
import com.example.employeemanager.exception.UserNotFoundException;
import com.example.employeemanager.Dao.EmployeeRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {
    private final EmployeeRepo employeeRepo;
    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }
    public Employee addEmployee(Employee employee) {
        employee.setEmployeeCode( UUID.randomUUID().toString());
        return employeeRepo.save(employee);
    }

    public List<Employee> findAllEmployees() {
        return employeeRepo.findAll();
    }
    public Employee updateEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }
    @Transactional
    public Employee findEmployeeById(Long id) {
        return employeeRepo.findEmployeeById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
    }
    @Transactional

    public void deleteEmployee(Long id) {

        employeeRepo.deleteEmployeeById(id);
    }

}
