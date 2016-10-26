package fr.oury.sow.service.Impl;

import java.util.List;

import fr.oury.sow.entities.Role;
import fr.oury.sow.entities.User;

public interface IUserRestService {

	//   http://localhost:8282/addUser?username=prof3&password=123
	public abstract User saveUser(User u);

	public abstract List<User> findAllUser();

	//http://localhost:8282/addRole?role=invite
	public abstract Role addRole(Role r);

	public abstract List<Role> findAllRole();

	public abstract User addRoleToUser(String username, String role);

}