package org.gestion.bp.service;

import org.gestion.bp.dto.user.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public interface IFoLoginService {

	public User authenticate(String uuid, String userLogin, String password) throws UsernameNotFoundException;

}
