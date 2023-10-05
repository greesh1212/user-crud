package com.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.entities.User;
import com.user.service.UserService;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {
	
	private UserService synthesisService;

	@Autowired
	public UserController(UserService synthesisService) {
		this.synthesisService = synthesisService;
	}
	
	@GetMapping("ping")
	public String ping() {
		return "pong";
	}
	
	@PostMapping("create")
	public String createUser(@RequestBody User user) {
		log.info("request received {}", user);
		return synthesisService.createUser(user);
	}
	
	@GetMapping("{userId}")
	public User getUser(@PathVariable(name = "userId") Long userId) {
		log.info("get request received for id {}", userId);
		return synthesisService.getUser(userId);
	}
	
	@DeleteMapping("/delete/{userId}")
	public String deleteUser(@PathVariable(name = "userId") Long userId) {
		log.info("delete request received for id {}", userId);
		synthesisService.deleteUser(userId);
		return "user deleted successfully";
	}
	
	@PutMapping("/update")
	public String updateUser(@RequestBody User user) {
		log.info("update request received for user {}", user);
		return synthesisService.updateUser(user);
	}

	@GetMapping("by-fname-and-lname/{fname}/{lname}")
	public List<User> getUsers(@PathVariable(name = "fname") String firstName, @PathVariable(name = "lname") String lastName) {
		log.info("get users request received for first name {} and last name {}", firstName, lastName);
		return synthesisService.getUsers(firstName, lastName);
	}
	
	@GetMapping("all")
	public List<User> getAllUsers() {
		log.info("get all users request received ");
		return synthesisService.getAllUsers();
	}
}
