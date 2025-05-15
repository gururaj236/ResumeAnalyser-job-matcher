package com.ResumeAnalyser.resume.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ResumeAnalyser.resume.Model.JobPosting;

public interface JobRepository extends JpaRepository<JobPosting,Long> {
}



