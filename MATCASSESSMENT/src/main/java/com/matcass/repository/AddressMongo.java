package com.matcass.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.matcass.modelmongo.AddressMdb;

public interface AddressMongo extends MongoRepository <AddressMdb,Integer> {

}
