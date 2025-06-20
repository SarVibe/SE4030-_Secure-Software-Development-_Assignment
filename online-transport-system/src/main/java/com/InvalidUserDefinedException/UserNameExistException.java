package com.InvalidUserDefinedException;

public class UserNameExistException extends Exception {
	public UserNameExistException(String msg) {
		super(msg);
	}
}