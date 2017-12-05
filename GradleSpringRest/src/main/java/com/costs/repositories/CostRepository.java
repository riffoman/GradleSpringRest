package com.costs.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.costs.data.Cost;

public interface CostRepository extends MongoRepository<Cost, String> {
	List<Cost> findByCostDescription(String c);
	List<Cost> findByCostDescriptionLike(String c);
}
