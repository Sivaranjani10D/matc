package com.matcass.modelmongo;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class PersonMdb {

	private Integer personId;
	private String pname;
	private long phno;
	
	
	
	public Integer getPersonId() {
		return personId;
	}



	public void setPersonId(Integer personId) {
		this.personId = personId;
	}



	public String getPname() {
		return pname;
	}



	public void setPname(String pname) {
		this.pname = pname;
	}



	public long getPhno() {
		return phno;
	}



	public void setPhno(long phno) {
		this.phno = phno;
	}



	public PersonMdb(String pname, long phno) {
		super();
		this.pname = pname;
		this.phno = phno;
	}



	public PersonMdb()
	{
		
	}
}
