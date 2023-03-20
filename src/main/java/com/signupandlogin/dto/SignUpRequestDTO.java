package com.signupandlogin.dto;

public class SignUpRequestDTO {

	private String name;
	private String gender;
	private String emailId;
	private String phoneNumber;
	private String password;

	public String getName() {
		return name;
	}

	public String getGender() {
		return gender;
	}

	public String getEmailId() {
		return emailId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
