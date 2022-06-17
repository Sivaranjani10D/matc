package com.matcass.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matcass.model.Address;


public interface AddressRepos extends JpaRepository <Address,Integer>  {

}
