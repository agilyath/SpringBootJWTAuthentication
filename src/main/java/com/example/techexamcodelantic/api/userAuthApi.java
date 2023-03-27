package com.example.techexamcodelantic.api;

import jakarta.validation.Valid;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.techexamcodelantic.jwt.tokenUtil;
import com.example.techexamcodelantic.models.User;
import com.example.techexamcodelantic.models.UserLogin;
import com.example.techexamcodelantic.repository.UserLoginDetailsRepository;

@RestController
public class userAuthApi {

    @Autowired AuthenticationManager authManager;
	@Autowired tokenUtil jwtUtil;
    @Autowired private UserLoginDetailsRepository loginDetailsRepository;
	
	@PostMapping("/auth/login")
	public ResponseEntity<?> login(@RequestBody @Valid authRequest request) {
		try {
			Authentication authentication = authManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							request.getEmail(), request.getPassword())
			);
			
			User user = (User) authentication.getPrincipal();
			String accessToken = jwtUtil.generateAccessToken(user);
			authResponse response = new authResponse(user.getUsername(), accessToken);
            UserLogin newloginDetails = new UserLogin(request.getEmail(), LocalDateTime.now().toString());
            loginDetailsRepository.save(newloginDetails);
			
			return ResponseEntity.ok().body(response);
			
		} catch (BadCredentialsException ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}
    
}
