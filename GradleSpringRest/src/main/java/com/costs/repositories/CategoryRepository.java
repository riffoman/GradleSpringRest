package com.costs.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.costs.data.Category;

public interface CategoryRepository extends MongoRepository<Category, String> {

}
