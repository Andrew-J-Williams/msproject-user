package com.msproject.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msproject.user.entity.User;
import com.msproject.user.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public User saveUser(User user) {
		// TODO Auto-generated method stub
		log.info("Inside saveUser inside of UserService");
		return userRepository.save(user);
	}
}
