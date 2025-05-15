package com.ResumeAnalyser.resume.Service;

import java.util.List;
import java.util.Optional;

import com.ResumeAnalyser.resume.Model.User;
import com.ResumeAnalyser.resume.Request.DTO.UserDTO;

public interface UserService {
	User saveUser(User user);
	User getuserbyId(Long id);
	User Update(Long id,User updatedUser);
	List<User> getAllUser();
	void deletebyId(Long id);
	

}
