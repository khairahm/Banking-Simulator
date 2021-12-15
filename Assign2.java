/*
 * Assign2.java
 * Khair Ahmed , 040946481
 * CST8132 300 - OOP
 * Lab 9
 * July 24th, 2019
 * Dr. Mohammad Kadum
 * 
 * Launches the application
 * 
 * Class List:
 * Assign2.java
 * Bank.java
 * BankAccount.java
 * ChequingAccount.java
 * SavingsAccount.java
 * Person.java
 */
/***********************************************************************************************/

import java.awt.EventQueue;

public class Assign2 {
	/**
	 * Launches the application
	 * 
	 * @author Khair Ahmed
	 * @version 1.2
	 * @since 1.8.0_191
	 */


	/*** Launch the application. */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
		
			/**
			 * method to run the program
			 */
			@Override
			public void run() {
				try {
					Bank bank = new Bank();
					
					bank.setVisible(true);
				
				}catch(Exception e) {
					e.printStackTrace();
				}				
			}
			
		});
		
		
	}

}
