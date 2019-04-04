package com.boot.security.server.model;

import java.util.Date;

public class Message extends BaseEntity<Long> {

	private String nickName;
	private String message;
	private String phoneOrEmail;
	private Integer isContact;

	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getPhoneOrEmail() {
		return phoneOrEmail;
	}
	public void setPhoneOrEmail(String phoneOrEmail) {
		this.phoneOrEmail = phoneOrEmail;
	}
	public Integer getIsContact() {
		return isContact;
	}
	public void setIsContact(Integer isContact) {
		this.isContact = isContact;
	}

}
