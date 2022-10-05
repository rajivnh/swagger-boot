package com.lti.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lti.EmployeeApiDelegate;
import com.lti.model.Employee;

@Service
public class EmployeeImpl implements EmployeeApiDelegate {
	private List<Employee> employees = new ArrayList<Employee>();
	
	@Override
	public ResponseEntity<List<Employee>> findAll() {
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Employee> findById(String id) {
		Employee employee = employees.stream().filter(e -> e.getId().equals(id)).map(e -> e).findAny().orElse(null);
		
		if(employee == null)
			return new ResponseEntity<Employee>(new Employee(), HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}
	
	@PostConstruct
	private void init() {
		Employee employee1 = new Employee();
		
		employee1.setId("EMP9845");
		employee1.setName("Gopal Krishnan");
		employee1.setPhoneNumber("(+91) 9123456789");
		employee1.setCreatedate(LocalDate.now());
		
		Employee employee2 = new Employee();
		
		employee2.setId("EMP3426");
		employee2.setName("Sathish Ramasarthy");
		employee2.setPhoneNumber("(+91) 9876543210");
		employee2.setCreatedate(LocalDate.now());
		
		employees = Arrays.asList(employee1, employee2);
	}
}
