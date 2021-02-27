package com.douglas.softeng.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.douglas.softeng.model.User;
import com.douglas.softeng.service.facades.ApiClient;

@Service
public class UserService {
	
	@Value("${api.url}")
	private String apiUrl;
	private ApiClient<User> apiClient;
	
	@PostConstruct
	private void initClient() {
		apiClient = new ApiClient<>(apiUrl + "v1/user", User.class);
	}
	
	public List<User> listUsers() {
		return apiClient.doGetAll();
	}
	
	public User findUser(String id) {
		if (id.isBlank()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		return apiClient.doGetOne(id);
	}
	
	public User createUser(User user) {
		if (user == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		return apiClient.doPost(user, "/create");
	}
	
	public User updateUser(String id, User user) {
		if (id.isBlank() || user == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		return apiClient.doPut(user, id);
	}
	
	public void deleteUser(String id) {
		if (id.isBlank()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		apiClient.doDelete(id);
	}

}
