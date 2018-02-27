package com.costs.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.costs.data.User;
import com.costs.repositories.UserRepositoryImpl;
import com.costs.util.Utility;

/**
 * Handles requests for the application home page.
 */
/**
 * @author sajinovics
 *
 */
@Controller
@EnableWebMvc
public class UserRestController {
	@Autowired
	private MongoOperations mongoOperations;

	private static final Logger logger = Logger.getLogger(UserRestController.class);
	private final int USER_SEQUENCE_ID = 3;

	@RequestMapping(value = "/user/{userRecord}", method = RequestMethod.POST)
	@ResponseBody
	public User createNewUser(@PathVariable("userRecord") User userRecord) {

		Utility u = new Utility();
		u.setMongoOperations(mongoOperations);
		
		UserRepositoryImpl users = new UserRepositoryImpl();
		users.setMongoOperations(mongoOperations);

		
		userRecord.setUserId(u.getSequenceNextval(USER_SEQUENCE_ID));

		User userCreated = users.insert(userRecord);
		return userCreated;
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	@ResponseBody
	@PreAuthorize("hasAuthority('ADMIN')")
	public User getUserById(@PathVariable("id") int id) {
		UserRepositoryImpl users = new UserRepositoryImpl();
		users.setMongoOperations(mongoOperations);

		User userRecord = users.findUserById(id);
		return userRecord;
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteUserById(@PathVariable("id") int id) {

		UserRepositoryImpl users = new UserRepositoryImpl();
		users.setMongoOperations(mongoOperations);

		User userRecord = users.findUserById(id);
		users.delete(userRecord);
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	@ResponseBody
	public List<User> getAllUsers() {

		UserRepositoryImpl users = new UserRepositoryImpl();
		users.setMongoOperations(mongoOperations);

		List<User> list = users.findAll();
		return list;
	}

	@RequestMapping(value = "/user", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteAllUsers() {

		UserRepositoryImpl users = new UserRepositoryImpl();
		users.setMongoOperations(mongoOperations);

		users.deleteAll();
	}
}