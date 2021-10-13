package com.msproject.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.msproject.user.VO.Department;
import com.msproject.user.VO.ResponseTemplateVO;
import com.msproject.user.entity.User;
import com.msproject.user.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;

	public User saveUser(User user) {
		// TODO Auto-generated method stub
		log.info("Inside saveUser inside of UserService");
		return userRepository.save(user);
	}

	public ResponseTemplateVO getUserWithDepartment(Long userId) {
		// TODO Auto-generated method stub
		
		log.info("Inside getUserWithDepartment inside of UserService");
		ResponseTemplateVO vo = new ResponseTemplateVO(); 
		
		// First, we fetch our user object from the repository
		User user = userRepository.findByUserId(userId);
		
		// Second, we fetch our department object from our Department service at the url plus the departmentId that we can get from the user.
		Department department = restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/" + user.getDepartmentId(), Department.class);
		
		// Third, we set our VO object's user and department, acting as a wrapper object
		vo.setUser(user);
		vo.setDepartment(department);
		
		return vo;
	}
}
