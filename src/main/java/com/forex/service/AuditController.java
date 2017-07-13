package com.forex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.forex.domain.Costumers;
import com.forex.repository.AuditRepository;

@RestController
public class AuditController {
	@Autowired
	AuditRepository aud_repo;
	
	@RequestMapping(value = "/auditor/make-table", method = RequestMethod.POST)
	public int ConstructAuditTable(@RequestBody Costumers customer)
	{
		return aud_repo.AuditorRepository(customer);
	}
	
}
