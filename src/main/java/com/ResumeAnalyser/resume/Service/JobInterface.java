package com.ResumeAnalyser.resume.Service;

import java.util.List;
import java.util.Optional;

import com.ResumeAnalyser.resume.Model.JobPosting;

public interface JobInterface {
	
	 public JobPosting saveJob(JobPosting job);
	 public List<JobPosting> getAllJobs();
	 public Optional<JobPosting> getJobById(Long id);
	 public void deleteJobById(Long id);

}
