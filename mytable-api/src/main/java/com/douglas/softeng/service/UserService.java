package com.douglas.softeng.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import com.douglas.softeng.model.User;
import com.douglas.softeng.repository.UserRepository;

@Service
public class UserService {
	
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public List<User> listUsers() {
        return userRepository.findAll();
    }
    
    public User findUserById(String id) {
    	Optional<User> user = userRepository.findById(id);
    	
    	if (user.isPresent()) {
    		return user.get();
    	}
    	
    	throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }
    
    public User updateUser(@PathVariable("id") String id, @RequestBody User user) {
    	user.setId(id);
        return userRepository.save(user);
    }
    
    public void deleteUser(@PathVariable("id") String id) {
        userRepository.deleteById(id);
    }

}
