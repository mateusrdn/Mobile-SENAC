package com.mobile.pwa.controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mobile.pwa.model.User;
import com.mobile.pwa.service.UserService;


@RestController
@RequestMapping("/api")
public class UserController {

	
	@Autowired
	private UserService service;
	
	@GetMapping(value = "/users")
	public ResponseEntity<Page<User>> findAll(Pageable pageable){
		Page<User> paged = service.findAllPagedUser(pageable);
		return ResponseEntity.ok().body(paged);
	}
	
	@GetMapping(value = "/user/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id) {
		User user = service.findByIdUser(id);
		return ResponseEntity.ok().body(user);
	}

	@PostMapping(value = "/user")
	public ResponseEntity<User> insert(@Valid @RequestBody User user){
		service.insertUser(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(user);
	}
	
	@PutMapping(value = "/user/{id}")
	public ResponseEntity<User> update(@PathVariable Long id,@Valid @RequestBody User user) {
		service.updateUser(id, user);
		return ResponseEntity.ok().body(user);
	}
	
	@DeleteMapping(value = "/user/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.deleteUser(id);
		return ResponseEntity.noContent().build();
	} 
	
	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody User user){
		Optional<User> usere = service.login(user.getEmail(), user.getPassword());
		return ResponseEntity.ok().build();
		
	}
	
}
