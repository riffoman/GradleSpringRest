package com.costs.controller;

import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.costs.data.Cost;
import com.costs.data.User;
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
	private final int USER_SEQUENCE_ID = 3;
	@Autowired
	private MongoOperations mongoOperations;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		logger.info("Welcome home! The client locale is drugi parametar");

		Utility u = new Utility();
		u.setMongoOperations(mongoOperations);

		User newUser = new User();
		newUser.setUserUsername("dd");
		newUser.setUserPassword("dd");
		newUser.setUserId(u.getSequenceNextval(USER_SEQUENCE_ID));
		

		UserRepositoryImpl users = new UserRepositoryImpl();
		users.setMongoOperations(mongoOperations);
		users.insert(newUser);

		return "home";
	}

	@RequestMapping(value = "/cost/{costRecord}", method = RequestMethod.POST)
	@ResponseBody
	public Cost createNewCost(@PathVariable("costRecord") Cost costRecord) {

		Utility u = new Utility();
		u.setMongoOperations(mongoOperations);

		CostRepositoryImpl costs = new CostRepositoryImpl();
		costs.setMongoOperations(mongoOperations);
		costRecord.setCostId(u.getSequenceNextval(COST_SEQUENCE_ID));

		Cost costCreated = costs.insert(costRecord);
		return costCreated;
	}

	@RequestMapping(value = "/cost/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Cost getCostById(@PathVariable("id") int id) {

		CostRepositoryImpl costs = new CostRepositoryImpl();
		costs.setMongoOperations(mongoOperations);

		Cost costRecord = costs.findCostById(id);
		return costRecord;
	}

	@RequestMapping(value = "/cost/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteCostById(@PathVariable("id") int id) {

		CostRepositoryImpl costs = new CostRepositoryImpl();
		costs.setMongoOperations(mongoOperations);

		Cost costRecord = costs.findCostById(id);
		costs.delete(costRecord);
	}

	@RequestMapping(value = "/cost", method = RequestMethod.GET)
	@ResponseBody
	public List<Cost> getAllCosts() {

		CostRepositoryImpl costs = new CostRepositoryImpl();
		costs.setMongoOperations(mongoOperations);

		List<Cost> list = costs.findAll();
		return list;
	}

	@RequestMapping(value = "/cost", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteAllCosts() {

		CostRepositoryImpl costs = new CostRepositoryImpl();
		costs.setMongoOperations(mongoOperations);

		costs.deleteAll();
	}
}