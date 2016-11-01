package com.websystique.springmvc.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SC_ROLE")
public class ScRole implements Serializable {
	@Id
	private String role;
	private String description;
	
	
	public ScRole() {
		super();
	}
	
	public ScRole
	(String role, String description) {
		super();
		this.role = role;
		this.description = description;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
   
}
