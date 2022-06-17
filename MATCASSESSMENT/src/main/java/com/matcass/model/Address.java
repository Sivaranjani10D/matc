package com.matcass.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

	@Entity
	@Table(name="address")
	public class Address {
		 @Id
	     private Integer aid;
		 private String city;
		 private Integer eid;
			
		
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


		public Integer getEid() {
			return eid;
		}


		public void setEid(Integer eid) {
			this.eid = eid;
		}


		public Address() {
			
		 }
	}

