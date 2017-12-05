package com.costs.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.costs.data.Category;
import com.costs.data.Cost;
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
public class CostRestController {
	@Autowired
	MongoOperations mongoOperations;

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

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		return "home";
	}

	@RequestMapping(value = "/welcomeJDBC/{id}", method = RequestMethod.GET)
	public String welcomeJDBC(@PathVariable("id") int id, Model model) {
		logger.info("Napravio konekciju JDBCTemplate");

		model.addAttribute("id", id);
		return "welcomeJDBC";
	}

	@RequestMapping(value = "/saja/{id}", method = RequestMethod.GET)
	@ResponseBody
	public List<Cost> saja(@PathVariable("id") int id, Model model) {

		Utility u = new Utility();
		u.setMongoOperations(mongoOperations);

		CostRepositoryImpl costs = new CostRepositoryImpl();
		costs.setMongoOperations(mongoOperations);

		List<Cost> list = costs.findAll();
		return list;
	}
}