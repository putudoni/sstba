/**
 * 
 */
package com.pnwd.sstba;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Putu Ngurah Wigadoni
 * @version 1.0
 */
@RestController
public class LoginApi {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/login")
	public ResponseEntity<ResponseApi> login(@Valid @RequestBody User user){
		ResponseApi response = new ResponseApi();
		Optional<User> users = userService.findByUsername(user.getUsername());
		if(users.isPresent()) {
			if(passwordEncoder.matches(user.getPassword(), users.get().getPassword())) {
				Map<String, Object> data = new HashMap<>();
				data.put(SstbaAppConstants.TOKEN_ATTR, users.get().getToken());
				response.setData(data);
				response.setMessages("Welcome "+users.get().getUsername());
			}else {
				response.setMessages(SstbaAppConstants.INVALID_PASSWORD_MESSAGE);
			}
		}else {
			response.setMessages(SstbaAppConstants.USER_NOTFOUD_MESSAGE);
		}
		
		response.setStatus(HttpStatus.OK.toString());
		return new ResponseEntity<ResponseApi>(response,HttpStatus.OK);
	}
	
}
