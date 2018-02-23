package com.costs.config.security.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.costs.data.User;
import com.costs.repositories.UserRepository;
import com.costs.repositories.UserRepositoryImpl;

@Component
public class CostUserDetailsService implements UserDetailsService {
	@Autowired
	MongoOperations mongoOperations;

	@Autowired
	private UserRepositoryImpl userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		/*
		 * Here add user data layer fetching from the MongoDB. I have used
		 * userRepository
		 */
		userRepository.setMongoOperations(mongoOperations);
		User user = userRepository.findUserByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		} else {
			UserDetails userDetails = new CostUserDetails(user);
			return userDetails;
		}
	}
}
