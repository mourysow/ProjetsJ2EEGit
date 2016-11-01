package com.websystique.springmvc.service;

import java.util.List;

import com.websystique.springmvc.model.ScRole;
import com.websystique.springmvc.model.ScUser;


public interface IScUserRestService {

	//   http://localhost:8282/addUser?username=prof3&password=123
	public abstract ScUser saveUser(ScUser u);

	public abstract List<ScUser> findAllUser();

	//http://localhost:8282/addRole?role=invite
	public abstract ScRole addRole(ScRole r);

	public abstract List<ScRole> findAllRole();

	public abstract ScUser addRoleToUser(String username, String role);

}