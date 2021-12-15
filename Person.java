/*
 * Person.java
 * Khair Ahmed , 040946481
 * CST8132 300 - OOP
 * Lab 9
 * July 24th, 2019
 * Dr. Mohammad Kadum
 * 
 * Contains all user information
 * 
 * Class List:
 * Assign1.java
 * Bank.java
 * BankAccount.java
 * ChequingAccount.java
 * SavingsAccount.java
 * Person.java
 */
/***********************************************************************************************/
/**
 * contains all user information on the account
 * 
 * 
 * @author Khair Ahmed
 * @version 1.2
 * @since 1.8.0_191
 */
public class Person {
	/**
	 * first name of the account owner
	 */
	private String firstName;
	/**
	 * last name of account owner
	 */
	private String lastName;
	/**
	 * phonenumber of account owner
	 */
	private double phoneNumber;
	/**
	 * email of account owner
	 */
	private String emailAddress;
	/**
	 * Constructor that sets all account information to the account
	 * @param firstName firstname of account owner
	 * @param lastName lastname of account owner
	 * @param phoneNumber phonenumber of account owner 
	 * @param emailAddress email address of account owner
	 */
	public Person (String firstName, String lastName, double phoneNumber, String emailAddress) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.phoneNumber = phoneNumber;
	}
	/**
	 * get first name and last name of account
	 * @return this.firstName first name of account
	 * @return this.lastName of account name
	 */
	public String getName () {
		return this.firstName + " " + this.lastName; 
	}
	/**
	 * get phonenumber of account owner
	 * @return this.phoneNumber
	 */
	public double getPhoneNumber () {
		return this.phoneNumber;
	}
	/**
	 * get the emailaddress of the account
	 * @return
	 */
	public String getEmailAddress() {
		return this.emailAddress;
	}
	
}
