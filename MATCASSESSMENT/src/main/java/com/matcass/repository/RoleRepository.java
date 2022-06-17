package com.matcass.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matcass.usermodel.Erole;
import com.matcass.usermodel.Role;

public interface RoleRepository extends JpaRepository <Role,Long> {
	Optional<Role> findByName(Erole name);
}
