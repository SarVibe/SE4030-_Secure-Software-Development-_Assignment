package com.payments;

public class Payment {
	private int amount;
	private String paymentMethod;
	private String userName;
	
	public Payment(String userName, int amount, String paymentMethod) {
		this.amount = amount;
		this.userName = userName;
		this.paymentMethod = paymentMethod;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}