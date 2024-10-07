package com.example.cruddemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cruddemo.entity.Employee;
import com.example.cruddemo.repo.EmployeeRepo;

@Service
public class EmployeeService {

  @Autowired
  EmployeeRepo employeeRepo;

  public List<Employee> gettAllEmployees() {
    return employeeRepo.findAll();
  }

  public Optional<Employee> findById(int id) {
    return employeeRepo.findById(id);
  }

  public void deleteEmployee(int id) {
    Optional<Employee> tempEmployee = employeeRepo.findById(id);
    System.out.println("deleted Employee : " + tempEmployee);
    employeeRepo.deleteById(id);
  }

  public Employee updateEmployee(Employee employee, int id) {
    Employee dbEmployee = employeeRepo.findById(id).get();
    dbEmployee.setFirstName(employee.getFirstName());
    dbEmployee.setLastName(employee.getLastName());
    dbEmployee.setEmail(employee.getEmail());
    employeeRepo.save(dbEmployee);
    return dbEmployee;
  }

  public Employee createEmployee(Employee employee) {
    employeeRepo.save(employee);
    return employee;
  }
}