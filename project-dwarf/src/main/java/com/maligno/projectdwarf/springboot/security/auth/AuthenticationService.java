package com.maligno.projectdwarf.springboot.security.auth;

import java.security.SecureRandom;
import java.util.Base64;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.maligno.projectdwarf.springboot.model.User;
import com.maligno.projectdwarf.springboot.repository.UserRepository;
import com.maligno.projectdwarf.springboot.security.Role;
import com.maligno.projectdwarf.springboot.security.config.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

	private final UserRepository repository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;

	public AuthenticationResponse register(RegisterRequest request) {
		var salt = generateSalt();
		var user = User.builder()
				.username(request.getUsername())
				.salt(salt)
				.password(passwordEncoder.encode(request.getPassword() + salt))
				.role(Role.USER)
				.build();
		repository.save(user);
		var jwtToken = jwtService.generateToken(user);
		return AuthenticationResponse.builder()
				.token(jwtToken)
				.build();
	}
	
	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		var user = repository.findByUsername(request.getUsername()).orElseThrow();
		
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getUsername(),
						request.getPassword() + user.getSalt()
						)
		);
		
		var jwtToken = jwtService.generateToken(user);
		return AuthenticationResponse.builder()
				.token(jwtToken)
				.build();
	}

	private String generateSalt() {
        int saltLength = 16; 

        byte[] salt = new byte[saltLength];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(salt);

        String encodedSalt = Base64.getEncoder().encodeToString(salt);

        return encodedSalt;
    }
}
