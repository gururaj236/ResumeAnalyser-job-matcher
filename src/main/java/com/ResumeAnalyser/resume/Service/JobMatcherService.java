package com.ResumeAnalyser.resume.Service;

import com.ResumeAnalyser.resume.Model.JobPosting;
import com.ResumeAnalyser.resume.Model.Resume;
import com.ResumeAnalyser.resume.Request.DTO.JobMatchDTO;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class JobMatcherService {


	public double calculateMatchScore(Resume resume, JobPosting job) {
	    double score = 0;

	    
	    java.util.function.Function<String, Set<String>> tokenize = s -> {
	        if (s == null) return Set.of();
	        return new HashSet<>(Arrays.asList(s.toLowerCase().split("\\W+"))); // split by non-word chars
	    };

	    
	    Set<String> jobSkillsTokens = new HashSet<>();
	    if (job.getRequiredSkills() != null) {
	        for (String skill : job.getRequiredSkills()) {
	            jobSkillsTokens.addAll(tokenize.apply(skill));
	        }
	    }

	    Set<String> resumeSkillsTokens = new HashSet<>();
	    if (resume.getSkills() != null) {
	        for (String skill : resume.getSkills()) {
	            resumeSkillsTokens.addAll(tokenize.apply(skill));
	        }
	    }

	    
	    long matchedSkills = jobSkillsTokens.stream()
	            .filter(jsToken -> resumeSkillsTokens.stream().anyMatch(rsToken -> rsToken.contains(jsToken) || jsToken.contains(rsToken)))
	            .count();

	    if (!jobSkillsTokens.isEmpty()) {
	        score += ((double) matchedSkills / jobSkillsTokens.size()) * 50;
	    }

	    
	    Set<String> jobEduTokens = new HashSet<>();
	    if (job.getRequiredEducation() != null) {
	        for (String edu : job.getRequiredEducation()) {
	            jobEduTokens.addAll(tokenize.apply(edu));
	        }
	    }

	    Set<String> resumeEduTokens = new HashSet<>();
	    if (resume.getEducation() != null) {
	        for (String edu : resume.getEducation()) {
	            resumeEduTokens.addAll(tokenize.apply(edu));
	        }
	    }

	    boolean eduMatched = jobEduTokens.stream()
	            .anyMatch(jt -> resumeEduTokens.stream()
	                    .anyMatch(rt -> rt.contains(jt) || jt.contains(rt)));

	    if (eduMatched) {
	        score += 30;
	    }

	    Set<String> jobExpTokens = new HashSet<>();
	    if (job.getRequiredExperience() != null) {
	        for (String exp : job.getRequiredExperience()) {
	            jobExpTokens.addAll(tokenize.apply(exp));
	        }
	    }

	    Set<String> resumeExpTokens = new HashSet<>();
	    if (resume.getExperience() != null) {
	        for (String exp : resume.getExperience()) {
	            resumeExpTokens.addAll(tokenize.apply(exp));
	        }
	    }

	    boolean expMatched = jobExpTokens.stream()
	            .anyMatch(jt -> resumeExpTokens.stream()
	                    .anyMatch(rt -> rt.contains(jt) || jt.contains(rt)));

	    if (expMatched) {
	        score += 20;
	    }
	    System.out.println("Resume skills: " + resume.getSkills());
	    System.out.println("Job required skills: " + job.getRequiredSkills());


	    return score;
	}
	

	public List<JobMatchDTO> calculateMatchScore(Resume resume, List<JobPosting> allJobs) {
	    return allJobs.stream()
	        .map(job -> new JobMatchDTO(job, calculateMatchScore(resume, job)))  
	        .toList();
	}



}
