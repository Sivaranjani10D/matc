package com.matcass.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.matcass.modelmongo.EmployeeMdb;

public interface EmployeeMongo extends MongoRepository <EmployeeMdb,Integer>{

}
