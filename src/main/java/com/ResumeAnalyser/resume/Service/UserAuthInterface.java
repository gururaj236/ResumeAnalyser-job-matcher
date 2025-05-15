package com.ResumeAnalyser.resume.Service;

import java.util.List;

import com.ResumeAnalyser.resume.Model.UserAuthEntity;

public interface UserAuthInterface {
	UserAuthEntity CreateUser(UserAuthEntity user);

	UserAuthEntity getUserByUserId(Long id);

	UserAuthEntity Updateuser(Long id, UserAuthEntity user);

	List<UserAuthEntity> getAllUser();

	public void DeletebyUser(Long id);
}
