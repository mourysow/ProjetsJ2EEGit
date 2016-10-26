package fr.oury.sow.entities;

import groovy.util.logging.Slf4j;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Etudiant implements Serializable{

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
	           
	public Etudiant() {
 
		super();
		
	}
    
	public Etudiant(Long idEtudiant) {
		super();
		this.idEtudiant = idEtudiant;
	}

	public Etudiant(String nom, String prenom, Date dateNaissance) {
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
