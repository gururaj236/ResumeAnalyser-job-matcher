package com.ResumeAnalyser.resume.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ResumeAnalyser.resume.Model.User;

public interface UserRepository extends JpaRepository<User,Long> {
	
	User findByEmail(String email);

}
