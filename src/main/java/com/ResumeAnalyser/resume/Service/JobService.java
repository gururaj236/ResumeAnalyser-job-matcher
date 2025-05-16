package com.ResumeAnalyser.resume.Service;

import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ResumeAnalyser.resume.Model.JobPosting;
import com.ResumeAnalyser.resume.Request.DTO.JobDTO;
import com.ResumeAnalyser.resume.repository.JobRepository;


@Service
public class JobService implements JobInterface {
	

	    @Autowired
	    private JobRepository jobRepository;

	    public JobPosting saveJob(JobPosting job) {
	        return jobRepository.save(job);
	    }

	    public List<JobPosting> getAllJobs() {
	        return jobRepository.findAll();
	    }

	    public Optional<JobPosting> getJobById(Long id) {
	        return jobRepository.findById(id);
	    }

	    public void deleteJobById(Long id) {
	        jobRepository.deleteById(id);
	    }

	    

	    private JobDTO mapToDTO(JobPosting job) {
	        return JobDTO.builder()
	                .id(job.getJobId())
	                .title(job.getTitle())
	                .description(job.getDescription())
	                .requiredSkills(job.getRequiredSkills())
	                .requiredEducation(job.getRequiredEducation())
	                .requiredExperience(job.getRequiredExperience())
	                .location(job.getLocation())
	                .company(job.getCompany())
	                .build();
	    }
	}



