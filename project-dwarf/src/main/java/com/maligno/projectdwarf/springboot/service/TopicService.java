package com.maligno.projectdwarf.springboot.service;



import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.maligno.projectdwarf.springboot.exception.TopicNotFoundException;
import com.maligno.projectdwarf.springboot.model.Comment;
import com.maligno.projectdwarf.springboot.model.Topic;
import com.maligno.projectdwarf.springboot.model.User;
import com.maligno.projectdwarf.springboot.repository.CommentRepository;
import com.maligno.projectdwarf.springboot.repository.TopicRepository;
import com.maligno.projectdwarf.springboot.request.NewCommentRequest;
import com.maligno.projectdwarf.springboot.request.NewTopicRequest;
import com.maligno.projectdwarf.springboot.response.TopicListResponse;
import com.maligno.projectdwarf.springboot.response.TopicResponse;
import com.maligno.projectdwarf.springboot.response.CommentListResponse;
import com.maligno.projectdwarf.springboot.response.CommentResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TopicService {

//attributes
	//repository
	private final TopicRepository topicRepository;
	private final CommentRepository commentRepository;



//operations
	//request operations
	public TopicResponse createTopic(NewTopicRequest request, User user) {
		var topic = Topic.builder()
				.title(request.getTitle())
				.content(request.getContent())
				.user(user)
				.creationDate(Date.valueOf(LocalDate.now()))
			.build();
		
		saveTopic(topic);
		
		TopicResponse response = createTopicResponse(topic);
		
		return response;
	}
	

	public TopicListResponse getAllTopics() {
		List<Topic> topics = findAllTopics();
		TopicListResponse response = new TopicListResponse();
		response.setTopicResponses(new ArrayList<TopicResponse>());
		for (Topic t:topics) {
			response.getTopicResponses().add(createTopicResponse(t));
		}
		return response;
	}


	public TopicResponse getTopicFromId(Long id) throws TopicNotFoundException {
		Topic topic = findTopicById(id);
		TopicResponse response = createTopicResponse(topic);
		return response;
	}


	public TopicResponse getTopicFromTitle(String title) throws TopicNotFoundException {
		Topic topic = findTopicByTitle(title);
		TopicResponse response = createTopicResponse(topic);
		return response;
	}	
	

	public CommentListResponse getAllCommentsFromTopic(Long id) throws TopicNotFoundException {
		findTopicById(id);
		List<Comment> comments = commentRepository.findByTopicId(id);
		
		CommentListResponse response = new CommentListResponse();
		response.setCommentList(new ArrayList<CommentResponse>());
		for (Comment c:comments) {
			response.getCommentList().add(createCommentResponse(c));
		}
		
		return response;
	}


	public CommentResponse addComment(long topicId, NewCommentRequest request, User user) throws TopicNotFoundException {
		var topic = findTopicById(topicId);
		var comment = Comment.builder()
				.content(request.getContent())
				.user(user)
				.topic(topic)
				.creationDate(Date.valueOf(LocalDate.now()))
			.build();
		
		saveComment(comment);
		
		CommentResponse response = createCommentResponse(comment);
		
		return response;
	}
	
	
	//response operations
	private TopicResponse createTopicResponse(Topic topic) {
		TopicResponse response = TopicResponse.builder()
				.id(topic.getId())
				.title(topic.getTitle())
				.content(topic.getContent())
				.userId(topic.getUser().getId())
				.creationDate(topic.getCreationDate().toLocalDate())
			.build();
		return response;
	}
	
	private CommentResponse createCommentResponse(Comment comment) {
		CommentResponse response = CommentResponse.builder()
				.id(comment.getId())
				.content(comment.getContent())
				.topicId(comment.getTopic().getId())
				.userId(comment.getUser().getId())
				.creationDate(comment.getCreationDate().toLocalDate())
			.build();
		return response;
	}
	
	
	//repository operations
	public List<Topic> findAllTopics(){
		List<Topic> topics = topicRepository.findAll();
		return topics;
	}
	
	public Topic findTopicById(long id) throws TopicNotFoundException {
		Optional<Topic> optionalTopic = topicRepository.findById(id);
		Topic topic = optionalTopic.orElseThrow(() -> new TopicNotFoundException());
		return topic;
	}
	
	public Topic findTopicByTitle(String title) throws TopicNotFoundException {
		Optional<Topic> optionalTopic = topicRepository.findByTitle(title);
		Topic topic = optionalTopic.orElseThrow(() -> new TopicNotFoundException());
		return topic;
	}
	
	public List<Comment> findAllCommentsByTopicID(long topicId){
		List<Comment> comments = commentRepository.findByTopicId(topicId);
		return comments;
	}
	
	private Topic saveTopic(Topic topic) {
		return topicRepository.save(topic);
	}
	
	private Comment saveComment(Comment comment) {
		return commentRepository.save(comment);
	}
	
	
}
