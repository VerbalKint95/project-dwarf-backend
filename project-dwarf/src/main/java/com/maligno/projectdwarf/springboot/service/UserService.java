package com.maligno.projectdwarf.springboot.service;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.maligno.projectdwarf.springboot.exception.UserNotAuthenticatedException;
import com.maligno.projectdwarf.springboot.exception.UserNotFoundException;
import com.maligno.projectdwarf.springboot.model.User;
import com.maligno.projectdwarf.springboot.repository.UserRepository;
import com.maligno.projectdwarf.springboot.response.UserResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService{

//attributes
	//repository
	private final UserRepository userRepository;
	//other services
	
//operations
	public User getCurrentAuthenticatedUser() throws UserNotAuthenticatedException {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    if (authentication == null || !authentication.isAuthenticated()) {
	        throw new UserNotAuthenticatedException();
	    }

	    return (User) authentication.getPrincipal();
	}

    public UserResponse getUserById(Long id) throws UserNotFoundException {
        User user = findUserById(id);
		UserResponse response = createUserResponse(user);
		return response;
    }


	//response operations
	private UserResponse createUserResponse(User user) {
		UserResponse response = UserResponse.builder()
				.id(user.getId())
				.username(user.getUsername())
				.role(user.getRole())
			.build();
		return response;
	}

	//repository operations
	private User findUserById(Long id) throws UserNotFoundException {
		Optional<User> optionalUser = userRepository.findById(id);
		User user = optionalUser.orElseThrow(() -> new UserNotFoundException());
		return user;
	}
}

