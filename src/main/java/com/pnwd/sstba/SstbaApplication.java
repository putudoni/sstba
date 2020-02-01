package com.pnwd.sstba;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class SstbaApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public static void main(String[] args) {
		SpringApplication.run(SstbaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if(mongoTemplate.findAll(User.class).size() == 0) {
			User user = new User();
			user.setUsername("admin");
			user.setPassword("e38ad214943daad1d64c102faec29de4afe9da3d");
			user.setToken("e4d437bb37fedc4183ee3f658b13c6633593a4a8");
			userService.save(user);
			System.out.println("API_KEY = "+user.getToken());
		}
	}

}
