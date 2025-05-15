package com.ResumeAnalyser.resume.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ResumeAnalyser.resume.Exceptions.UserNotfoundException;
import com.ResumeAnalyser.resume.Model.User;
import com.ResumeAnalyser.resume.repository.UserRepository;

@Service
public class UserServiceimpli implements UserService {
	@Autowired
	private UserRepository repository;

	@Override
	public User saveUser(User user) {

		return repository.save(user);
	}

	@Override
	public User getuserbyId(Long id) {

		return repository.findById(id).orElseThrow(() -> new UserNotfoundException("user not found with id: " + id));
	}

	@Override
	public List<User> getAllUser() {

		return repository.findAll();
	}

	@Override
	public void deletebyId(Long id) {

		if (!repository.existsById(id)) {
			throw new UserNotfoundException("cannote delete, user not found with id: " + id);
		}
		repository.deleteById(id);

	}

	@Override
	public User Update(Long id, User updatedUser) {
		Optional<User> user = repository.findById(id);

		if (user.isPresent()) {
			User Existing = user.get();

			Existing.setName(updatedUser.getName());
			Existing.setEmail(updatedUser.getEmail());
			return repository.save(Existing);
		} else {
			throw new UserNotfoundException("user not found");
		}

	}

}
