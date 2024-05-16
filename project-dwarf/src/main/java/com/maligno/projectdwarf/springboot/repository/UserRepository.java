package com.maligno.projectdwarf.springboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maligno.projectdwarf.springboot.model.User;


public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findById(long id);
	Optional<User> findByUsername(String username);

	
}