package org.gestion.bp.controllers;

import lombok.extern.slf4j.Slf4j;

import org.gestion.bp.service.IBanqueService;
import org.springframework.beans.factory.annotation.Autowired;


@Slf4j
public abstract class AbstractFrontController {
	
	protected static final String		ROLE_USER		= "ROLE_USER";
	protected static final String		ROLE_DEFAULT	= "ROLE_DEFAULT";
 
	@Autowired
	protected  IBanqueService           banqueService;

}
