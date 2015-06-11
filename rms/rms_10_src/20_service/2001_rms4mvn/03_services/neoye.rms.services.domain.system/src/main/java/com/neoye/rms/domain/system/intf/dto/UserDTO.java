package com.neoye.rms.domain.system.intf.dto;

import com.neoye.rms.domain.infrastructure.base.BaseDTO;

public class UserDTO extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userId;
	
	private String account;
	
	private String password;
	
	private String userName;
	
	private String sex;
	
	private String email;
	
	private String phone;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "UserDTO [account=" + account + ", password=" + password
				+ ", userName=" + userName + ", sex=" + sex + ", email="
				+ email + ", phone=" + phone + "]";
	}
	

	
}
