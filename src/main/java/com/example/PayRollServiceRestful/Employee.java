package com.example.PayRollServiceRestful;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Employee {
	
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	private String name, role;
	private Integer age;
	private Integer salary;
	
	public Employee() {
		
	}
	
	public Employee(String name, String role, Integer age, Integer salary) {
		this.name = name;
		this.role = role;
		this.age = age;
		this.salary = salary;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getSalary() {
		return salary;
	}
	public void setSalary(Integer salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "Employee [name=" + name + ", role=" + role + ", age=" + age + ", salary=" + salary + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(age, name, role, salary);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(age, other.age) && Objects.equals(name, other.name) && Objects.equals(role, other.role)
				&& Objects.equals(salary, other.salary);
	}
	
	

}
