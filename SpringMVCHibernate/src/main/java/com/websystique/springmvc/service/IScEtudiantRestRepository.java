package com.websystique.springmvc.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;

import com.websystique.springmvc.model.ScEtudiant;

import javax.servlet.http.HttpServletRequest;

public interface IScEtudiantRestRepository {

	/*
	 * @valid: pour valider l'inscription voir annotation dans 
	 * entity Etudiant 	@NotNull@Size(min=3,max=10)
	 * bindingResult: si pendant la validation ya des erreur on les genere  dans ce object
	 * Au lieu de:  public Etudiant mettons public Object
	 * la methode sava aulieu de retourner que etudiant elle retourne soit l'etudiant soit une erreur
	 */
	public  Object saveEtudiant(ScEtudiant e, BindingResult bindingResult);

	public Page<ScEtudiant> listeEtudiants(int page, int size);

	public  Map<String, Object> getLogUser(
			HttpServletRequest httpServletRequest);

}