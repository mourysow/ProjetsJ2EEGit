package fr.oury.sow.service.Impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.oury.sow.dao.RoleRepository;
import fr.oury.sow.dao.UserRepository;
import fr.oury.sow.entities.Role;
import fr.oury.sow.entities.User;
import fr.oury.sow.service.IUserRestService;

@RestController
@Secured(value={"ROLE_ADMIN"})
public class UserRestService implements IUserRestService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	
	//   http://localhost:8282/addUser?username=prof3&password=123
	/* (non-Javadoc)
	 * @see fr.oury.sow.service.Impl.IUserRestService#saveUser(fr.oury.sow.entities.User)
	 */
	@Override
	@RequestMapping(value = "/addUser")	
	public User saveUser(User u){		
	//	log.debug("saveUser(); user {}", u);		
		return userRepository.save(u);
	  }
	
	/* (non-Javadoc)
	 * @see fr.oury.sow.service.Impl.IUserRestService#findAllUser()
	 */
	@Override
	@RequestMapping(value = "/findUsers")
	public List<User> findAllUser(){
		return userRepository.findAll();
	}	
	//http://localhost:8282/addRole?role=invite
	/* (non-Javadoc)
	 * @see fr.oury.sow.service.Impl.IUserRestService#addRole(fr.oury.sow.entities.Role)
	 */
	@Override
	@RequestMapping(value = "/addRole")	
	public Role addRole(Role r){		
		//log.debug("saveRole(); role {}", r);		
		return roleRepository.save(r);
	  }
	
	/* (non-Javadoc)
	 * @see fr.oury.sow.service.Impl.IUserRestService#findAllRole()
	 */
	@Override
	@RequestMapping(value = "/findAllUsers")
	public List<Role> findAllRole(){
		return roleRepository.findAll();
	}
	
	/* (non-Javadoc)
	 * @see fr.oury.sow.service.Impl.IUserRestService#addRoleToUser(java.lang.String, java.lang.String)
	 */
	@Override
	@RequestMapping(value = "/addRoleToUser")
	public User addRoleToUser(String username, String role){
		User u = userRepository.findOne(username);
		Role r = roleRepository.findOne(role);
		u.getRoles().add(r);
		userRepository.save(u);
		return u;
	}	
  }
