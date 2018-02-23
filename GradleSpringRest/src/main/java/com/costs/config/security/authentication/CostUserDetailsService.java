package com.costs.config.security.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.costs.data.User;
import com.costs.repositories.UserRepository;

@Component
public class CostUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		/*
		 * Here add user data layer fetching from the MongoDB. I have used
		 * userRepository
		 */
		User user = userRepository.findUserByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		} else {
			UserDetails userDetails = new CostUserDetails(user);
			return userDetails;
		}
	}
}
