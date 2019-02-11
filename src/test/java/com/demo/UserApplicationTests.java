package com.demo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.controller.WebController;
import com.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserApplicationTests {
	private static final org.jboss.logging.Logger logger = LoggerFactory.logger(UserApplicationTests.class);

	 @Autowired
	private WebController userController;
	 
	 @Test
	public void testCreateUser() throws Exception{
		List<User> userList = userController.findAll();
		User user = new User();
		user.setId(1);
		user.setFirstName("John");
		user.setLastName(null);
		user.setStatus(0);
		userController.create(user);
		List<User> userListMatch = userController.findAll();
		logger.info("--------------------- " + userList.size()+"---------------"+userListMatch.size());
		assertTrue(userList.size()!=userListMatch.size());   
		}
	
	@Test
	public void testAllUser() throws Exception{
		List<User> user = userController.findAll();
		logger.info("--------------------- " + user.size());
		assertFalse(user.isEmpty());   
	}
	
	@Test
	public void testUserID() throws Exception{
		long id = 1;
		Optional<User> user = userController.findById(id);
		logger.info("--------------------- " + user.get().getId());
		assertTrue(user.get().getId()==21);   
	}




	
}

