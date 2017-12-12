package com.costs.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.costs.data.Category;
import com.costs.data.User;
import com.costs.repositories.CategoryRepositoryImpl;
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
	MongoOperations mongoOperations;

	private static final Logger logger = Logger.getLogger(UserRestController.class);
	private final int COST_SEQUENCE_ID = 1;
	private final int CATEGORY_SEQUENCE_ID = 2;
	private final int USER_SEQUENCE_ID = 3;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/welcomeJDBC/{id}", method = RequestMethod.GET)
	public String welcomeJDBC(@PathVariable("id") int id, Model model) {
		logger.info("Napravio konekciju JDBCTemplate");

		model.addAttribute("id", id);
		return "welcomeJDBC";
	}

	@RequestMapping(value = "/user/{userRecord}", method = RequestMethod.POST)
	@ResponseBody
	public User createNewUser(@PathVariable("userRecord") User userRecord) {

		Utility u = new Utility();
		u.setMongoOperations(mongoOperations);

		UserRepositoryImpl users = new UserRepositoryImpl();
		users.setMongoOperations(mongoOperations);

		User userCreated = users.insert(userRecord);
		return userCreated;
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	@ResponseBody
	public User getUserById(@PathVariable("id") int id) {

		Utility u = new Utility();
		u.setMongoOperations(mongoOperations);

		UserRepositoryImpl users = new UserRepositoryImpl();
		users.setMongoOperations(mongoOperations);

		User userRecord = users.findUserById(id);
		return userRecord;
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteUserById(@PathVariable("id") int id) {

		Utility u = new Utility();
		u.setMongoOperations(mongoOperations);

		UserRepositoryImpl users = new UserRepositoryImpl();
		users.setMongoOperations(mongoOperations);

		User userRecord = users.findUserById(id);
		users.delete(userRecord);
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	@ResponseBody
	public List<User> getAllUsers() {

		Utility u = new Utility();
		u.setMongoOperations(mongoOperations);

		UserRepositoryImpl users = new UserRepositoryImpl();
		users.setMongoOperations(mongoOperations);

		List<User> list = users.findAll();
		return list;
	}

	@RequestMapping(value = "/user", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteAllUsers() {

		Utility u = new Utility();
		u.setMongoOperations(mongoOperations);

		UserRepositoryImpl users = new UserRepositoryImpl();
		users.setMongoOperations(mongoOperations);

		users.deleteAll();
	}

	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	@ResponseBody
	public List<Category> getAllCategories() {

		Utility u = new Utility();
		u.setMongoOperations(mongoOperations);

		CategoryRepositoryImpl categoryRepository = new CategoryRepositoryImpl();
		categoryRepository.setMongoOperations(mongoOperations);

		List<Category> categories = categoryRepository.findAll();
		return categories;
	}
}