package com.maligno.projectdwarf.springboot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.PathVariable;

import com.maligno.projectdwarf.springboot.exception.UserNotAuthenticatedException;
import com.maligno.projectdwarf.springboot.exception.UserNotFoundException;
import com.maligno.projectdwarf.springboot.response.UserResponse;
import com.maligno.projectdwarf.springboot.service.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController()
@RequestMapping("/api/user")
public class UserController {
	
//attributes
	private final UserService userService;
	
	@GetMapping("/role")
	public ResponseEntity<String> getUserRole(){
		String role;
		try {
			role = userService.getCurrentAuthenticatedUser().getRole().name();
		} catch (UserNotAuthenticatedException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User not authenticated", e);
		}
		return ResponseEntity.ok(role);
	}
	
	@GetMapping("/id")
	public ResponseEntity<Long> getUserId(){
		long id;
		try {
			id = userService.getCurrentAuthenticatedUser().getId();
		} catch (UserNotAuthenticatedException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User not authenticated", e);
		}
		return ResponseEntity.ok(id);
	}

	@GetMapping("/{id}")
	public UserResponse getUserById(
		@PathVariable Long id
	){
		UserResponse response;
		try {
			response = userService.getUserById(id);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", e);
		}
		return response;
	}
	
}
