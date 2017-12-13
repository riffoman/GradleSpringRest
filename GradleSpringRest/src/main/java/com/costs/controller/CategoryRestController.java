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
import com.costs.data.Cost;
import com.costs.repositories.CategoryRepositoryImpl;
import com.costs.repositories.CostRepositoryImpl;
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
public class CategoryRestController {
	@Autowired
	MongoOperations mongoOperations;

	private static final Logger logger = Logger.getLogger(CategoryRestController.class);
	private final int COST_SEQUENCE_ID = 1;
	private final int CATEGORY_SEQUENCE_ID = 2;
	private final int USER_SEQUENCE_ID = 3;

	@RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Category getCategoryById(@PathVariable("categoryId") int categoryId) {

		Utility u = new Utility();
		u.setMongoOperations(mongoOperations);

		CategoryRepositoryImpl categories = new CategoryRepositoryImpl();
		categories.setMongoOperations(mongoOperations);

		Category categoryRecord = categories.findCategoryById(categoryId);
		return categoryRecord;
	}

	@RequestMapping(value = "/category/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteCostById(@PathVariable("categoryId") int categoryId) {

		Utility u = new Utility();
		u.setMongoOperations(mongoOperations);

		CategoryRepositoryImpl categories = new CategoryRepositoryImpl();
		categories.setMongoOperations(mongoOperations);

		Category categoryRecord = categories.findCategoryById(categoryId);
		categories.delete(categoryRecord);
	}

	@RequestMapping(value = "/category", method = RequestMethod.GET)
	@ResponseBody
	public List<Category> getAllCategories() {

		Utility u = new Utility();
		u.setMongoOperations(mongoOperations);

		CategoryRepositoryImpl categoryRepository = new CategoryRepositoryImpl();
		categoryRepository.setMongoOperations(mongoOperations);

		List<Category> categories = categoryRepository.findAll();
		return categories;
	}

	@RequestMapping(value = "/category", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteAllCosts() {

		Utility u = new Utility();
		u.setMongoOperations(mongoOperations);

		CategoryRepositoryImpl categoryRepository = new CategoryRepositoryImpl();
		categoryRepository.setMongoOperations(mongoOperations);

		categoryRepository.deleteAll();
	}
}