package com.matcass.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.domain.Page;

@Entity
@Table(name = "employee")
public class Employee {

	@Id
	private Integer eid;
	private String ename;
	private Float esalary;
	private String role;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "personId", nullable = true)
	private Person person;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "eid", referencedColumnName = "eid")
	List<Address> list = new ArrayList<>();

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) 
	{
		this.person = person;
	}
	
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

	public List<Address> getList() {
		return list;
	}

	public void setList(List<Address> list) {
		this.list = list;
	}

	public Employee(String ename, Float esalary, String role, Person person, List<Address> list) {
		super();
		this.ename = ename;
		this.esalary = esalary;
		this.role = role;
		this.list = list;
		this.person = person;
	}

	public Employee() {

	}

//public void setData(Page<Employee> employees) {
	// TODO Auto-generated method stub

}
