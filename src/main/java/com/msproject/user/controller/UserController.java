package com.msproject.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msproject.user.VO.ResponseTemplateVO;
import com.msproject.user.entity.User;
import com.msproject.user.service.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
	
	private static final String USER_SERVICE = "userService";
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	public User saveUser(@RequestBody User user) {
		log.info("Inside saveUser inside of UserController");
		return userService.saveUser(user);
	}

	@GetMapping("/{id}")
	@CircuitBreaker(name=USER_SERVICE, fallbackMethod="userFallback")
	public ResponseTemplateVO getUserWithDepartment(@PathVariable("id") Long userId) {
		log.info("Inside getUserWithDepartment inside of UserController");
		return userService.getUserWithDepartment(userId);
	}
	
	public ResponseTemplateVO userFallback(Exception e) {
		log.info("Inside userFallback inside of UserController");
		return userService.userFallback(e);
	}
}
