package com.ResumeAnalyser.resume.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ResumeAnalyser.resume.Model.Resume;

public interface ResumeResository extends JpaRepository<Resume, Long> {
	List<Resume> findByUserId(Long userId);
	
}
