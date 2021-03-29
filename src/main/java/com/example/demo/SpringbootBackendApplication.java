package com.example.demo;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.model.Employee;
import com.example.demo.model.Role;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.RoleService;

@SpringBootApplication
public class SpringbootBackendApplication {
	
	// Attributes :
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private EmployeeService employeeService;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBackendApplication.class, args);
	}
	
	@PostConstruct
	public void init() {
		Role role1 = new Role(1, 6000.00);
		Role role2 = new Role(2, 5000.00);
		Role role3 = new Role(3, 600.00);
		Role role4 = new Role(4, 2000.00);
		Role role5 = new Role(5, 1800.00);
		Role role6 = new Role(6, 1500.00);
		Role role7 = new Role(7, 3000.00);
		Role role8 = new Role(8, 2500.00);
		
		roleService.save(role1);
		roleService.save(role2);
		roleService.save(role3);
		roleService.save(role4);
		roleService.save(role5);
		roleService.save(role6);
		roleService.save(role7);
		roleService.save(role8);
		
		Employee employee1 = new Employee("CET1_firstName", "CTE_lastName", "example@gmail.com", role1);
		Employee employee2 = new Employee("CET2_firstName", "CTE2_lastName", "example@gmail.com", role1);
		Employee employee3 = new Employee("CEO1_firstName", "CEO1_lastName", "example@gmail.com", role2);
		Employee employee4 = new Employee("CEO2_firstName", "CEO2_lastName", "example@gmail.com", role2);
		Employee employee5 = new Employee("CEO3_firstName", "CEO3_lastName", "example@gmail.com", role2);
		Employee employee6 = new Employee("CEO4_firstName", "CEO4_lastName", "example@gmail.com", role2);
		Employee employee7 = new Employee("CEO5_firstName", "CEO5_lastName", "example@gmail.com", role2);
		
		// Persisting employees to DB :
		employeeService.save(employee1);
		employeeService.save(employee2);
		employeeService.save(employee3);
		employeeService.save(employee4);
		employeeService.save(employee5);
		employeeService.save(employee6);
		employeeService.save(employee7);
		
		
	}

}
