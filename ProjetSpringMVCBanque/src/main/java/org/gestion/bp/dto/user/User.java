package org.gestion.bp.dto.user;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.gestion.bp.model.UserRole;
import org.springframework.security.core.userdetails.UserDetails;


public class User implements UserDetails {

	private static final long	serialVersionUID	= -4290995900291541517L;

	public User() {
	}

	public User(String uuid) {
		this.uuid = uuid;
	}

	private Long				id;

	@NotNull
	private String				uuid;

	private boolean				anonymousUser;

	@NotNull
	private String				userLogin;

	@NotNull
	private String				password;

	private long				expires;

	@NotNull
	private boolean				accountExpired;

	@NotNull
	private boolean				accountLocked;

	@NotNull
	private boolean				credentialsExpired;

	@NotNull
	private boolean				accountEnabled;

	private String				newPassword;

	private Set<UserAuthority>	authorities;
	/**
	 * [STAGE] indique si l'utilisation demande une authentification pour les vélo
	 */
	private boolean				veloUser;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	@Override
	@JsonIgnore
	public String getPassword() {
		return password;
	}

	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}

	@JsonIgnore
	public String getNewPassword() {
		return newPassword;
	}

	@JsonProperty
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	@Override
	@JsonIgnore
	public Set<UserAuthority> getAuthorities() {
		return authorities;
	}

	// Use Roles as external API
	public Set<UserRole> getRoles() {
		Set<UserRole> roles = EnumSet.noneOf(UserRole.class);
		if (authorities != null) {
			for (UserAuthority authority : authorities) {
				roles.add(UserRole.valueOf(authority));
			}
		}
		return roles;
	}

	public void setRoles(Set<UserRole> roles) {
		for (UserRole role : roles) {
			grantRole(role);
		}
	}

	public void grantRole(UserRole role) {
		if (authorities == null) {
			authorities = new HashSet<UserAuthority>();
		}
		authorities.add(role.asAuthorityFor(this));
	}

	public void revokeRole(UserRole role) {
		if (authorities != null) {
			authorities.remove(role.asAuthorityFor(this));
		}
	}

	public boolean hasRole(UserRole role) {
		return authorities.contains(role.asAuthorityFor(this));
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {
		return !accountExpired;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {
		return !accountLocked;
	}

	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		return !credentialsExpired;
	}

	@Override
	@JsonIgnore
	public boolean isEnabled() {
		return !accountEnabled;
	}

	public long getExpires() {
		return expires;
	}

	public void setExpires(long expires) {
		this.expires = expires;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + ": " + getUsername();
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public boolean isAnonymousUser() {
		return anonymousUser;
	}

	public void setAnonymousUser(boolean anonymousUser) {
		this.anonymousUser = anonymousUser;
	}

	@Override
	@JsonIgnore
	public String getUsername() {
		return getUuid();
	}

	public boolean isVeloUser() {
		return veloUser;
	}

	public void setVeloUser(boolean veloUser) {
		this.veloUser = veloUser;
	}
}
