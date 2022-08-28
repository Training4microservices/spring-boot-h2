package com.training.springboot.controller;

import com.training.springboot.model.Employee;
import com.training.springboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class EmployeeController {

  @Autowired
  EmployeeService employeeService;

  @GetMapping("/employees")
  private List getAllEmployees() {
    return employeeService.getAllEmployees();
  }

  @GetMapping("/employees/{id}")
  private Employee getEmployeeById(@PathVariable("id") int id) {
    return employeeService.getEmployeeById(id);
  }

  @PostMapping("/employees")
  private ResponseEntity createEmployee(@Valid @RequestBody Employee employee) {
    try{
      employeeService.saveOrUpdate(employee);
    } catch (Exception exception) {
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity("New employee created with id: "+employee.getId(), HttpStatus.CREATED);
  }

  @DeleteMapping("/employees/{id}")
  private ResponseEntity deleteById(@PathVariable("id") int id) {
    try{
      employeeService.delete(id);
    } catch (Exception exception) {
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity("Employee deleted with id: "+id, HttpStatus.OK);
  }

  @PutMapping("/employees")
  private ResponseEntity updateEmployee(@RequestBody Employee employee) {
    try{
      employeeService.saveOrUpdate(employee);
    } catch (Exception exception) {
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity(" employee updated with id: "+employee.getId(), HttpStatus.OK);
  }

  @PatchMapping("/employees/{id}")
  public ResponseEntity<?> updateEmployeeByPatch(@RequestParam("name") String name, @PathVariable("id") Integer id){
    Employee employee = employeeService.updateEmployee(name,id);
    return new ResponseEntity<>(employee,HttpStatus.OK);
  }


}