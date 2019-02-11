package com.controller;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.exception.UserException;
import com.model.User;

@RestController
public class UserController {
	
	private static final org.jboss.logging.Logger logger = LoggerFactory.logger(UserController.class);

	@Autowired
	private WebController webController;
	
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public ResponseEntity<List<User>> getAllToDo(){
    	logger.info("Returning all the ToDo´s");
		return new ResponseEntity<List<User>>(webController.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public Optional<User> geById(@PathVariable("id") long id) throws UserException{
    	logger.info("ToDo id to return " + id);
    	Optional<User> user = webController.findById((int) id);
    	if (user == null || user.get().getId() <= 0){
           throw new UserException("User doesn´t exist");
    	}
		return user;
	}
	
	 @RequestMapping(value = "/deleteByID/{id}", method = RequestMethod.GET)
		public ResponseEntity<Optional<User>> removeById(@PathVariable("id") long id) throws UserException{
	    	logger.info("ToDo id to remove " + id);
	    	Optional<User> user = webController.findById((int) id);
	    	if (user == null || user.get().getId() <= 0){
	            throw new UserException("User to delete doesn´t exist");
	    	}
	    	webController.deleteById(user.get().getId());
	    	return new ResponseEntity<Optional<User>>(webController.findById((int) id), HttpStatus.OK);
		}

	 @RequestMapping(value = "/updateUser", method = RequestMethod.PATCH)
	   	public ResponseEntity<User>  updateUser(@RequestBody User user) throws UserException{
	    	logger.info("Payload to update " + user);
	    	Optional<User> userUpdate = webController.findById(user.getId());
	    	if (userUpdate == null || userUpdate.get().getId() <= 0){
	            throw new UserException("User to update doesn´t exist");
	    	}
			return new ResponseEntity<User>(webController.create(user), HttpStatus.OK);
	   	}
	

}
