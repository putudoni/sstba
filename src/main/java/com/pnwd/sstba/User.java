/**
 * 
 */
package com.pnwd.sstba;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 * @author Putu Ngurah Wigadoni
 * @version 1.0
 */
@Document(collection = "users")
public class User {

	@Id
	private String id;

	@NotEmpty
	private String username;

	@NotEmpty
	private String password;

	private String token;

	@CreatedDate
	@Field("created_date")
	@DateTimeFormat(iso=ISO.DATE_TIME)
	private Date createdDate = new Date();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
}
