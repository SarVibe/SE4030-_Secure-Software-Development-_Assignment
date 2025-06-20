package com.Logins;

public class Matchpassword extends Exception
{
   String message;
   
   Matchpassword(String message) 
   {
	   this.message = message;
   }
   
   String getmsg()
   {
	   return message;
   }
}
