package org.gestion.bp.service.impl;

import org.gestion.bp.dao.IBanqueDao;
import org.springframework.beans.factory.annotation.Autowired;


public class AbstractService {
    
	@Autowired
	protected IBanqueDao       dao;
	
}
