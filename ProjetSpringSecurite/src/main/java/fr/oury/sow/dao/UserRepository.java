package fr.oury.sow.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.oury.sow.entities.User;

public interface UserRepository extends JpaRepository<User, String> {
	

}
