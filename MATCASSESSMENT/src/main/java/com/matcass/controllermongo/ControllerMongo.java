package com.matcass.controllermongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.matcass.modelmongo.EmployeeMdb;
import com.matcass.modelmongo.PersonMdb;
import com.matcass.servicemongo.ServiceMongo;

@RestController
public class ControllerMongo {
		@Autowired
		private ServiceMongo serviceMongo;
	    
		@PostMapping("/personMongo")
		@PreAuthorize("hasRole('ADMIN')")
		public PersonMdb save(@RequestBody PersonMdb person) {
			return  serviceMongo.saves(person);
		}
		@PostMapping("/employeeMongo")
		@PreAuthorize("hasRole('ADMIN')")
	    public EmployeeMdb saveall(@RequestBody EmployeeMdb  employeeMongo) {
	    	return serviceMongo.saveAll(employeeMongo);
	    }

}
