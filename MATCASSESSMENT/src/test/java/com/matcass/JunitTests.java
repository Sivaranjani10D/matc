package com.matcass;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import java.util.List;


import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)

class JunitTests {
	
	@Autowired
	private PersonRepos personRepos;
	
	@Autowired
	private EmployeeRepos employeeRepos;
	
	@Autowired
	private AddressRepos addressRepos;
	
	@Mock
	private PersonMongo personMongo;
	
	@Autowired
	private EmployeeMongo employeeMongo;

	@Autowired
	private AddressMongo addressMongo;
	
	
	
	@Test
	@Order(1)
	void testCreatePerson() {
		
		Person person=new Person();
		person.setPersonId(1);
		person.setPname("Shiva");
		person.setPhno(7010872554L);
	
		personRepos.save(person);
		assertNotNull(personRepos.findById(1).get());	
	}
	
	@Test
	@Order(2)
	void testReadAllPerson()
	{
		List<Person> list=personRepos.findAll();
		assertThat(list).size().isPositive();
	}
	
	@Test
	@Order(3)
	void updatePerson()
	{
		Person per=personRepos.findById(1).get();
		per.setPhno(98490527355L);
		personRepos.save(per);
		assertNotEquals(7010872554L,personRepos.findById(1).get().getPhno());	
	}
	
	@Test
	@Order(4)
	void testReadSinglePerson()
	{
		Person person=personRepos.findById(1).get();
		assertEquals(98490527355L,person.getPhno());
	}
    @Test
    @Order(5)
    void testCreatePersonMongo() {
		
		PersonMdb person=new PersonMdb();
		person.setPersonId(1);
		person.setPname("Shiva");
		person.setPhno(7010872554L);
	
		personMongo.save(person);
		//assertNotNull(personMongo.findById(1).get());	
	}
    @Test
    @Order(6)
    void testCreateEmployeeMongo() {
		
		EmployeeMdb employee=new EmployeeMdb();
		employee.setEid(1);
		employee.setEname("Shiva");
		employee.setEsalary(70000F);
		employee.setRole("Software Eng");
	
		employeeMongo.save(employee);
		//assertNotNull(personMongo.findById(1).get());	
	}
    @Test
    @Order(7)
    void testCreateAddressMongo() {
		
		AddressMdb address=new AddressMdb();
		address.setAid(1);
		address.setCity("Chennai");
		addressMongo.save(address);
		//assertNotNull(personMongo.findById(1).get());	
	}
	
	
}
