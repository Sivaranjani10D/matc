package com.matcass.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.matcass.config.JwtTokenUtil;
import com.matcass.dto.EmployeeDto;
import com.matcass.dto.PersonDto;
import com.matcass.exception.ServiceException;
import com.matcass.model.Employee;
import com.matcass.model.Person;

import com.matcass.modelmongo.EmployeeMdb;
import com.matcass.modelmongo.PersonMdb;
import com.matcass.payload.JwtResponses;
import com.matcass.payload.LoginRequest;
import com.matcass.payload.MessageResponse;
import com.matcass.payload.SignUp;
import com.matcass.repository.RoleRepository;
import com.matcass.repository.UserRepository;
import com.matcass.responsemodel.ResponseModel;
import com.matcass.responsemodel.ResponseModelData;
import com.matcass.security.UserDetailsImpl;
import com.matcass.service.JwtUserDetailsService;
import com.matcass.usermodel.Erole;
import com.matcass.usermodel.Role;
import com.matcass.usermodel.UserDao;


@RestController
@CrossOrigin
@RequestMapping(method = RequestMethod.GET, value = "/api/MatcAssessment")
public class JwtAuthenticationController {
	
	Logger logger = LoggerFactory.getLogger(JwtAuthenticationController.class);

	@Autowired
	private PasswordEncoder bCryptEncoder;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UserRepository repository;

	@Autowired
	private JwtUserDetailsService userDetailsService;
	@Autowired
	private RoleRepository roleRepository;

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser( @RequestBody SignUp signUpRequest) {
		if (repository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}
		if (repository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}
		// Create new user's account
		UserDao user = new UserDao(signUpRequest.getUsername(),
							 signUpRequest.getEmail(),
							 bCryptEncoder.encode(signUpRequest.getPassword()));
		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();
		if (strRoles == null) {
			Role userRole= roleRepository.findByName(Erole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(Erole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);
					break;
				case "mod":
					Role modRole = roleRepository.findByName(Erole.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);
					break;
				default:
					Role useRole = roleRepository.findByName(Erole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(useRole);
				}
			});
		}
		user.setRoles(roles);
		repository.save(user);
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

   @PostMapping("/authenticate")
	
	public ResponseEntity<?>createAuthenticationToken(@RequestBody LoginRequest auth) throws Exception{
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(auth.getUsername(), auth.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtTokenUtil.generateToken(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		return ResponseEntity.ok(new JwtResponses(jwt,
				userDetails.getId(),
				userDetails.getUsername(),
				userDetails.getEmail(),
				roles));
	}
   @GetMapping("/getemployee")
	@PreAuthorize("hasRole('ADMIN')")
   
   public ResponseEntity<?> get(Pageable pageble) {
       ResponseModelData responseModels=new ResponseModelData(HttpStatus.OK,userDetailsService.findAll(pageble),"Success","No Error",new Date());	
       return new ResponseEntity<ResponseModelData>(responseModels,HttpStatus.OK);
   };
	   
	   
   
		
//	@GetMapping("/getemployee")
//	@PreAuthorize("hasRole('ADMIN')")
//	public ResponseEntity<?> get(Employee employee) {
//		ResponseModelData responseModels = new ResponseModelData(HttpStatus.OK, userDetailsService.getAll(employee), "Success", "No error",
//				new Date());
//		return new ResponseEntity<ResponseModelData>(responseModels,HttpStatus.OK);
//	}
	
	@GetMapping("/getemployee/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN') ")
	public ResponseEntity<?> get(@PathVariable Integer id) {
		ResponseModelData responseModel = new ResponseModelData(HttpStatus.OK, userDetailsService.get(id), "Success", "No error",
					new Date());
			return new ResponseEntity<ResponseModelData>(responseModel,HttpStatus.OK);
		} 
	
	//post the data for person in mysql and mongodb
	@PostMapping("/person")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> save(@RequestBody PersonDto person) {
//		logger.info("Info message");
//		System.out.println("Person id not found");
		Person per=new Person();
		BeanUtils.copyProperties(person,per);
		userDetailsService.savePerson(per);
		
		
		PersonMdb personmongo =new PersonMdb();
	    userDetailsService.saveMdb(personmongo);
		BeanUtils.copyProperties(person,personmongo);

		ResponseModel responseModel=new ResponseModel(HttpStatus.OK,userDetailsService.savePerson(per)
				,userDetailsService.saveMdb(personmongo),"Success","No Error",new Date());
		return new ResponseEntity<ResponseModel>(responseModel,HttpStatus.OK);
		
	}
	
	@PostMapping("/employee")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> save(@RequestBody EmployeeDto employee) {
		Employee emp=new Employee();
		BeanUtils.copyProperties(employee, emp);
		userDetailsService.save(employee);
		
		EmployeeMdb employeemongo =new EmployeeMdb();
		BeanUtils.copyProperties(employeemongo, emp);
		userDetailsService.saveall(employee);	
ResponseModel responseModel=new ResponseModel(HttpStatus.OK,userDetailsService.save(employee)
				,userDetailsService.saveall(employee),"Success","No Error",new Date());
		return new ResponseEntity<ResponseModel>(responseModel,HttpStatus.OK);
	}
	
	
	@GetMapping("/logging")
    public String getEmployees() {
//		logger.info("[getMessage] info message");
//		logger.warn("[getMessage] warn message");
//		logger.error("[getMessage] error message");
//		logger.debug("[getMessage] debug message");
//		logger.trace("[getMessage] trace message");
//	 	System.out.println(10/0);
		return "open console to check log messages.";
       
    }
	@GetMapping("/matcfirstservice")
	public String firstService() {
		
		return "<b>Show Method of MatcAssessment first service</b>";
	}
	
	@GetMapping("/tracingjaeger")
	public String tracing() {
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8082/matcsecondservice", String.class);
		String res = response.getBody();
		return "<b>"+res+"</b>";
	}
	
//	@GetMapping("/hello")
//    public String helloWorld() {
//        return userDetailsService.hello();
//    }
//	
}
//	@PutMapping("/employee/{id}")
//	public ResponseEntity<String> update(@RequestBody EmployeeDto employee, @PathVariable Integer id) {
//		try {
//			userDetailsService.save(employee);
//			return new ResponseEntity<>("Success!!!", HttpStatus.OK);
//		} catch (NoSuchElementException e) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//	}
//	@DeleteMapping("/employee/{id}")
//	public ResponseEntity<?> delete(@PathVariable Integer id) {
//		try {
//			
//		userDetailsService.delete(id);
//		return new ResponseEntity<>("Success!!!",HttpStatus.OK);
//		}catch(ServiceException e) {
//			ServiceException service=new ServiceException(e.getErrCode(),e.getErrMsg());
//		return new ResponseEntity<>(service,HttpStatus.NOT_FOUND);
//		}
	//}

