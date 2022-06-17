package com.matcass.modelmongo;




import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class EmployeeMdb {

		   private Integer eid;
		   private String ename;
		   private Float esalary;
		   private String role;
		   private PersonMdb personMdb;
		   private AddressMdb addressMdb;
		   
		public Integer getEid() {
			return eid;
		}
		public void setEid(Integer eid) {
			this.eid = eid;
		}
		public String getEname() {
			return ename;
		}
		public void setEname(String ename) {
			this.ename = ename;
		}
		public Float getEsalary() {
			return esalary;
		}
		public void setEsalary(Float esalary) {
			this.esalary = esalary;
		}
		public String getRole() {
			return role;
		}
		public void setRole(String role) {
			this.role = role;
		}
		public PersonMdb getPersonMdb() {
			return personMdb;
		}
		public void setPersonMdb(PersonMdb personMdb) {
			this.personMdb = personMdb;
		}
		public AddressMdb getAddressMdb() {
			return addressMdb;
		}
		public void setAddressMdb(AddressMdb addressMdb) {
			this.addressMdb = addressMdb;
		}

}
