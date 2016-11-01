package fr.oury.sow.service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.oury.sow.dao.EtudiantRpository;
import fr.oury.sow.entities.Etudiant;
import fr.oury.sow.service.IEtudiantRestRepository;

@RestController
public class EtudiantRestService implements IEtudiantRestRepository {
	//Logger log = null;
	
	@Autowired
	private EtudiantRpository etudiantRpository;
	/*
	 * @valid: pour valider l'inscription voir annotation dans 
	 * entity Etudiant 	@NotNull@Size(min=3,max=10)
	 * bindingResult: si pendant la validation ya des erreur on les genere  dans ce object
	 * Au lieu de:  public Etudiant mettons public Object
	 * la methode sava aulieu de retourner que etudiant elle retourne soit l'etudiant soit une erreur
	 */
	/* (non-Javadoc)
	 * @see fr.oury.sow.service.IEtudiantRestRepository#saveEtudiant(fr.oury.sow.entities.Etudiant, org.springframework.validation.BindingResult)
	 */
	@Override
	@Secured(value={"ROLE_ADMIN","ROLE_SCOLARITE"})
	@RequestMapping(value = "/etudiants", method = RequestMethod.POST)
	public Object saveEtudiant(@RequestBody @Valid Etudiant e, BindingResult bindingResult){		
		//log.debug("saveEtudiant(); etudiant {}", e);
	
	 if(bindingResult.hasErrors()){
			Map<String, Object>errors = new HashMap<>();
			errors.put("errors", true);
		
			for(FieldError fe: bindingResult.getFieldErrors()){
				errors.put(fe.getField(), fe.getDefaultMessage());				
			}
		  return errors;			
		}  
		return etudiantRpository.save(e);
	}
	
	
	/* (non-Javadoc)
	 * @see fr.oury.sow.service.IEtudiantRestRepository#listeEtudiants(int, int)
	 */
	@Override
	@Secured(value={"ROLE_ADMIN","ROLE_SCOLARITE","ROLE_PROF", "ROLE_ETUDIANT"})
	@RequestMapping(value = "/etudiants")
	public Page<Etudiant> listeEtudiants(int page, int size){
		return etudiantRpository.findAll(new PageRequest(page, size));
	}

	/* (non-Javadoc)
	 * @see fr.oury.sow.service.IEtudiantRestRepository#getLogUser(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	@RequestMapping(value = "/getLogUser")
	public Map<String, Object> getLogUser(HttpServletRequest httpServletRequest){
		
		HttpSession httpSession = httpServletRequest.getSession();
		SecurityContext securityContext = (SecurityContext)
				httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
		
		String username = securityContext.getAuthentication().getName();
		List<String>roles = new ArrayList<>();
		for(GrantedAuthority ga:securityContext.getAuthentication().getAuthorities()){
			roles.add(ga.getAuthority());
		    }
		Map<String, Object>params = new HashMap<>();
		params.put("username", username);
		params.put("roles", roles);
		return params;
		}	
    }
