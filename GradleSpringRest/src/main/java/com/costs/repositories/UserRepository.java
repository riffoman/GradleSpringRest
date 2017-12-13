package com.costs.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.costs.data.User;

public interface UserRepository extends MongoRepository<User, String> {

}
