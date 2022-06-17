package com.matcass;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.matcass.model.Employee;
import com.matcass.model.Person;
import com.matcass.repository.EmployeeRepos;
import com.matcass.service.JwtUserDetailsService;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
 class MatcassessmentApplicationTests {
	
	
	
	
	
	@Mock
	EmployeeRepos repos;
	
	
	
	
	@Test
	Employee testSaveMock() 
	{
	 Employee employee =new Employee();	
	 Person per=new Person();
	 employee.setEid(1);
	 employee.setEname("siva");
	 employee.setEsalary(10000F);
	 employee.setRole("admin");
	 employee.setPerson(per);
	 return repos.save(employee);
	 
	
	 
	 }
	
	
//	//@Test
//	public void testmockito() throws Exception
//	{
//		 when(userDetailsService.hello()).thenReturn("hello");
//		mock.perform(get("/hello"))
//        .andExpect(status().isOk())
//        .andExpect(content().string("Hello World"));
//		verify(userDetailsService).hello();
//
//	    
//	}
	
	}
