package com.msproject.user.VO;

import com.msproject.user.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateVO {
	// This class acts as a wrapper object for both the User and the Department
	
	private User user;
	private Department department;
}
