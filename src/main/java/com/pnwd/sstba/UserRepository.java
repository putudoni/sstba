/**
 * 
 */
package com.pnwd.sstba;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Putu Ngurah Wigadoni
 * @version 1.0
 */
public interface UserRepository extends MongoRepository<User, String>{

	public Optional<User> findByUsername(String username);
	
	public Optional<User> findByToken(String token);
	
}
