package com.matcass.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.matcass.modelmongo.PersonMdb;

@Repository
public interface PersonMongo extends MongoRepository <PersonMdb,Integer>{

	Optional<PersonMdb> findByPersonId(Integer personId);



	


}
