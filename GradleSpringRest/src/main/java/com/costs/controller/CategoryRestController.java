package com.costs.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.costs.data.Category;
import com.costs.repositories.CategoryRepositoryImpl;
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
	private MongoOperations mongoOperations;

	private static final Logger logger = Logger.getLogger(CategoryRestController.class);
	private final int CATEGORY_SEQUENCE_ID = 2;

	@RequestMapping(value = "/category/{categoryRecord}", method = RequestMethod.POST)
	@ResponseBody
	public Category createNewCost(@PathVariable("categoryRecord") Category categoryRecord) {

		Utility u = new Utility();
		u.setMongoOperations(mongoOperations);

		CategoryRepositoryImpl categories = new CategoryRepositoryImpl();
		categories.setMongoOperations(mongoOperations);
		categoryRecord.setCategoryId(u.getSequenceNextval(CATEGORY_SEQUENCE_ID));

		Category categoryCreated = categories.insert(categoryRecord);
		return categoryCreated;
	}

	@RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Category getCategoryById(@PathVariable("categoryId") int categoryId) {

		CategoryRepositoryImpl categories = new CategoryRepositoryImpl();
		categories.setMongoOperations(mongoOperations);

		Category categoryRecord = categories.findCategoryById(categoryId);
		return categoryRecord;
	}

	@RequestMapping(value = "/category/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteCostById(@PathVariable("categoryId") int categoryId) {

		CategoryRepositoryImpl categories = new CategoryRepositoryImpl();
		categories.setMongoOperations(mongoOperations);

		Category categoryRecord = categories.findCategoryById(categoryId);
		categories.delete(categoryRecord);
	}

	@RequestMapping(value = "/category", method = RequestMethod.GET)
	@ResponseBody
	public List<Category> getAllCategories() {

		CategoryRepositoryImpl categoryRepository = new CategoryRepositoryImpl();
		categoryRepository.setMongoOperations(mongoOperations);

		List<Category> categories = categoryRepository.findAll();
		return categories;
	}

	@RequestMapping(value = "/category", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteAllCosts() {

		CategoryRepositoryImpl categoryRepository = new CategoryRepositoryImpl();
		categoryRepository.setMongoOperations(mongoOperations);

		categoryRepository.deleteAll();
	}
}