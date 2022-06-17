package com.matcass.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;



import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.matcass.dto.EmployeeDto;
import com.matcass.exception.ServiceException;
import com.matcass.model.Address;
import com.matcass.model.Employee;

import com.matcass.model.Person;

import com.matcass.modelmongo.AddressMdb;
import com.matcass.modelmongo.EmployeeMdb;
import com.matcass.modelmongo.PersonMdb;
import com.matcass.repository.AddressMongo;
import com.matcass.repository.AddressRepos;
import com.matcass.repository.EmployeeMongo;
import com.matcass.repository.EmployeeRepos;
import com.matcass.repository.PersonMongo;
import com.matcass.repository.PersonRepos;

import com.matcass.repository.UserDaoRepos;
import com.matcass.repository.UserRepository;
import com.matcass.security.UserDetailsImpl;
import com.matcass.usermodel.UserDao;


@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	
	@Autowired
	private UserDaoRepos userDao;
	
	@Autowired
	private EmployeeRepos repos;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AddressRepos addressRepos;
    
	@Autowired
	private PersonRepos per;
	
	@Autowired
	private PersonMongo personMongo;
	
	@Autowired
	private EmployeeMongo employeeMongo;
	
	@Autowired
	private AddressMongo addressMongo;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;

	//mysql
	public Person savePerson(Person person)
	{
		return per.save(person);
	}
	
	public PersonMdb saveMdb(PersonMdb mdb)
	{
		return personMongo.save(mdb);
	}

	public Employee save(EmployeeDto emp) {
		Optional<Person> person=per.findById(emp.getPersonId());
	     if(person.isPresent()) {
			Person per=person.get();
			Employee employee=new Employee();
			employee.setPerson(per);
			BeanUtils.copyProperties(emp,employee);
			repos.save(employee);
			
		    Address address =new Address();
			address.setAid(emp.getAid());
			address.setCity(emp.getCity());
			address.setEid(emp.getEid());
			BeanUtils.copyProperties(emp,address);
			addressRepos.save(address);
			
			if(emp.getCity().isEmpty()|| emp.getRole().length()==0) {
				throw new ServiceException();
			}
			//BeanUtils.copyProperties(address, employee);
			return employee;
			//return employeeRepository.save(employee);
		}else {
			return null;
		}
	}

	public EmployeeMdb saveall(EmployeeDto emp) {
		
         Optional<PersonMdb> person=personMongo.findByPersonId(emp.getPersonId());
		if(person.isPresent()) {
			PersonMdb per=person.get();
			EmployeeMdb employeemongo=new EmployeeMdb();
			employeemongo.setPersonMdb(per);
			BeanUtils.copyProperties(emp,employeemongo);
			
			AddressMdb addressmongo =new AddressMdb();
		    addressmongo.setAid(emp.getAid());
            addressmongo.setCity(emp.getCity());
			addressmongo.setEid(emp.getEid());
			employeemongo.setAddressMdb(addressmongo);
			BeanUtils.copyProperties(emp,addressmongo);
			addressMongo.save(addressmongo);
		    employeeMongo.save(employeemongo);
		    return employeemongo;
		}else {
			return null;
		}
	}

	public UserDao save(UserDao user)
	{
		if(user.getUsername().isEmpty()||user.getEmail().length()==0)
		{
			throw new ServiceException();
		}
		return userDao.save(user);
	}
	
//	public List<Employee> getAll(Employee employee)
//    {
//    	List<Employee> list=(Page<Employee>) repos.findAll();
//    	
//    	return (Page<Employee>) repos.findAll(pageable);
//    }
 
	public 	Page<Employee> findAll(Pageable pageble) {
		
		Employee employee=new Employee();
		Page<Employee>employees=repos.findAll(pageble);
		//employee.setData(employees);
		return employees;
	}
 
    
    public Employee get(Integer id)
    {
    try {
	     return repos.findById(id).get();
			
	}catch(java.util.NoSuchElementException e) {
	throw new ServiceException("607","Given employee id is doesnt exit"+e.getMessage());
	}
}
    
    public void delete(Integer id) {
		try {
		     repos.deleteById(id);
		
		}catch(java.util.NoSuchElementException e) {
		throw new ServiceException("607","Given employee id is doesnt exit"+e.getMessage());
		}
	}
    @Override
	  @Transactional
	  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    UserDao user = userRepository.findByUsername(username)
	        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
	    return UserDetailsImpl.build(user);
	  }
    
    

	}

