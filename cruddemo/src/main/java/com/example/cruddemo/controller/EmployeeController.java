package com.example.cruddemo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cruddemo.entity.Employee;
import com.example.cruddemo.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {

  @Autowired
  private EmployeeService employeeService;

  @GetMapping("/employees")
  public ResponseEntity<List<Employee>> getAllData() {
    List<Employee> theList = employeeService.gettAllEmployees();
    return new ResponseEntity<List<Employee>>(theList, HttpStatus.OK);
  }

  @GetMapping("/employees/{id}")
  public ResponseEntity<Optional<Employee>> findByIdEmployee(@PathVariable int id) {
    Optional<Employee> theOptional = employeeService.findById(id);
    return new ResponseEntity<Optional<Employee>>(theOptional, HttpStatus.OK);
  }

  @PostMapping("/employees")
  public Employee createEmployee(@RequestBody Employee theEmployee) {
    theEmployee.setId(0);
    return employeeService.createEmployee(theEmployee);
  }

  @PutMapping("/employees/{id}")
  public Employee updateEmployee(@RequestBody Employee employee, @PathVariable int id) {
    return employeeService.updateEmployee(employee, id);
  }

  @DeleteMapping("/employees/{id}")
  public void deleteById(@PathVariable int id) {
    Employee deletedEmployee = employeeService.findById(id).get();
    System.out.println("student is deleting... " + deletedEmployee.getId());
    employeeService.deleteEmployee(id);
  }
}
