/*
 * ChequingAccount.java
 * Khair Ahmed , 040946481
 * CST8132 300 - OOP
 * Lab 9
 * July 24th 2019
 * Dr. Mohammad Kadum
 * 
 * subclass of bank account acts contains information and performs fuctions specific to the chequing account only
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
 * 
 * subclass of bank account acts contains information and performs fuctions
 * specific to the chequing account only
 * 
 * 
 * @author Khair Ahmed
 * @version 1.2
 * @since 1.8.0_191
 * @see BankAccount.java
 */

public class ChequingAccount extends BankAccount {
	
	
	
	/**
	 * default constructor that calls super() class;
	 */
	public ChequingAccount() {
		super();
	}
	/**
	 * 
	 */
	public boolean addBankAccount(String firstNameField, String lastNameField,String phoneNumField, String emailAddressField) {
		super.addBankAccount(firstNameField, lastNameField, phoneNumField, emailAddressField);
		

		return true;
	}
	/**
	 * Subtracts fee from the bank account
	 */

	@Override
	public void monthlyAccountUpdate() {
		balance -= fee;
		if (balance < fee) {
			balance = 0;
		}

	}
	/**
	 * prints out all user information from super method  and then adds the monthly fee
	 */
	@Override
	public String toString() {
		return "c " + super.toString() + " " + decimalFormat.format(this.fee);
	}
	
	/**
	 * set the fee of the account and the balance of the account
	 * used only for data imports
	 * @param fee of the account
	 * @param balance balance of the account
	 */
	public void setValues(double fee, double balance) {
		this.fee = fee;
		this.balance = balance;
		
	}

}
