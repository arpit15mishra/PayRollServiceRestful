package com.example.PayRollServiceRestful;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
	
	private static final Logger logger = LoggerFactory.getLogger(LoadDatabase.class);
	
	CommandLineRunner initDatabase(EmployeeRepository employeeRepository) {
		
		return args->{
		employeeRepository.save(new Employee("arpit mishra", "associate software engineer qa", 24, 58000));
		logger.info("loaded data!! Total entity in repository : " + employeeRepository.count());
		employeeRepository.save(new Employee("apoorv mishra", "SDE", 24, 158000));
		logger.info("loaded data!! Total entity in repository : " + employeeRepository.count());
		};
		
		
	}

}
