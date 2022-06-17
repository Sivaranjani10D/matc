package com.matcass.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matcass.model.Person;


public interface PersonRepos extends JpaRepository <Person,Integer>  {

}
