package com.ResumeAnalyser.resume.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ResumeAnalyser.resume.Exceptions.UserNotfoundException;
import com.ResumeAnalyser.resume.Model.UserAuthEntity;
import com.ResumeAnalyser.resume.repository.UserAuthRepository;
@Service
public class CustomUserDetailsService implements UserDetailsService {

	
	@Autowired
	private UserAuthRepository authRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 UserAuthEntity user = authRepository.findByUserName(username)
			        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
			    
			    return new CustomUserDetails(user);
	}

}
