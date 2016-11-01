package com.websystique.springmvc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "SC_ETUDIANT")
public class ScEtudiant implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	private Long idEtudiant;
	@NotNull
	@Size(min=3,max=10)
	private String nom;
	@NotNull
	@Size(min=3,max=10)
	private String prenom;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dateNaissance;
	           
	public ScEtudiant() {
 
		super();
		
	}
    
	public ScEtudiant(Long idEtudiant) {
		super();
		this.idEtudiant = idEtudiant;
	}

	public ScEtudiant(String nom, String prenom, Date dateNaissance) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
	}

	
	public Long getIdEtudiant() {
		return idEtudiant;
	}

	public void setIdEtudiant(Long idEtudiant) {
		this.idEtudiant = idEtudiant;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	
	
}
