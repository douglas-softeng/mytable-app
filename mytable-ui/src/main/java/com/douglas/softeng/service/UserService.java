package com.douglas.softeng.service;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.douglas.softeng.model.User;

@Service
public class UserService {
	
	private static final String HEADER_ACCEPT = "accept";
	private static final String HEADER_JSON = "application/json";
	private static final String API_URL = "v1/user";
	
	@Value("${service.mytableapi.url}")
	private String mytableApiURL;
	
	private final Client client;
	
	public UserService() {
		this.client = ClientBuilder.newClient();
	}
	
	public List<User> listUsers() {
		try {
			final WebTarget myResource = client.target(String.format("%s%s/", mytableApiURL, API_URL));
			final Response response = myResource
				.request(MediaType.APPLICATION_JSON)
				.header(HEADER_ACCEPT, HEADER_JSON)
				.get();
			
			if (response.getStatus() == 200)
				return response.readEntity(new GenericType<List<User>>() {});
			
			return List.of();
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}
	
	public User findUser(String id) {
		try {
			final WebTarget myResource = client.target(String.format("%s%s/%s", mytableApiURL, API_URL, id));
			final Response response = myResource
				.request(MediaType.APPLICATION_JSON)
				.header(HEADER_ACCEPT, HEADER_JSON)
				.get();
			
			if (response.getStatus() == 200)
				return response.readEntity(User.class);
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}
	
	public User createUser(User user) {
		try {
			final WebTarget myResource = client.target(String.format("%s%s/create", mytableApiURL, API_URL));
			final Response response = myResource
				.request(MediaType.APPLICATION_JSON)
				.header(HEADER_ACCEPT, HEADER_JSON)
				.post(Entity.entity(user, MediaType.APPLICATION_JSON));
			
			if (response.getStatus() == 201)
				return response.readEntity(User.class);
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}
	
	public User updateUser(String id, User user) {
		try {
			final WebTarget myResource = client.target(String.format("%s%s/%s", mytableApiURL, API_URL, id));
			final Response response = myResource
				.request(MediaType.APPLICATION_JSON)
				.header(HEADER_ACCEPT, HEADER_JSON)
				.put(Entity.entity(user, MediaType.APPLICATION_JSON));
			
			if (response.getStatus() == 200)
				return response.readEntity(User.class);
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}
	
	public void deleteUser(String id) {
		try {
			final WebTarget myResource = client.target(String.format("%s%s/%s", mytableApiURL, API_URL, id));
			final Response response = myResource
				.request(MediaType.APPLICATION_JSON)
				.header(HEADER_ACCEPT, HEADER_JSON)
				.delete();
			
			if (response.getStatus() != 200)
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}

}
