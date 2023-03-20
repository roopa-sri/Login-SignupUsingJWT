package com.signupandlogin.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.signupandlogin.common.Constant;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String gender;
	private String emailId;
	private String phoneNumber;
	private String userType = Constant.USER_TYPE.NORMAL;
	private String password;
	private Boolean isActive = true;
	private Integer loginCount = 0;
	private String ssoType;//single sign on
	private DateTime loginAt;
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime createdAt;
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime updatedAt;

	public Long getId() {
		return id;
	}

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

	public String getUserType() {
		return userType;
	}

	public String getPassword() {
		return password;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public Integer getLoginCount() {
		return loginCount;
	}

	public String getSsoType() {
		return ssoType;
	}

	public DateTime getLoginAt() {
		return loginAt;
	}

	public DateTime getCreatedAt() {
		return createdAt;
	}

	public DateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setId(Long id) {
		this.id = id;
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

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	public void setSsoType(String ssoType) {
		this.ssoType = ssoType;
	}

	public void setLoginAt(DateTime loginAt) {
		this.loginAt = loginAt;
	}

	public void setCreatedAt(DateTime createdAt) {
		this.createdAt = createdAt;
	}

	public void setUpdatedAt(DateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	@PrePersist
	public void onSave() {
		DateTime currentDateTime = new DateTime();
		this.createdAt = currentDateTime;
		this.updatedAt = currentDateTime;
	}

	@PostPersist
	public void onUpdate() {
		DateTime currentDateTime = new DateTime();
		this.updatedAt = currentDateTime;
	}
}
