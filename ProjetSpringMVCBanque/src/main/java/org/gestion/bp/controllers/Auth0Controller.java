package org.gestion.bp.controllers;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Renvoie OK si la couche présentation fonctionne correctement et KO sinon
 * 
 * @author JEANFRG
 * 
 */
@Controller
@RequestMapping("auth0")
@Slf4j
public class Auth0Controller extends AbstractFrontController {

	@RequestMapping(value = "/index.html", method = RequestMethod.GET)
	public String index(Model model) {
		//log.debug("INFO ;  auth0/index.html ;");
		return "auth0/index";
	}
}
