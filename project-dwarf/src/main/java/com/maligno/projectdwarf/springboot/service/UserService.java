package com.maligno.projectdwarf.springboot.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.maligno.projectdwarf.springboot.exception.UserNotAuthenticatedException;
import com.maligno.projectdwarf.springboot.model.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService{

	//repository
	
	//other services
	
	//operations
	public User getCurrentAuthenticatedUser() throws UserNotAuthenticatedException {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    if (authentication == null || !authentication.isAuthenticated()) {
	        throw new UserNotAuthenticatedException();
	    }

	    return (User) authentication.getPrincipal();
	}
}

