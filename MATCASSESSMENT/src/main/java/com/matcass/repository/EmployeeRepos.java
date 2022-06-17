package com.matcass.repository;


import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.matcass.model.Address;
import com.matcass.model.Employee;
import com.matcass.model.Person;

public interface EmployeeRepos extends PagingAndSortingRepository <Employee,Integer> {

	void save(Person person);

	void save(List<Address> list);

	
}
