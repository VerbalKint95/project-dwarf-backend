package com.maligno.projectdwarf.springboot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.maligno.projectdwarf.springboot.request.NewCommentRequest;
import com.maligno.projectdwarf.springboot.request.NewTopicRequest;
import com.maligno.projectdwarf.springboot.response.TopicListResponse;
import com.maligno.projectdwarf.springboot.response.TopicResponse;
import com.maligno.projectdwarf.springboot.response.CommentListResponse;
import com.maligno.projectdwarf.springboot.response.CommentResponse;
import com.maligno.projectdwarf.springboot.service.TopicService;
import com.maligno.projectdwarf.springboot.service.UserService;
import com.maligno.projectdwarf.springboot.exception.TopicNotFoundException;
import com.maligno.projectdwarf.springboot.exception.UserNotAuthenticatedException;
import com.maligno.projectdwarf.springboot.model.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController()
@RequestMapping("/api/topic")
public class TopicController {
	
//attributes
	private final UserService userService;
	private final TopicService topicService;
	
//operations
	@PostMapping()
	public TopicResponse createTopic(
			@RequestBody NewTopicRequest newTopicRequest
	)
	{
		User user;
		try {
			user = userService.getCurrentAuthenticatedUser();
		} catch (UserNotAuthenticatedException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User not authenticated", e);
		}
		return topicService.createTopic(newTopicRequest, user);
		
	}
	
	@GetMapping()
	public TopicListResponse getAllTopic() {
		TopicListResponse response;
		response = topicService.getAllTopics();
		return response;
	}
	
	@GetMapping("/{id}")
	public TopicResponse getTopicFromId(
			@PathVariable Long id
	){
		TopicResponse response;
		try {
			response = topicService.getTopicFromId(id);
		} catch (TopicNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Topic not found", e);
		}
		return response;
	}
	
	@GetMapping("/{id}/comment")
	public CommentListResponse getAllCommentsFromTopic(
			@PathVariable Long id
			) {
		CommentListResponse response;
		try {
			response = topicService.getAllCommentsFromTopic(id);
		} catch (TopicNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Topic not found", e);
		}
		return response;
	}
	
	@PostMapping("/{id}/comment")
	public CommentResponse addComment(
			@PathVariable Long id,
			@RequestBody NewCommentRequest request
	) {
		User user;
		CommentResponse response;
		try {
			user = userService.getCurrentAuthenticatedUser();
			response = topicService.addComment(id, request, user);
		} catch (UserNotAuthenticatedException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User not authenticated", e);
		} catch (TopicNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Topic not found", e);
		}
		
		return response;
	}
	
	@GetMapping("/search")
	public TopicResponse getTopicFromTilte(
			@RequestParam String title
	){
		TopicResponse response;
		try {
			response = topicService.getTopicFromTitle(title);
		} catch (TopicNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Topic not found", e);
		}
		return response;
	}
	

}
