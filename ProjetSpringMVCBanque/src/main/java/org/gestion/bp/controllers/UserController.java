package org.gestion.bp.controllers;

import org.gestion.bp.ath0.model.UserAuthentication;
import org.gestion.bp.dto.user.User;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController extends AbstractFrontController {

	@RequestMapping(value = "api/users/current", method = RequestMethod.GET)
	@ResponseBody
	public User getCurrent() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof UserAuthentication) {
			return ((UserAuthentication) authentication).getDetails();
		}
		return new User(authentication.getName()); //anonymous user support
	}

	@Secured({ ROLE_DEFAULT, ROLE_USER })
	@RequestMapping(value = "api/pingSecured", method = RequestMethod.GET)
	@ResponseBody
	public String pingSecured() {
		return "OK"; //anonymous user support
	}

}
