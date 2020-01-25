/**
 * 
 */
package com.pnwd.sstba;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

/**
 * @author Putu Ngurah Wigadoni
 * @version 1.0
 */
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public void save(User user) {
		userRepository.save(user);
	}
	
	public Optional<User> findByUsernameAndPassword(String username, String password){
		return userRepository.findByUsernameAndPassword(username,password);
	}
	public Optional<org.springframework.security.core.userdetails.User> findByToken(String token){
		Optional<User> userToken = userRepository.findByToken(token);
		if(userToken.isPresent()) {
			org.springframework.security.core.userdetails.User user= new org.springframework.security.core.userdetails.User(userToken.get().getUsername(), userToken.get().getPassword(), true, true, true, true,
                    AuthorityUtils.createAuthorityList("USER"));
			return Optional.of(user);
		}
		return Optional.empty();
	}
	
	public Optional<User> findById(String id){
		return userRepository.findById(id);
	}
}
