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
import com.costs.data.User;
import com.costs.repositories.CategoryRepositoryImpl;
import com.costs.repositories.CostRepositoryImpl;
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
public class CostRestController {

	private static final Logger logger = Logger.getLogger(CostRestController.class);
	private final int COST_SEQUENCE_ID = 1;
	private final int CATEGORY_SEQUENCE_ID = 2;
	private final int USER_SEQUENCE_ID = 3;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		logger.info("Welcome home! The client locale is drugi parametar");

		Utility u = new Utility();

		User newUser = new User();
		newUser.setUserUsername("dd");
		newUser.setUserPassword("dd");
		newUser.setUserId(u.getSequenceNextval(USER_SEQUENCE_ID));
		

		UserRepositoryImpl users = new UserRepositoryImpl();
		users.insert(newUser);

		return "home";
	}

	@RequestMapping(value = "/cost/{costRecord}", method = RequestMethod.POST)
	@ResponseBody
	public Cost createNewCost(@PathVariable("costRecord") Cost costRecord) {

		Utility u = new Utility();

		CostRepositoryImpl costs = new CostRepositoryImpl();

		Cost costCreated = costs.insert(costRecord);
		return costCreated;
	}

	@RequestMapping(value = "/cost/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Cost getCostById(@PathVariable("id") int id) {

		Utility u = new Utility();

		CostRepositoryImpl costs = new CostRepositoryImpl();

		Cost costRecord = costs.findCostById(id);
		return costRecord;
	}

	@RequestMapping(value = "/cost/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteCostById(@PathVariable("id") int id) {

		Utility u = new Utility();

		CostRepositoryImpl costs = new CostRepositoryImpl();

		Cost costRecord = costs.findCostById(id);
		costs.delete(costRecord);
	}

	@RequestMapping(value = "/cost", method = RequestMethod.GET)
	@ResponseBody
	public List<Cost> getAllCosts() {

		Utility u = new Utility();

		CostRepositoryImpl costs = new CostRepositoryImpl();

		List<Cost> list = costs.findAll();
		return list;
	}

	@RequestMapping(value = "/cost", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteAllCosts() {

		Utility u = new Utility();

		CostRepositoryImpl costs = new CostRepositoryImpl();

		costs.deleteAll();
	}
}