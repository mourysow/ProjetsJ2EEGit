package com.websystique.springmvcEmp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import com.websystique.springmvcEmp.service.EmployeeService;


public abstract class AbstractControllerEmp {
	
	@Autowired
	protected MessageSource messageSource;
	
	@Autowired
	protected EmployeeService employeeService;

	
	
}
