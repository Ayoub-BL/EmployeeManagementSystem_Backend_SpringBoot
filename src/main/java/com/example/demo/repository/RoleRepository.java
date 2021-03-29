// operation 2
package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
	
	public Optional<Role> findByHierarchyRole(int hierarchyRoleId);
	
}
