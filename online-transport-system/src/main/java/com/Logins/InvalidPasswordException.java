package com.Logins;

public class InvalidPasswordException extends Exception {
	String message;
	   
	InvalidPasswordException(String message) 
	   {
		   this.message = message;
	   }
	   
	   String getmsg()
	   {
		   return message;
	   }
}