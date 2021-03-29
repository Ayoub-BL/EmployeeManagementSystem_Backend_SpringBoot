package com.example.demo.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Role;
import com.example.demo.repository.RoleRepository;

@Service
public class RoleService implements Serializable {

	// Attributes :
	private static final long serialVersionUID = 7410191614187361717L;
	@Autowired
	private RoleRepository roleRepository;
	
	public Role save(Role role) {
		return roleRepository.save(role);
	}
	
	public List<Role> findAll() {
		return roleRepository.findAll();
	}
	
	public Optional<Role> findById(long id) {
		return roleRepository.findById(id);
	}
	

	public Optional<Role> findByHierarchyRole(int hierarchyRoleId) {
		return roleRepository.findByHierarchyRole(hierarchyRoleId);
	}
	
	public void delete(Role role) {
		roleRepository.delete(role);
	}
	
	
	
}
