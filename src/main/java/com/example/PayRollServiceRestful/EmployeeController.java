package com.example.PayRollServiceRestful;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee emp) {
		return empRepository.save(emp);
	}
	
	@GetMapping("/employees/{id}")
	public Optional<Employee> getEmployeeById(@PathVariable Long id) {
		return empRepository.findById(id);
		
	}
	
	@PutMapping("/employees/{id}")
	public Employee modifyEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
		return empRepository.findById(id).
				map(employee->{
					employee.setName(newEmployee.getName());
					employee.setRole(newEmployee.getRole());
					employee.setAge(newEmployee.getAge());
					employee.setSalary(newEmployee.getSalary());
					return empRepository.save(employee);
					
					
				}).
				orElseGet(() -> {
			        newEmployee.setId(id);
			        return empRepository.save(newEmployee);
			      });
	}

}
