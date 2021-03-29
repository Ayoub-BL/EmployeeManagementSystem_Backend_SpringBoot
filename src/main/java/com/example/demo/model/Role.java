package com.example.demo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "Role")
@Table(name = "role")
public class Role implements Serializable{
	// Attributes :
	private static final long serialVersionUID = -2975877849722398826L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_generator")
	@SequenceGenerator(name="role_generator", sequenceName = "role_sequence")
	@Column(name = "role_id")
	private long id;
	
	
	
	@Column(name = "hierarchy_role")
	private int hierarchyRole;
	@Column(name = "salary")
	private Double salary;	
	
	@OneToMany(mappedBy = "role", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Employee> employees = new ArrayList<Employee>();
	
	// Constructors :
	public Role() {}
	public Role( int hierarchyRole,Double salary) {
		this.hierarchyRole = hierarchyRole;
		this.salary = salary;
		
	}
	
	// Getters :
	public long getId() {return id;}
	public Double getSalary() {return salary;}
	public int getHierarchyRole() {return hierarchyRole;}
	public List<Employee> getEmployees() {return employees;}
	
	// Setters :
//	public void setId(long id) {this.id = id;}
	public void setSalary(Double salary) {this.salary = salary;}
	public void setHierarchyRole(int hierarchyRole) {this.hierarchyRole = hierarchyRole;}
//	public void setEmployees(List<Employee> employees) {this.employees = employees;}
	
	public void addEmployee(Employee employee) {
		if (employee!=null) {
			if (!employees.contains(employee)) {
				employee.setRole(this);
				employees.add(employee);
			}
		}
	}
	
	public void updateEmployee(Employee employee) {
		if (employee!=null) {
			int index = employees.lastIndexOf(employee);
			employees.set(index, employee);
		}
	}
	
	public void removeEmployee(Employee employee) {
		if (employee!=null) {
			if (employees.contains(employee)) {
				employee.setRole(null);
				employees.remove(employee);
			}
		}
	}
	
	// Methods :
	@Override
	public String toString() {return "Role [id=" + id + ", salary=" + salary + ", hierarchyRole=" + hierarchyRole + "]";}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employees == null) ? 0 : employees.hashCode());
		result = prime * result + (int) (hierarchyRole ^ (hierarchyRole >>> 32));
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((salary == null) ? 0 : salary.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
//		if (employees == null) {
//			if (other.employees != null)
//				return false;
//		} else if (!employees.equals(other.employees))
//			return false;
//		if (hierarchyRoles != other.hierarchyRoles)
//			return false;
		if (id != other.id)
			return false;
//		if (salary == null) {
//			if (other.salary != null)
//				return false;
//		} else if (!salary.equals(other.salary))
//			return false;
		return true;
	}
	
}
