package com.matcass.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matcass.usermodel.UserDao;



public interface UserRepository extends JpaRepository <UserDao,Long>
{
	Optional<UserDao> findByUsername(String username);
	  Boolean existsByUsername(String username);
	  Boolean existsByEmail(String email);
	
}
