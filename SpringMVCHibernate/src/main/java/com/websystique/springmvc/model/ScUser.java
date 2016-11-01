package com.websystique.springmvc.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "SC_USER")
public class ScUser implements Serializable {
	@Id
	private String username;
	private String password;
	private boolean actived;
	@ManyToMany
	@JoinTable(name = "SC_USERS_ROLES")
	private Collection<ScRole> roles;

	/* 
	 creation de la table nom au choix ici  USERS_ROLES 
	Cette tables aura 2 colonnes
	1 colone user_username = colone des usernames de la table username
	1 colone roles_role: il forme ce nom en concatenant les 2 attributs communs 
	aux 2 classes(donc aux 2 tables). le nom des 2 attributs communs qui fait la correspondance
    */
	public ScUser() {
		super();
	}

	public ScUser(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public ScUser(String username, String password, boolean actived) {
		super();
		this.username = username;
		this.password = password;
		this.actived = actived;
	}
	
	

	public ScUser(String username, Collection<ScRole> roles) {
		super();
		this.username = username;
		this.roles = roles;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActived() {
		return actived;
	}

	public void setActived(boolean actived) {
		this.actived = actived;
	}

	public Collection<ScRole> getRoles() {
		return roles;
	}

	public void setRoles(Collection<ScRole> roles) {
		this.roles = roles;
	}

}