package com.toprockies.springboot.thymeleaf.rest;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toprockies.springboot.thymeleaf.entity.Employee;
import com.toprockies.springboot.thymeleaf.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	private EmployeeService employeeService;
	
	public EmployeeRestController(EmployeeService theEmployeeService) {
		employeeService = theEmployeeService;
	}

	// add mapping for Get employees
	@GetMapping("/employees")
	public List<Employee> listEmployees() {

		// get employees from db
		List<Employee> theEmployees = employeeService.findAll();

		return theEmployees;
	}
	
	// add mapping for one employee
	@GetMapping("/employees/{theId}")
	public Employee getEmployeeInfoById(@PathVariable int theId) {

		// get the employee from the service
		Employee theEmployee = employeeService.findById(theId);
		
		// send over to our form
		return theEmployee;
	}
	
	// Save a new record (when id field is empty) or update an existing record
	@PostMapping("/employees")
	public String saveEmployee(@RequestBody Employee theEmployee) {

		// save the employee
		employeeService.save(theEmployee);
		
		// use a redirect to prevent duplicate submissions
		return "Successfully added Employee";
	}
	
	@DeleteMapping("/employees/{theId}")
	public void deleteEmployee(@PathVariable int theId) {
		// delete the employee
		employeeService.deleteById(theId);
	}
}
