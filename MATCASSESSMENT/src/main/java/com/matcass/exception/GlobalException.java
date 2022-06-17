package com.matcass.exception;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalException {
	
	@ExceptionHandler (ServiceException.class)
	public ResponseEntity<String>handleServiceException(ServiceException service){
		return new ResponseEntity<String>("Please Send a Proper Name",HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String>handleNoSuchElementException(NoSuchElementException service)
	{
		return new ResponseEntity<String>("Dosen't Exists Employee Details",HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(AlreadyExistException.class)
	public ResponseEntity<String>AlreadyExistException(AlreadyExistException service)
	{
		return new ResponseEntity<String>("Id already exist",HttpStatus.BAD_REQUEST);
	}
}
