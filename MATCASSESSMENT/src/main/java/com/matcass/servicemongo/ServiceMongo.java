package com.matcass.servicemongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matcass.modelmongo.EmployeeMdb;
import com.matcass.modelmongo.PersonMdb;
import com.matcass.repository.EmployeeMongo;
import com.matcass.repository.PersonMongo;
@Service
public class ServiceMongo {
	@Autowired
	private PersonMongo personMongo;
	@Autowired
	private EmployeeMongo employeeMongo;
	
	public EmployeeMdb saveAll(EmployeeMdb employeeMdb) {
		return employeeMongo.save(employeeMdb);
	}
	public PersonMdb saves(PersonMdb personMdb) {
		return personMongo.save(personMdb);
	}
	
	

}
