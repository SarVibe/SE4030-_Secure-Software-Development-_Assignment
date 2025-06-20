package com.Bookings;

import java.time.LocalTime;
import java.util.Date;

public class User {
	private String userName;
	private String PickupAddress;
	private String DropAddress;
	private String PhoneNumber;
	private LocalTime pickUpTime;
	private Date bookDate;
	private String DriverName;
	private int amount;
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPickupAddress() {
		return PickupAddress;
	}
	
	public void setPickupAddress(String pickupAddress) {
		PickupAddress = pickupAddress;
	}
	
	public String getDropAddress() {
		return DropAddress;
	}
	
	public void setDropAddress(String dropAddress) {
		DropAddress = dropAddress;
	}
	
	public String getPhoneNumber() {
		return PhoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}
	
	public LocalTime getPickUpTime() {
		return pickUpTime;
	}
	
	public void setPickUpTime(LocalTime pickUpTime) {
		this.pickUpTime = pickUpTime;
	}
	
	public Date getBookDate() {
		return bookDate;
	}

	public void setBookDate(Date bookDate) {
		this.bookDate = bookDate;
	}

	public String getDriverName() {
		return DriverName;
	}

	public void setDriverName(String driverName) {
		DriverName = driverName;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public User(String userName, String pickupAddress, String dropAddress, String phoneNumber, LocalTime pickUpTime) {
		super();
		this.userName = userName;
		PickupAddress = pickupAddress;
		DropAddress = dropAddress;
		PhoneNumber = phoneNumber;
		this.pickUpTime = pickUpTime;
	}
	
	public User( String pickupAddress, String dropAddress, LocalTime pickUpTime, Date bookDate, String DriverName, int amount ) {
		super();
		this.PickupAddress = pickupAddress;
		this.DropAddress = dropAddress;
		this.pickUpTime = pickUpTime;
		this.bookDate = bookDate;
		this.DriverName = DriverName;
		this.amount = amount;
	}
}