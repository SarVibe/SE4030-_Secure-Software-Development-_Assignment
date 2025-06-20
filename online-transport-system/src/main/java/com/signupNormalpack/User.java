package com.signupNormalpack;

public class User {
	private String userName;
	private String gender;
	private String email;
	private String password;
	private String phone;
	private String role;
	private String address;
	private String comments;
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getComments() {
		return comments;
	}
	
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public User(String userName, String gender, String email, String password, String phone,
			String role, String address, String comments) {
		super();
		this.userName = userName;
		this.gender = gender;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.role = role;
		this.address = address;
		this.comments = comments;
	}
}