/**
 * 
 */
package com.pnwd.sstba;

/**
 * @author Putu Ngurah Wigadoni
 * @version 1.0
 */
public class ResponseApi {

	private String status;

	private String data;

	private String messages;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getMessages() {
		return messages;
	}

	public void setMessages(String messages) {
		this.messages = messages;
	}

}
