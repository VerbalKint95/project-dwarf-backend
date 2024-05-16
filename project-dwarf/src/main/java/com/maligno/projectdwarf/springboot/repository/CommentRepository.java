package com.maligno.projectdwarf.springboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.maligno.projectdwarf.springboot.model.Comment;
import com.maligno.projectdwarf.springboot.model.Topic;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	Optional<Comment> findById(long id);
	Optional<Comment> findByTopic(Topic topic);
	
	@Query("SELECT c FROM Comment c WHERE c.topic.id = :topicId")
    List<Comment> findByTopicId(@Param("topicId") Long topicId);
}
