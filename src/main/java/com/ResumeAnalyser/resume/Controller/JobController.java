package com.ResumeAnalyser.resume.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ResumeAnalyser.resume.Model.JobPosting;
import com.ResumeAnalyser.resume.Request.DTO.JobDTO;
import com.ResumeAnalyser.resume.Service.JobService;

@RestController
@RequestMapping("/job")
public class JobController {

	@Autowired
	private JobService jobService;

	@PostMapping("/jobupload")
	ResponseEntity<JobDTO> postJob(@RequestBody JobPosting job) {
		try {
			JobPosting savedJob = jobService.saveJob(job);
			return ResponseEntity.status(HttpStatus.CREATED).body(mapToDTO(savedJob));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

		}
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<JobDTO> getJobById(@PathVariable Long id) {
		return jobService.getJobById(id).map(job -> ResponseEntity.ok(mapToDTO(job)))
				.orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<JobPosting>> getAllJobs() {
		List<JobPosting> jobDTOs = jobService.getAllJobs();
		if (jobDTOs.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(jobDTOs);
		}
		return ResponseEntity.ok(jobDTOs);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<JobDTO> updateJob(@PathVariable Long id, @RequestBody JobPosting updatedJob) {
	    return jobService.getJobById(id).map(existingJob -> {
	        existingJob.setTitle(updatedJob.getTitle());
	        existingJob.setDescription(updatedJob.getDescription());
	        existingJob.setRequiredSkills(updatedJob.getRequiredSkills());
	        existingJob.setRequiredEducation(updatedJob.getRequiredEducation());
	        existingJob.setRequiredExperience(updatedJob.getRequiredExperience());
	        existingJob.setLocation(updatedJob.getLocation());
	        existingJob.setCompany(updatedJob.getCompany());

	        JobPosting savedJob = jobService.saveJob(existingJob);
	        return ResponseEntity.ok(mapToDTO(savedJob));
	    }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteJob(@PathVariable Long id) {
		try {
			jobService.deleteJobById(id);
			return ResponseEntity.ok("Job deleted successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error occurred while deleting the job");
		}
	}

	private JobDTO mapToDTO(JobPosting job) {
		return JobDTO.builder().id(job.getJobId()).title(job.getTitle()).description(job.getDescription())
				.requiredSkills(job.getRequiredSkills()).requiredEducation(job.getRequiredEducation())
				.requiredExperience(job.getRequiredExperience()).location(job.getLocation()).company(job.getCompany())
				.build();
	}
}
