package com.Admins;


public class Admin {
	String userName;
    String gender; 
    String email; 
    String password;
    String phone; 
    String role;
    String address; 
    String comments;
    String status;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Admin(String userName, String gender, String email, String phone, String role, String address,
			String comments, String status) {
		super();
		this.userName = userName;
		this.gender = gender;
		this.email = email;
		this.phone = phone;
		this.role = role;
		this.address = address;
		this.comments = comments;
		this.status = status;
	}
    
    
}
