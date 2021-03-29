package com.example.demo.dto;

import java.io.Serializable;

public class EmpolyeeDTO implements Serializable{ 
	
	private static final long serialVersionUID = 1223078048133964710L;
	// Attributes
	private Long id;
	private String firstName;
	private String lastName;
	private String emailId;
	private String hierarchyRole;
	private Double salary;	
	// Constructors
	public EmpolyeeDTO() {};
	public EmpolyeeDTO(Long id, String firstName, String lastName, String emailId, Double salary, String hierarchyRole) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.salary = salary;
		this.hierarchyRole = hierarchyRole;}
	
	//Getters
	public Long getId() {return id;}
	public String getFirstName() {return firstName;}
	public String getLastName() {return lastName;}
	public String getEmailId() {return emailId;}
	public Double getSalary() {return salary;}
	public String getHierarchyRole() {return hierarchyRole;}
	
	//Setters
	public void setId(Long id) {this.id = id;}
	public void setFirstName(String firstName) {this.firstName = firstName;}
	public void setLastName(String lastName) {this.lastName = lastName;}
	public void setEmailId(String emailId) {this.emailId = emailId;}
	public void setSalary(Double salary) {this.salary = salary;}
	public void setHierarchyRole(String hierarchyRole) {this.hierarchyRole = hierarchyRole;}

	@Override
	public String toString() {
		return "EmpolyeeDTO [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", emailId=" + emailId
				+ ", hierarchyRole=" + hierarchyRole + ", salary=" + salary + "]";
	}
	
}
