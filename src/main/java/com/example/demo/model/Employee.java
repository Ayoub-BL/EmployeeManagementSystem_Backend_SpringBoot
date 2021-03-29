// operation 1
package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity(name = "Employee")
@Table(name = "employees")
public class Employee implements Serializable {
	
	// Attributes :
	private static final long serialVersionUID = 8574677565099300009L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_generator")
	@SequenceGenerator(name="employee_generator", sequenceName = "employee_sequence")
	@Column(name = "employee_id")
	private long id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "email_id")
	private String emailId;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "role_id_fk")
	@JsonBackReference
	private Role role;
	
	// Constructors :
	public Employee() {}
	public Employee(String firstName, String lastName, String emailId, Role role) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		setRole(role);
	}
	
	// Getters :
	public long getId() {return id;}
	public String getFirstName() {return firstName;}
	public String getLastName() {return lastName;}
	public String getEmailId() {return emailId;}
	public Role getRole() {return role;}
	
	// Setters :
	public void setId(long id) {this.id = id;}
	public void setFirstName(String firstName) {this.firstName = firstName;}
	public void setLastName(String lastName) {this.lastName = lastName;}
	public void setEmailId(String emailId) {this.emailId = emailId;}
	public void setRole(Role role) {this.role = role;}
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", emailId=" + emailId + ", role=" + role + "]";
	}
	
}
