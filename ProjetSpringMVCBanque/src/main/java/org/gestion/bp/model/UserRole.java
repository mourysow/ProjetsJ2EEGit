package org.gestion.bp.model;

import org.gestion.bp.dto.user.UserAuthority;
import org.gestion.bp.dto.user.User;


public enum UserRole {
	USER, ADMIN, DEFAULT, USER_VELO;

	public UserAuthority asAuthorityFor(final User user) {
		final UserAuthority authority = new UserAuthority();
		authority.setAuthority("ROLE_" + toString());
		authority.setUser(user);
		return authority;
	}

	public static UserRole valueOf(final UserAuthority authority) {
		switch (authority.getAuthority()) {
			case "ROLE_USER":
				return USER;
			case "ROLE_ADMIN":
				return ADMIN;
			case "ROLE_USER_VELO":
				return USER_VELO;
			case "ROLE_DEFAULT":
				return DEFAULT;
			default:
				throw new IllegalArgumentException("No role defined for authority: " + authority.getAuthority());

		}
	}
}
