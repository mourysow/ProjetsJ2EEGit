package fr.oury.sow.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.oury.sow.entities.Role;

public interface RoleRepository extends JpaRepository<Role, String> {
	

}
