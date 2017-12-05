package com.costs.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.costs.data.Cost;

public interface CategoryRepository extends MongoRepository<Cost, String> {

}
