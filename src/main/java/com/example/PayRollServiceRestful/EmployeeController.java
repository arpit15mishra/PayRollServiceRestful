package com.example.PayRollServiceRestful;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;



@RestController
public class EmployeeController {
	
	private final EmployeeRepository empRepository;
	
	public EmployeeController(EmployeeRepository empRepository) {
		this.empRepository = empRepository;
	}
	
	@GetMapping("/employees")
	CollectionModel<EntityModel<Employee>> all() {

	  List<EntityModel<Employee>> employees = empRepository.findAll().stream()
	      .map(employee -> EntityModel.of(employee,
	          linkTo(methodOn(EmployeeController.class).one(employee.getId())).withSelfRel(),
	          linkTo(methodOn(EmployeeController.class).all()).withRel("employees")))
	      .collect(Collectors.toList());

	  return CollectionModel.of(employees, linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
	}
	
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee emp) {
		return empRepository.save(emp);
	}
	
	@GetMapping("/employees/{id}")
	EntityModel<Employee> one(@PathVariable Long id) {

	  Employee employee = empRepository.findById(id) //
	      .orElseThrow(() -> new EmployeeNotFoundException(id));

	  return EntityModel.of(employee,
	      linkTo(methodOn(EmployeeController.class).one(id)).withSelfRel(),
	      linkTo(methodOn(EmployeeController.class).all()).withRel("employees"));
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
