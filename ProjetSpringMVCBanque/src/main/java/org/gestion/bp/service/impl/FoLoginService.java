package org.gestion.bp.service.impl;

import lombok.extern.slf4j.Slf4j;

import org.gestion.bp.dto.user.User;
import org.gestion.bp.service.IFoLoginService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("foLoginService")
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class FoLoginService extends AbstractService implements IFoLoginService {

	//	private final AccountStatusUserDetailsChecker	detailsChecker	= new AccountStatusUserDetailsChecker();

	@Override
	public User authenticate(String uuid, String userLogin, String password) {
		//log.debug("START;{}; authenticate ; {}", uuid);
		throw new UsernameNotFoundException("user not found");
	}
}
