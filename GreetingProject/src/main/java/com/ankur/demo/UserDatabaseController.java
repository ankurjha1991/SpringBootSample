package com.ankur.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserDatabaseController {
	
	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/createUser")
	public User createUser(@RequestBody User user) {
		return userRepository.save(user);
	}
	
	@GetMapping("/getAllUsers")
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<User> getUserByID(@PathVariable(value = "id") Long userID){
		User user = userRepository.findOne(userID);
		if(user == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(user);
	}
	
	@PutMapping("/updateUser/{id}")
	public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userID, @RequestBody User userDetails){
		User user = userRepository.findOne(userID);
		if(user == null) {
			return ResponseEntity.notFound().build();
		}
		
		user.setUserName(userDetails.getUserName());
		user.setEmailId(userDetails.getEmailId());
		
		User updatedUser = userRepository.save(user);
		return ResponseEntity.ok(updatedUser);
		
	}
	
	@DeleteMapping("deleteUser/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable(value = "id") Long userID){
		User user = userRepository.findOne(userID);
		
		if(user == null) {
			return ResponseEntity.notFound().build();
		}
		
		userRepository.delete(user);
		
		return ResponseEntity.ok(user);
	}
	

}
