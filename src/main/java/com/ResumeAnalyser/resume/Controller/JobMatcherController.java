package com.ResumeAnalyser.resume.Controller;
import com.ResumeAnalyser.resume.Model.JobPosting;
import com.ResumeAnalyser.resume.Model.Resume;
import com.ResumeAnalyser.resume.Request.DTO.JobMatchDTO;
import com.ResumeAnalyser.resume.Service.JobMatcherService;
import com.ResumeAnalyser.resume.repository.JobRepository;
import com.ResumeAnalyser.resume.repository.ResumeResository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matcher")
public class JobMatcherController {

    @Autowired
    private JobMatcherService jobMatcherService;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ResumeResository resumeRepository;

    @GetMapping("/match/{resumeId}")
    public ResponseEntity<List<JobMatchDTO>> getMatchingJobs(@PathVariable Long resumeId) {
        Resume resume = resumeRepository.findById(resumeId)
            .orElseThrow(() -> new RuntimeException("Resume not found with id: " + resumeId));

        List<JobPosting> allJobs = jobRepository.findAll();

        List<JobMatchDTO> jobsWithScores = jobMatcherService.calculateMatchScore(resume, allJobs);

        return ResponseEntity.ok(jobsWithScores);
    }

}
