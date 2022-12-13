package com.mobile.pwa.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mobile.pwa.exception.EntityException;
import com.mobile.pwa.exception.LoginException;
import com.mobile.pwa.model.User;
import com.mobile.pwa.repository.UserRepository;

import io.swagger.annotations.ApiOperation;


@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Transactional(readOnly = true)
	public Page<User> findAllPagedUser(Pageable pageable){
		return repository.findAll(pageable);
	}
	
	@Transactional(readOnly = true)
	public User findByIdUser(Long id) {
		Optional<User> opt = repository.findById(id);
		User user = opt.orElseThrow(() -> new EntityException("User " + id + " not exist"));
		return user;
	}
	
	@Transactional
	public User insertUser(User user){
		user.setPassword(encoder.encode(user.getPassword()));
		repository.save(user);
		return user;
	}
	
	@Transactional
	public User updateUser(Long id, User user) { 
		try {
			repository.save(user);
			return user;
		}
		catch(EntityNotFoundException e) {
			throw new EntityException("Id not found " + id);
		}
	}
	
	public void deleteUser(Long id) {
		
		try {
			repository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new EntityException("Id not found " + id);
		}
	}   
	
	public Optional<User> login(String email, String password) {
		Optional<User> optUser = repository.findByEmail(email);
		if(optUser == null || !encoder.matches(password, optUser.get().getPassword())) {
			throw new LoginException("Invalid user");
		}
		return optUser;
	}
	
}
