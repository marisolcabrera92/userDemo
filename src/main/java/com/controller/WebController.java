package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.model.User;
import com.repository.UserRepository;

@Service("UserService")
public class WebController {
	 @Autowired
	    UserRepository repository;
	    

	    public User create (User user){
	        return repository.save(user);
	    }
	       
	    public ResponseEntity<Object> update(@RequestBody User user, @PathVariable int id) {
	    	Optional<User> userOption = repository.findById((long) id);

	    	if (!userOption.isPresent())
	    		return ResponseEntity.notFound().build();

	    	user.setId(id);
	    	repository.save(user);
	    	return ResponseEntity.noContent().build();
	    }
	       
	     public List<User> findAll(){ 
	        return (List<User>) repository.findAll();
	    }   
	    
	    public Optional<User>  findById(@RequestParam("id") long id){
	        return repository.findById(id);
	    }

	    public void deleteById(@RequestParam("id") long id){
	        repository.deleteById(id);
	    }
}
