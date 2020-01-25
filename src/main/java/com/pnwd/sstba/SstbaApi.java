/**
 * 
 */
package com.pnwd.sstba;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Putu Ngurah Wigadoni
 * @version 1.0
 */
@RestController
@RequestMapping("/api")
public class SstbaApi {

	private static final String MESSAGES = "Simple Spring Boot Security Token Based Authentication with MongoDB";
	
	@GetMapping("/welcome")
	public ResponseEntity<ResponseApi> welcome(){
		ResponseApi response = new ResponseApi();
		response.setStatus(HttpStatus.OK.toString());
		response.setData(null);
		response.setMessages(MESSAGES);
		return new ResponseEntity<ResponseApi>(response,HttpStatus.OK);
	}
	
}
