package com.pnwd.sstba;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SstbaApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(SstbaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if(mongoTemplate.findAll(User.class).size() == 0) {
			User user = new User();
			user.setUsername("admin");
			user.setPassword(passwordEncoder.encode("password"));
			user.setToken("$2a$10$3MlaAZ4EoflQbTvkZYPXzOzf97uCd8rnMeBcX1u91TIcWfNA5GxkW");
			userService.save(user);
			System.out.println("API_KEY = "+user.getToken());
		}
	}

}
