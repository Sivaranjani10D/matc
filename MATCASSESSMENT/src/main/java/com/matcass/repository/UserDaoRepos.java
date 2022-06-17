package com.matcass.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matcass.usermodel.UserDao;




public interface UserDaoRepos extends JpaRepository <UserDao,Integer>  {

	UserDao findByUsername(String username);

}
