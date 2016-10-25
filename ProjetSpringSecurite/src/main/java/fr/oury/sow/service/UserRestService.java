package fr.oury.sow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.oury.sow.dao.RoleRepository;
import fr.oury.sow.dao.UserRepository;
import fr.oury.sow.entities.Role;
import fr.oury.sow.entities.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Secured(value={"ROLE_ADMIN"})
public class UserRestService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	
	//   http://localhost:8282/addUser?username=prof3&password=123
	@RequestMapping(value = "/addUser")	
	public User saveUser(User u){		
	//	log.debug("saveUser(); user {}", u);		
		return userRepository.save(u);
	  }
	
	@RequestMapping(value = "/findUsers")
	public List<User> findAllUser(){
		return userRepository.findAll();
	}	
	//http://localhost:8282/addRole?role=invite
	@RequestMapping(value = "/addRole")	
	public Role addRole(Role r){		
		//log.debug("saveRole(); role {}", r);		
		return roleRepository.save(r);
	  }
	
	@RequestMapping(value = "/findAllUsers")
	public List<Role> findAllRole(){
		return roleRepository.findAll();
	}
	
	@RequestMapping(value = "/addRoleToUser")
	public User addRoleToUser(String username, String role){
		User u = userRepository.findOne(username);
		Role r = roleRepository.findOne(role);
		u.getRoles().add(r);
		userRepository.save(u);
		return u;
	}	
  }
