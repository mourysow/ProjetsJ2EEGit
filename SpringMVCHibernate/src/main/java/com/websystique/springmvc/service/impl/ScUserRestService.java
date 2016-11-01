package com.websystique.springmvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.websystique.springmvc.dao.impl.ScRoleRepository;
import com.websystique.springmvc.dao.impl.ScUserRepository;
import com.websystique.springmvc.model.ScRole;
import com.websystique.springmvc.model.ScUser;
import com.websystique.springmvc.service.IScUserRestService;

@RestController
@Secured(value={"ROLE_ADMIN"})
public class ScUserRestService implements IScUserRestService {
	
	@Autowired
	private ScUserRepository userRepository;
	@Autowired
	private ScRoleRepository roleRepository;
	
	//   http://localhost:8282/addUser?username=prof3&password=123
	/* (non-Javadoc)
	 * @see fr.oury.sow.service.Impl.IUserRestService#saveUser(fr.oury.sow.entities.User)
	 */
	@Override
	@RequestMapping(value = "/addUser")	
	public ScUser saveUser(ScUser u){		
	//	log.debug("saveUser(); user {}", u);		
		return userRepository.save(u);
	  }
	
	/* (non-Javadoc)
	 * @see fr.oury.sow.service.Impl.IUserRestService#findAllUser()
	 */
	@Override
	@RequestMapping(value = "/findUsers")
	public List<ScUser> findAllUser(){
		return userRepository.findAll();
	}	
	//http://localhost:8282/addRole?role=invite
	/* (non-Javadoc)
	 * @see fr.oury.sow.service.Impl.IUserRestService#addRole(fr.oury.sow.entities.Role)
	 */
	@Override
	@RequestMapping(value = "/addRole")	
	public ScRole addRole(ScRole r){		
		//log.debug("saveRole(); role {}", r);		
		return roleRepository.save(r);
	  }
	
	/* (non-Javadoc)
	 * @see fr.oury.sow.service.Impl.IUserRestService#findAllRole()
	 */
	@Override
	@RequestMapping(value = "/findAllUsers")
	public List<ScRole> findAllRole(){
		return roleRepository.findAll();
	}
	
	/* (non-Javadoc)
	 * @see fr.oury.sow.service.Impl.IUserRestService#addRoleToUser(java.lang.String, java.lang.String)
	 */
	@Override
	@RequestMapping(value = "/addRoleToUser")
	public ScUser addRoleToUser(String username, String role){
		ScUser u = userRepository.findOne(username);
		ScRole r = roleRepository.findOne(role);
		u.getRoles().add(r);
		userRepository.save(u);
		return u;
	}	
  }
