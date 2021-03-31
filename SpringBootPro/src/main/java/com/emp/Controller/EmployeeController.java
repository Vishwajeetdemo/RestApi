package com.emp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emp.exception.ResourceNotFoundException;
import com.emp.model.Employee;
import com.emp.repository.EmployeeRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	//get all employee
	@GetMapping("/employees")
	public List<Employee> getEmployees(){
		return employeeRepository.findAll();
	}
	//create employee rest api
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	
	//get employee by id rest api
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
		Employee employee=employeeRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("Not found"+id));
		return ResponseEntity.ok(employee);
	}
	
	//update employee by id rest api
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails){
		Employee employee=employeeRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("Not found"+id));
		   
		  employee.setFirstName(employeeDetails.getFirstName());
		  employee.setLastName(employeeDetails.getLastName());
		  employee.setEmailId(employeeDetails.getEmailId());
		
		  Employee updateEmployee = employeeRepository.save(employee);
		  return ResponseEntity.ok(updateEmployee);
	}
}
