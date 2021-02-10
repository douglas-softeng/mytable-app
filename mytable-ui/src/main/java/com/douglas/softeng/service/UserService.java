package com.douglas.softeng.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.douglas.softeng.model.User;
import com.douglas.softeng.service.facades.UserFacade;

@Service
public class UserService {
	
	private final UserFacade userFacade;
	
	@Autowired
	public UserService(UserFacade userFacade) {
		this.userFacade = userFacade;
	}
	
	public List<User> listUsers() {
		return userFacade.listUsers();
	}
	
	public User findUser(String id) {
		if (id.isBlank()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		return userFacade.findUser(id);
	}
	
	public User createUser(User user) {
		if (user == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		return userFacade.createUser(user);
	}
	
	public User updateUser(String id, User user) {
		if (id.isBlank() || user == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		return userFacade.updateUser(id, user);
	}
	
	public void deleteUser(String id) {
		if (id.isBlank()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		userFacade.deleteUser(id);
	}

}
