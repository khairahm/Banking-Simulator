/*
 * BankAccount.java
 * Khair Ahmed , 040946481
 * CST8132 300 - OOP
 * Lab 7
 * July 10th, 2019
 * Dr. Mohammad Kadum
 * 
 * 
 * Parent class to chequing and savings account that contains all items 
 * that are required by all accounts
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



import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * 
 * Parent class that creates and intializes all required bank information that
 * is needed by both chequings and savings account objects
 * 
 * @author Khair Ahmed
 * @version 1.2
 * @since 1.8.0_191
 * @see ChequingAccount.java
 * @see SavingsAccount.java
 */
public abstract class BankAccount {
	/**
	 * DecimalFormat object used to format money
	 */
	protected DecimalFormat decimalFormat;

	/**
	 * Person object used to store user information
	 */
	protected Person accHolder;
	/**
	 * accountNumber of the bankAccount
	 */
	protected double accountNumber;
	/**
	 * balance of the account
	 */
	protected double balance;
	
	/**
	 * instance variable that holds the monthly fee that will be subtracted
	 */
	protected double fee;
	
	/**
	 * minimumBalance is the lowest amount of money that the account can have
	 */
	protected double minimumBalance;
	/**
	 * interestRate is percentage of money that will be compounded
	 */
	protected double interestRate;

	/**
	 * Default constructor that intializes Scanner object and DecimalFormat object
	 */
	public BankAccount() {
		
		decimalFormat = new DecimalFormat("#,###,###,###,##0.00");
	}
	
	/**
	 * 
	 * @return true if account is created
	 */
	public boolean addBankAccount(String firstNameField, String lastNameField, String phoneNumField, String emailAddressField) {
		String firstName = firstNameField;
		String lastName = lastNameField;
		String phoneNumber = phoneNumField;
		String emailAddress = emailAddressField;
		
		

		accHolder = new Person(firstName, lastName, Double.parseDouble(phoneNumber),  emailAddress);
		return true;

	}


	/**
	 * abstract method will subtract the fee from the chequing account or add the
	 * interest to the savings accounts
	 */
	public abstract void monthlyAccountUpdate();
	/**
	 * used to set values of the account type specific variables
	 */
	public abstract void setValues(double amt ,double amtTwo);
	
	/**
	 * used to deposit fund into a bank account
	 * @param amt amount that will be deposited into the account
	 * @throws IllegalArgumentException if the amount is less than 0
	 */
	public void deposit(double amt) {
		if (amt < 0) {
			throw new IllegalArgumentException();
		}
		else {
			this.balance+= amt;
		}		
	}
	/**
	 * will withdraw funds from a bank account
	 * @param amt amount that will be withdrawn from the account
	 * @throws IllegalArgumentException when the amt want to be withdrawn is greater than balance or less than 0
	 */
	public void withdraw(double amt) {
		if (amt < 0 || amt > this.balance) {
			/*
			 * if the value being inputed it a negative value, value is multipled by
			 * negative and then tested to see if the value is greater than the balance. If
			 * amt is greater than balance the transaction will not happen
			 */
			throw new IllegalArgumentException();

		} else { // if the number is not negative then the transaction will not complete

			this.balance -= amt;

		}

	}
	
	/**
	 * prints all user information in the account
	 * 
	 * @return all user information
	 */
	public String toString() {
		DecimalFormat numberFormat = new DecimalFormat("### ### #####");
		return numberFormat.format(this.accountNumber) + " " + accHolder.getName() + " "
				+ numberFormat.format(accHolder.getPhoneNumber()) + " " + accHolder.getEmailAddress()
				+ " " + decimalFormat.format(this.balance);
	}

}
