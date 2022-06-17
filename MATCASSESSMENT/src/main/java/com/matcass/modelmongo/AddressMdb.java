package com.matcass.modelmongo;


import org.springframework.data.mongodb.core.mapping.Document;
@Document
public class AddressMdb {

    private Integer aid;
	 private String city;
	 private Integer eid;
	 
	public Integer getEid() {
		return eid;
	}
	public void setEid(Integer eid) {
		this.eid = eid;
	}
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public AddressMdb(Integer aid, String city) {
		super();
		this.aid = aid;
		this.city = city;
	}
	public AddressMdb()
	{
		
	}

}
