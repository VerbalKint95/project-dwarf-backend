package com.maligno.projectdwarf.springboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.maligno.projectdwarf.springboot.model.Topic;
import com.maligno.projectdwarf.springboot.model.User;

public interface TopicRepository extends JpaRepository<Topic, Long>{
	
//find
	//
	Optional<Topic> findById(long id);
	Optional<Topic> findByTitle(String title);
	
	//By User
	@Query("SELECT c FROM Topic c WHERE c.user.id = :userId")
    List<Topic> findByTopicId(@Param("userId") Long userId);
	Optional<Topic> findByUser(User user);
}