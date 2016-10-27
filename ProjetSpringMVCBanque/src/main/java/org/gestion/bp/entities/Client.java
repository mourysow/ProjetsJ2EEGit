package org.gestion.bp.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CLIENTS")
public class Client implements Serializable {
	
	/**
	 * @GenerateValue.
	 * Cette annotation indique que la cl� primaire est g�n�r�e de fa�on automatique
	 * lors de l�insertion en base. Sans cette annotation,la valeur de l�identifiant
	 * de la cl� primaire doit �tre affect� avant l�insertion en base.
	 * Elle est utilis�e avec une autre annotation @Id qui permet de mapper une cl� 
	 * primaire sur un champ unique. 
	 * Cet attribut peut prendre plusieurs valeurs :
     * Strategy = GenerationType.AUTO : La g�n�ration de la cl� primaire est laiss�e 
     * � l�impl�mentation.  C�est hibernate qui s�en charge et qui cr�e une s�quence unique
     * sur tout le sch�ma via la table hibernate_sequence.
     * Strategy = GenerationType. IDENTITY : La g�n�ration de la cl� primaire se fera �
     *  partir d�une Identit� propre au SGBD. Il utilise un type de colonne sp�ciale � la base 
     *  de donn�es.Exemple pour MySQL, il s�agit d�un AUTO_INCREMENT.
	 * 
	 */
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "CODE_CLI")
	 private Long              codeClient;
	
	 private String            nomClient;
	
	 private String            adresseClient;
	
	/** 
	 * mappedBy: indique donc que la classe Conmpte devra donc comporter 
	 * un attribut de type Client appel� client :
	 * mappedBy:indique que la responsabilit� de l�association est d�l�gu�e
	 * � la classe r�f�renc�e c'est � la classe Client. donc c'est dans cette classe 
	 * qu'on mettra: @JoinColumn
	 * 
	 **/
	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
	private Collection<Compte>   comptes;
	

	public Long getCodeClient() {
		return codeClient;
	}

	public void setCodeClient(Long codeClient) {
		this.codeClient = codeClient;
	}

	public String getNomClient() {
		return nomClient;
	}

	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}

	public String getAdresseClient() {
		return adresseClient;
	}

	public void setAdresseClient(String adresseClient) {
		this.adresseClient = adresseClient;
	}

	public Collection<Compte> getComptes() {
		return comptes;
	}

	public void setComptes(Collection<Compte> comptes) {
		this.comptes = comptes;
	}

	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Client(String nomClient, String adresseClient) {
		super();
		this.nomClient = nomClient;
		this.adresseClient = adresseClient;
	}

}
