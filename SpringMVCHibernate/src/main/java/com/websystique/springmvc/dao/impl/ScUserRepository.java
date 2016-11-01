package com.websystique.springmvc.dao.impl;

import org.springframework.data.jpa.repository.JpaRepository;

import com.websystique.springmvc.model.ScUser;


public interface ScUserRepository extends JpaRepository<ScUser, String> {
	

}
