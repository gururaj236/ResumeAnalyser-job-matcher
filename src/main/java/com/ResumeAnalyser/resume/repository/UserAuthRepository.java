package com.ResumeAnalyser.resume.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ResumeAnalyser.resume.Model.UserAuthEntity;

public interface UserAuthRepository extends JpaRepository<UserAuthEntity,Long> {
	Optional<UserAuthEntity> findByUserName(String userName);


}
