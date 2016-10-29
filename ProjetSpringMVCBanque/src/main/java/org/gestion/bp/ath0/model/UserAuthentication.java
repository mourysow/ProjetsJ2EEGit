package org.gestion.bp.ath0.model;

import java.util.Collection;

import org.gestion.bp.dto.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;


public class UserAuthentication implements Authentication {

	private static final long	serialVersionUID	= 7299101011382111175L;
	private final User			user;
	private boolean				authenticated		= true;

	public UserAuthentication(User user) {
		this.user = user;
	}

	@Override
	public String getName() {
		return user.getUsername();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return user.getAuthorities();
	}

	@Override
	public Object getCredentials() {
		return user.getPassword();
	}

	@Override
	public User getDetails() {
		return user;
	}

	@Override
	public Object getPrincipal() {
		return user.getUsername();
	}

	@Override
	public boolean isAuthenticated() {
		return authenticated;
	}

	@Override
	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}
}
