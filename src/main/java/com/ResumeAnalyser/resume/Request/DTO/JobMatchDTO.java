package com.ResumeAnalyser.resume.Request.DTO;

import com.ResumeAnalyser.resume.Model.JobPosting;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JobMatchDTO {
    private JobPosting jobPosting;
    private double matchScore;
}
