package com.ResumeAnalyser.resume.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ResumeAnalyser.resume.Exceptions.UserNotfoundException;
import com.ResumeAnalyser.resume.Model.UserAuthEntity;
import com.ResumeAnalyser.resume.repository.UserAuthRepository;

@Service
public class UserAuthService implements UserAuthInterface {
	@Autowired
	private UserAuthRepository userRepo;

	@Override
	public UserAuthEntity CreateUser(UserAuthEntity user) {
		// TODO Auto-generated method stub
		return userRepo.save(user);
	}

	@Override
	public UserAuthEntity getUserByUserId(Long id) {
		// TODO Auto-generated method stub
		return userRepo.findById(id).orElseThrow(() -> new UserNotfoundException("user not found with id: " + id));
	}

	@Override
	public UserAuthEntity Updateuser(Long id, UserAuthEntity user) {
		Optional<UserAuthEntity> User = userRepo.findById(id);

		if (User.isPresent()) {
			UserAuthEntity ExistingUser = User.get();
			ExistingUser.setUserName(user.getUserName());
			ExistingUser.setRole(user.getRole());
			ExistingUser.setPassword(user.getPassword());
			return userRepo.save(ExistingUser);

		} else {
			throw new UserNotfoundException("user not found with id: " + id);
		}

	}

	@Override
	public List<UserAuthEntity> getAllUser() {
		// TODO Auto-generated method stub
		return userRepo.findAll();
	}

	@Override
	public void DeletebyUser(Long id) {
		// TODO Auto-generated method stub
		if (!userRepo.existsById(id)) {
			throw new UserNotfoundException("user not found with id :" + id);
		}
		userRepo.deleteById(id);
	}

}
