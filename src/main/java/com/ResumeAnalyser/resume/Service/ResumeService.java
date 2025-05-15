package com.ResumeAnalyser.resume.Service;

import java.util.List;

import com.ResumeAnalyser.resume.Model.Resume;
import com.ResumeAnalyser.resume.Model.User;

public interface ResumeService {
	
	
	Resume saveResume(Resume resume);
	
	Resume getResumebyId(Long id);

	
	List<Resume>GetAllResume();
	List<Resume>GetAllResumebyUserId(Long userid);
	void deleteResumebyid(Long id);
	

}
