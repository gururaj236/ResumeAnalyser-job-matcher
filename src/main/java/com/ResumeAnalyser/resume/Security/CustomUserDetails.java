package com.ResumeAnalyser.resume.Security;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ResumeAnalyser.resume.Model.UserAuthEntity;

public class CustomUserDetails implements UserDetails,Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private UserAuthEntity userAuthEntity;

	CustomUserDetails(UserAuthEntity userAuthEntity) {
		this.userAuthEntity = userAuthEntity;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return Collections.singleton(new SimpleGrantedAuthority(userAuthEntity.getRole()));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return userAuthEntity.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userAuthEntity.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
