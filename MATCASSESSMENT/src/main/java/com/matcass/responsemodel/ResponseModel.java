package com.matcass.responsemodel;

import java.util.Date;

import org.springframework.http.HttpStatus;

public class ResponseModel {
	private HttpStatus status;
	private Object data;
	private Object data1;
	private String message;
	private String errorMessage;
	private Date date;
	
	
	public Object getData1() {
		return data1;
	}
	public void setData1(Object data1) {
		this.data1 = data1;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public ResponseModel(HttpStatus status, Object data,Object data1, String message, String errorMessage, Date date) {
		super();
		this.status = status;
		this.data = data;
		this.data1 = data1;
		this.message = message;
		this.errorMessage = errorMessage;
		this.date = date;
	}
}
