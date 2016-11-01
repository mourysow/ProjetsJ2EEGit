package com.websystique.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import com.websystique.springmvc.service.UserService;
import com.websystique.springmvc.service.UserProfileService;
import org.springframework.context.MessageSource;


public abstract class AbstractController {

	protected static final String		ROLE_USER		= "ROLE_USER";
	protected static final String		ROLE_DEFAULT	= "ROLE_DEFAULT";

	@Autowired
	protected UserService userService;
	
	@Autowired
	protected UserProfileService userProfileService;
	
	@Autowired
	protected MessageSource messageSource;

	@Autowired
	protected PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;
	
	@Autowired
	protected AuthenticationTrustResolver authenticationTrustResolver;
	
}
