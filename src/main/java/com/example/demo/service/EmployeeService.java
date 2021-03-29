package com.example.demo.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeService implements Serializable {

	// Attributes :
	private static final long serialVersionUID = 4494899296045571629L;
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public List<Employee> findByOrderByIdDesc(){
		return employeeRepository.findByOrderByIdDesc();
	}
	
	public List<Employee> findAll(){
		return employeeRepository.findAll();
	}
	
	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	public Optional<Employee> findById(long id) {
		return employeeRepository.findById(id);
	}
	
	public void delete(Employee employee) {
		employeeRepository.delete(employee);
	}
	
	
	
}
