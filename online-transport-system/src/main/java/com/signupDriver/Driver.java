package com.signupDriver;


import java.sql.Date;

public class Driver {
	String name;
	String address;
	int phonenumber;
	String vechilenumber;
	String licensenumber;
	Date expirydate;
	String password;
	String confirmpassword;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(int phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getVechilenumber() {
		return vechilenumber;
	}
	public void setVechilenumber(String vechilenumber) {
		this.vechilenumber = vechilenumber;
	}
	public String getLicensenumber() {
		return licensenumber;
	}
	public void setLicensenumber(String licensenumber) {
		this.licensenumber = licensenumber;
	}
	public Date getExpirydate() {
		return expirydate;
	}
	public void setExpirydate(Date expirydate) {
		this.expirydate = expirydate;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmpassword() {
		return confirmpassword;
	}
	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}
	
	public Driver(String name, String address, int phonenumber, String vechilenumber, String licensenumber,
			Date expirydate, String password, String confirmpassword) {
		super();
		this.name = name;
		this.address = address;
		this.phonenumber = phonenumber;
		this.vechilenumber = vechilenumber;
		this.licensenumber = licensenumber;
		this.expirydate = expirydate;
		this.password = password;
		this.confirmpassword = confirmpassword;
	}
}
