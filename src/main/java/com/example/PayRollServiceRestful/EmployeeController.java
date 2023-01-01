package com.example.PayRollServiceRestful;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
	
	private final EmployeeRepository empRepository;
	
	public EmployeeController(EmployeeRepository empRepository) {
		this.empRepository = empRepository;
	}
	
	@GetMapping("/employees")
	public List<Employee> getAll() {
		return empRepository.findAll();
	}

}
