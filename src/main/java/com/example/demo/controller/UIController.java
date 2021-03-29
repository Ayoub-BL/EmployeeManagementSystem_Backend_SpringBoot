// operation 4
package com.example.demo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.EmpolyeeDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.model.HierarchyRoles;
import com.example.demo.model.Role;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.RoleService;

@RestController
@RequestMapping("/api/v1/")
public class UIController implements Serializable {

	private static final long serialVersionUID = 7168268571502023348L;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private RoleService roleService;

	// get all employee
	@CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")
	@GetMapping("/employees")
	public ModelMap getAllEmployees() {
		List<EmpolyeeDTO> employeesDTOList = new ArrayList<EmpolyeeDTO>();
		List<Employee> employees = employeeService.findAll();

		for (Employee o : employees) {
			String roleH = HierarchyRoles.values()[o.getRole().getHierarchyRole()].name();
			// System.out.println("o=" + o);
			EmpolyeeDTO e = new EmpolyeeDTO(o.getId(), o.getFirstName(), o.getLastName(), o.getEmailId(),
					o.getRole().getSalary(), roleH);
			employeesDTOList.add(e);
		}
		// System.out.println("getAllEmployees => employeesDTO=" + employeesDTO);
		ModelMap modalMap = new ModelMap();
		modalMap.put("employeesDTOList", employeesDTOList);
		modalMap.put("isTrue", true);
		return modalMap;
	}

	// get roles
	@CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")
	@GetMapping("/getRoles")
	public HierarchyRoles[] getHierarchyRoles() {
		return HierarchyRoles.values();
	}

	// create employee rest api
	@CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody EmpolyeeDTO employeeDTO) {
		// System.out.println("createEmployee => employeeDTO=" + employeeDTO);
		Employee employee = null;
		try {
			Optional<Role> optionalRole = roleService.findByHierarchyRole(Integer.parseInt(employeeDTO.getHierarchyRole()));
			Role role = optionalRole.isPresent() ? optionalRole.get() : null;
			if (role != null) {
				// System.out.println("role=" + role);
				employee = new Employee(employeeDTO.getFirstName(), employeeDTO.getLastName(), employeeDTO.getEmailId(), role);
				return employeeService.save(employee);
			} else {
				System.out.println("role is null");
			}

		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return employee;
	}

	// get the employee by id rest api
	@CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")
	@GetMapping("/employees/{id}")
	public ResponseEntity<EmpolyeeDTO> getEmployeeById(@PathVariable Long id) {

		// Getting the employee
		Employee employee = employeeService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee with id :" + id + "do net exist"));
		// Getting the role in string
		String roleH = HierarchyRoles.values()[employee.getRole().getHierarchyRole()].name();

		// Creating post response
		EmpolyeeDTO employeeDTO = new EmpolyeeDTO(employee.getId(), employee.getFirstName(), employee.getLastName(),
				employee.getEmailId(), employee.getRole().getSalary(), roleH);
		return ResponseEntity.ok(employeeDTO);
	}

	// update one employee
	@CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody EmpolyeeDTO employeeDTO) {
		Role role = null;
		try {
			Optional<Role> optionalRole = roleService.findByHierarchyRole(Integer.parseInt(employeeDTO.getHierarchyRole()));
			role = optionalRole.isPresent() ? optionalRole.get() : null;
		} catch (NumberFormatException e) {
			new ResourceNotFoundException("NumberFormatException with Role:\"" + employeeDTO.getHierarchyRole() + "\"");
		}
		Employee optionalEmployee = employeeService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("There is no Employee with id :\"" + id + "\""));
		optionalEmployee.setFirstName(employeeDTO.getFirstName());
		optionalEmployee.setLastName(employeeDTO.getLastName());
		optionalEmployee.setEmailId(employeeDTO.getEmailId());
		optionalEmployee.setRole(role);
		Employee employeeUpdate = employeeService.save(optionalEmployee);
		return ResponseEntity.ok(employeeUpdate);
	}

	// delete one employee
	@CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable int id, ModelMap modelMap) {
		// get the employee if exist
		System.out.println("id=" + id);
		Optional<Employee> optionalEmployee = employeeService.findById((long) id);
		Employee newEmployee = optionalEmployee.isPresent() ? optionalEmployee.get() : null;
		newEmployee.getRole().removeEmployee(newEmployee);
		// if (newEmployee!=null) {
		// delete employee
		employeeService.delete(newEmployee);
		// } else {new ResourceNotFoundException("There is no Employee with id :\"" + id
		// + "\"");}

		// Employee newEmployee = employeeService.findById((long)id)
		// .orElseThrow(()->new ResourceNotFoundException("There is no Employee with id
		// :\"" + id + "\""));

		System.out.println("newEmployee " + newEmployee);

		// delete employee
		// employeeService.delete(newEmployee);

		// create response
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);

		modelMap.addAttribute("r", response);

		return ResponseEntity.ok(response);
	}
}
