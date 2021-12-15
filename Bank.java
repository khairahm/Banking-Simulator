/*
 * Bank.java
 * Khair Ahmed , 040946481
 * CST8132 300 - OOP
 * Lab 7
 * July 10th, 2019
 * Dr. Mohammad Kadum
 * 
 * JFrame that set the GUI
 * 
 * Class List:
 * Assign1.java
 * Bank.java
 * BankAccount.java
 * ChequingAccount.java
 * SavingsAccount.java
 * Person.java
 */
/**********************************************************************************/
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.text.DecimalFormat;
/**
 * 
 * GUI of the application
 * 
 * @author Khair Ahmed
 * @version 1.2
 * @since 1.8.0_191
 * 
 */
public class Bank extends JFrame{
	DecimalFormat decimalFormat = new DecimalFormat("#,####,####,###0.00");
	/**
	 * Array of BankAccounts
	 */
	private static ArrayList<BankAccount> accounts;
	/**
	 * Used to tract the number of accounts that have been initialized
	 */
	private int numAccounts;
	/**
	 * allows user to know what month the monthly update is on
	 */
	private int month;
	/**
	 * Max size of the accounts
	 */

	
	private int maxSize;
	/**
	 * Scanner object used to open and read information
	 */
	private Scanner openFile;

	/**
	 * used to create an import after the information is printed
	 */
	private Formatter createFile;
	/**
	 * JTabbed pane that is used to control menu
	 */
	private final JTabbedPane menu;
	/**
	 * the display where all prompts and outputs come out
	 */
	private final JPanel[] display;

	// addAccount swing objects
	/**
	 * first name label for the add account display
	 */
	private final JLabel addAccountFirstNameLabel;
	/**
	 * last name label for addAccount display
	 */
	private final JLabel addAccountLastNameLabel;
	/**
	 * email label for the add account display
	 */
	private final JLabel addAccountEmailLabel;
	/**
	 * email prompt for add account display
	 */
	private final JTextField addAccountEmailField;
	/**
	 * last name prompt for the add account display
	 */
	private final JTextField addAccountLastNameField;
	/**
	 * first name prompt for the add account display
	 */
	private final JTextField addAccountFirstNameField;
	/**
	 * lets the user select wether a savings or chequings account is made 
	 */
	private final JRadioButton[] addAccTypeChoice;
	/**
	 * groups the J Buttons together
	 */
	private final ButtonGroup accTypeGroup;
	/**
	 * label for chequings or savings account
	 */
	private final JLabel addAccTypeLabel;
	/**
	 * balance label for add account typpe label
	 */
	private final JLabel addAccBalanceLabel;
	/**
	 * balance prompt for the add account display
	 */
	private final JTextField addAccBalanceField;
	/**
	 * phone number label for the phone num field
	 */
	private final JLabel addAccPhoneNumLabel;
	/**
	 * phone number prompt for add account display
	 */
	private final JTextField addAccPhoneNumField;

	// Label will change from interest rate to fee depending on what JRadioButton is
	// selected
	/**
	 * if the chequing jradio button is selected it will act as the fee label 
	 * if savings jradio button is selected it will act as the interest rate
	 */
	private final JLabel addAccDynamicLabel;
	/**
	 * field that will take fee or interest rate depending on which j radio button is selected
	 */
	private final JTextField addAccDynamicField;
	/**
	 * account number label 
	 */
	private final JLabel addAccountNoLabel;
	/**
	 * account number field that will be added to each bank account
	 */
	private final JTextField addAccountNoField;
	/**
	 * min bal label for the add account display
	 */
	private final JLabel addAccMinBalLabel;
	/**
	 * min bal field that will be added the bank account field
	 */
	private final JTextField addAccMinBalField;
	/**
	 * quit button that will close the window
	 */
	private final JButton addAccountQuitButton;
	/**
	 * add account button which will test if all fields are valid and then create a savings or chequings account
	 */
	private final JButton addAccountButton;
	/**
	 * output for user to see invalid input and if success occured or not
	 */
	private final JTextArea addAccountOutput;

	// WithDraw and Deposit Swing Objects tab2
	/**
	 * account number label for the withdraw and deposit field
	 */
	private final JLabel wAccountNoLabel;
	/**
	 * field where user enter the account number of the account they wish to access
	 */
	private final JTextField wAccountNoField;
	/**
	 * jbutton that will search for the account and print it out
	 */
	private final JButton wFindAccount;
	/**
	 * label for the transaction choice
	 */
	private final JLabel wTransactionChoiceLabel;
	/**
	 * J radio button that lets the user pick wether they want to deposit or withdraw
	 */
	private final JRadioButton[] wTransactionType;
	/**
	 * label  for the withdraw field  
	 */
	private final JLabel wAmountLabel;
	/**
	 * field where user will enter how muuch they wish to deposit
	 */
	private final JTextField wAmountField;
	/**
	 * quiit button that quit the window
	 */
	private final JButton wQuitButton;
	/**
	 * enter button that will add the deposit or take out the wtithdraw
	 */
	private final JButton wEnterButton;
	/**
	 * withdraw output
	 */
	private final JTextArea wOutput;
	/**
	 * deposit or withdraw group
	 */
	private final ButtonGroup wButtonGroup;

	// display account swing tab3
	/**
	 * account number label for display tab
	 */
	private final JLabel dAccountNoLabel;
	/**
	 * display tab account no field
	 */
	private final JTextField dAccountNoField;
	/**
	 * find account button display tab
	 */
	private final JButton dFindAccount;
	/**
	 * display tab quit button
	 */
	private final JButton dQuitButton;
	/**
	 * display output
	 */
	private final JTextArea dOutput;

	// print accounts swing object tab4
	/**
	 * print all account button
	 */
	private final JButton printAllAccounts;
	/**
	 * print button quit
	 */
	private final JButton pQuitButton;
	/**
	 * print outputof all accounts here
	 */
	private final JTextArea pOutput;
	// runMonthly Update swing objects tab 5
	/**
	 * used to run monthly update
	 */
	private final JButton runButton;
	/**
	 * output for monthly update tab
	 */
	private final JLabel rOutput;
	/**
	 * quit button for monthly run 
	 */
	private final JButton rQuitButton;

	// Show Index swing object tab 6
	/**
	 * account no label
	 */
	private final JLabel iAccountNoLabel;
	/**
	 * textfield for account number prompt
	 */
	private final JTextField iAccountNoField;
	/**
	 * used to find the account number
	 */
	private final JButton iFindAccount;
	/**
	 * index label
	 */
	private final JLabel indexLabel;
	/**
	 * output that shows the index
	 */
	private final JLabel iOutput;
	/**
	 * quit button for index tab
	 */
	private final JButton iQuitButton;

	// Import list tab 7
	/**
	 * sucess label is used for the output
	 */
	private final JTextArea sucessLabel;
	/**
	 * used to import the data
	 */
	private final JButton importDataButton;
	/**
	 * quit button for tab 7
	 */
	private final JButton dataQuitButton;
	
	/**
	 * Bank constructor that sets up the gui and create the interface
	 */
	
	public Bank() {
		//name account
		super("Khair's Banking Application");
		numAccounts = 0;
		maxSize = 1000;
		accounts = new ArrayList<BankAccount>();
		//allow the objects to be placed and allow normal window functionality
		setSize(900, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		//menu will be a bunch of tabs
		menu = new JTabbedPane();

		display = new JPanel[7];
		// create and add tabs
		for (int i = 0; i < display.length; i++) {
			display[i] = new JPanel();
			display[i].setLayout(new GridBagLayout());
		}
		menu.addTab("Add Account", display[0]);
		menu.addTab("Withdraw or Deposit", display[1]);
		menu.addTab("Display an Account", display[2]);
		menu.addTab("Print All Accounts", display[3]);
		menu.addTab("Run Monthly Update", display[4]);
		menu.addTab("Show Index", display[5]);
		menu.addTab("Import Database", display[6]);
		add(menu);
		// Add account information TAB1
		addAccountFirstNameLabel = new JLabel("First Name:  ");
		display[0].add(addAccountFirstNameLabel, c);
		c.gridx++;
		c.gridwidth = 2;
		addAccountFirstNameField = new JTextField(15);
		display[0].add(addAccountFirstNameField, c);
		addAccountFirstNameField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				// https://stackoverflow.com/questions/34377607/how-to-make-jtextfield-only-accept-characters-in-netbeans

				char letterInput = e.getKeyChar();
				if (!(Character.isAlphabetic(letterInput) || (letterInput == KeyEvent.VK_BACK_SPACE)
						|| letterInput == KeyEvent.VK_DELETE))
					e.consume();
			}

		});

		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy++;

		addAccountLastNameLabel = new JLabel("Last Name: ");
		display[0].add(addAccountLastNameLabel, c);
		c.gridx++;
		c.gridwidth = 2;
		addAccountLastNameField = new JTextField(15);
		display[0].add(addAccountLastNameField, c);
		addAccountLastNameField.addKeyListener(new KeyAdapter() {
			/**
			 * ensure that only letteres are typed in
			 */
			public void keyTyped(KeyEvent e) {
				// https://stackoverflow.com/questions/34377607/how-to-make-jtextfield-only-accept-characters-in-netbeans

				char letterInput = e.getKeyChar();
				if (!(Character.isAlphabetic(letterInput) || (letterInput == KeyEvent.VK_BACK_SPACE)
						|| letterInput == KeyEvent.VK_DELETE))
					e.consume();
			}

		});
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy++;
		//place email gui parts
		addAccountEmailLabel = new JLabel("Email: ");
		display[0].add(addAccountEmailLabel, c);
		c.gridx++;
		c.gridwidth = 2;
		addAccountEmailField = new JTextField(15);

		display[0].add(addAccountEmailField, c);
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy++;
		//place phonenumber gui parts
		addAccPhoneNumLabel = new JLabel("Phone Number: ");
		display[0].add(addAccPhoneNumLabel, c);
		c.gridx++;
		c.gridwidth = 2;
		addAccPhoneNumField = new JTextField(15);
		display[0].add(addAccPhoneNumField, c);
		c.gridx--;
		c.gridwidth--;
		c.gridy++;
		//place account type gui parts
		addAccTypeLabel = new JLabel("Account Type: ");
		display[0].add(addAccTypeLabel, c);
		c.gridx++;
		addAccTypeChoice = new JRadioButton[2];
		addAccTypeChoice[0] = new JRadioButton("Savings", true);
		addAccTypeChoice[1] = new JRadioButton("Chequings", false);
		accTypeGroup = new ButtonGroup();
		accTypeGroup.add(addAccTypeChoice[0]);
		accTypeGroup.add(addAccTypeChoice[1]);
		display[0].add(addAccTypeChoice[0], c);
		c.gridx++;
		display[0].add(addAccTypeChoice[1], c);
		AccTypeHandler accTypeHandler = new AccTypeHandler();
		addAccTypeChoice[0].addActionListener(accTypeHandler);
		addAccTypeChoice[1].addActionListener(accTypeHandler);
		//place account number gui parts
		addAccountNoLabel = new JLabel("Account Number: ");
		c.gridx = 0;
		c.gridy++;
		display[0].add(addAccountNoLabel, c);
		c.gridx++;
		c.gridwidth++;
		addAccountNoField = new JTextField(15);
		display[0].add(addAccountNoField, c);
		c.gridwidth--;
		// place balance gui parts
		addAccBalanceLabel = new JLabel("Balance");
		c.gridx = 0;
		c.gridy++;
		display[0].add(addAccBalanceLabel, c);
		c.gridx++;
		c.gridwidth = 2;
		addAccBalanceField = new JTextField(15);
		display[0].add(addAccBalanceField, c);
		c.gridwidth = 1;
		//place interest rate parts
		addAccDynamicLabel = new JLabel("Interest Rate: ");
		c.gridx = 0;
		c.gridy++;
		display[0].add(addAccDynamicLabel, c);
		c.gridwidth = 2;
		c.gridx++;
		addAccDynamicField = new JTextField(15);
		display[0].add(addAccDynamicField, c);
		c.gridwidth--;
		// place minimum balance gui parts
		addAccMinBalLabel = new JLabel("Minimum Balance: ");
		c.gridx = 0;
		c.gridy++;
		display[0].add(addAccMinBalLabel, c);
		c.gridx++;
		c.gridwidth++;
		addAccMinBalField = new JTextField(15);
		display[0].add(addAccMinBalField, c);
		c.gridwidth--;
		c.gridx = 0;
		c.gridy++;
		// place quit button for tab 1 
		addAccountQuitButton = new JButton("Quit");
		display[0].add(addAccountQuitButton, c);
		QuitButtonHandler quitButtonHandler = new QuitButtonHandler();
		addAccountQuitButton.addActionListener(quitButtonHandler);
		c.gridx += 2;
		// place add account button
		addAccountButton = new JButton("Add Account");
		display[0].add(addAccountButton, c);
		AddAccountHandler addAccountHandler = new AddAccountHandler();
		addAccountButton.addActionListener(addAccountHandler);
		c.gridx = 4;
		c.gridy = 0;
		c.gridheight = 9;
		//place tab 1 output
		addAccountOutput = new JTextArea();
		addAccountOutput.setEditable(false);
		display[0].add(addAccountOutput, c);
		addAccountOutput.setText("Output");
		c.gridheight = 1;
		// Tab2
		c.gridx = 0;
		c.gridy = 0;
		//place account number parts
		wAccountNoLabel = new JLabel("Account Number:         ");
		display[1].add(wAccountNoLabel, c);
		c.gridx++;
		c.gridwidth++;
		wAccountNoField = new JTextField(15);
		display[1].add(wAccountNoField, c);
		c.gridx = 4;
		c.gridwidth = 1;
		//find account
		wFindAccount = new JButton("Find Account");
		display[1].add(wFindAccount, c);
		wFindAccount.addActionListener(new ActionListener() {
			/**
			 * search for the correct account number
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					//if no account exists
					if(numAccounts ==0) {
						wAccountNoField.setText("");
						wOutput.setText("No Accounts Exist");
					}else {
						if (isDouble(wAccountNoField.getText())==true) {
							//error check
						if (wAccountNoField.getText().matches("^\\d{1,8}$")) {
							//searchs for accounts
							for (int i = 0; i < accounts.size(); i++) {
								if (accounts.get(i).accountNumber == Integer.parseInt(wAccountNoField.getText())) {
									wOutput.setText("Account Found: " + accounts.get(i).toString());
									wTransactionChoiceLabel.setVisible(true);
									wTransactionType[0].setVisible(true);
									wTransactionType[1].setVisible(true);
									wAmountLabel.setVisible(true);
									wAmountField.setVisible(true);
									wEnterButton.setVisible(true);
									wAccountNoField.setEditable(false);
									break;
									

								}else {
									wOutput.setText("Error: Account Number with that account number does not exist");
								}
								
							}

						}
						}else {
							wOutput.setText("Error: Enter a valid input");
							wAccountNoField.setText("");
						}
					}
				}catch(NumberFormatException ex) {
					wOutput.setText("Error: Enter a real number");
				}
			
				
				
			}

		});
		c.gridx = 0;
		c.gridy = 1;
		//place objects
		wTransactionChoiceLabel = new JLabel("Withdraw or Deposit: ");
		display[1].add(wTransactionChoiceLabel, c);
		wTransactionChoiceLabel.setVisible(false);

		wTransactionType = new JRadioButton[2];
		wTransactionType[0] = new JRadioButton("Withdraw", true);
		wTransactionType[0].setVisible(false);
		wTransactionType[1] = new JRadioButton("Deposit", false);
		wTransactionType[1].setVisible(false);
		wButtonGroup = new ButtonGroup();
		wButtonGroup.add(wTransactionType[0]);
		wButtonGroup.add(wTransactionType[1]);

		c.gridx++;
		display[1].add(wTransactionType[0], c);
		c.gridx++;
		display[1].add(wTransactionType[1], c);
		c.gridx = 0;

		wAmountLabel = new JLabel("Amount: ");
		c.gridy++;
		display[1].add(wAmountLabel, c);
		wAmountLabel.setVisible(false);

		wAmountField = new JTextField(15);
		c.gridx++;
		c.gridwidth = 2;
		display[1].add(wAmountField, c);
		wAmountField.setVisible(false);
		c.gridwidth--;
		c.gridx = 0;

		wQuitButton = new JButton("Quit");
		c.gridy++;
		display[1].add(wQuitButton, c);
		wQuitButton.addActionListener(quitButtonHandler);

		c.gridwidth += 2;
		c.gridx++;
		wEnterButton = new JButton("Enter");
		display[1].add(wEnterButton, c);
		wEnterButton.setVisible(false);
		WithdrawOrDepositHandler withdrawOrDepositHandler = new WithdrawOrDepositHandler();
		wEnterButton.addActionListener(withdrawOrDepositHandler);
		

		c.gridx--;
		c.gridwidth = 4;
		c.gridy++;
		wOutput = new JTextArea("Output");
		display[1].add(wOutput, c);

		// Tab3 display account
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 0;

		dAccountNoLabel = new JLabel("Account Number:         ");
		display[2].add(dAccountNoLabel, c);
		c.gridx++;
		c.gridwidth++;
		dAccountNoField = new JTextField(15);
		display[2].add(dAccountNoField, c);
		c.gridx += 2;
		c.gridwidth--;
		dFindAccount = new JButton("Find Account");
		display[2].add(dFindAccount, c);
		DisplayAccountHandler displayAccountHandler = new DisplayAccountHandler();
		dFindAccount.addActionListener(displayAccountHandler);
		c.gridx = 0;
		c.gridy++;
		dQuitButton = new JButton("Quit");
		display[2].add(dQuitButton, c);
		dQuitButton.addActionListener(quitButtonHandler);

		c.gridy++;
		c.gridwidth = 4;
		dOutput = new JTextArea("Output");
		display[2].add(dOutput, c);
		c.gridwidth = 0;

		// Tab4
		c.gridx = 0;
		c.gridy = 0;
		printAllAccounts = new JButton("Print All Accounts");
		display[3].add(printAllAccounts, c);
		PrintAccountHandler printAccountHandler = new PrintAccountHandler();
		printAllAccounts.addActionListener(printAccountHandler);
		c.gridy++;

		pQuitButton = new JButton("Quit");
		display[3].add(pQuitButton, c);
		pQuitButton.addActionListener(quitButtonHandler);
		c.gridy++;
		pOutput = new JTextArea("Output");
		display[3].add(pOutput, c);

		// Tab5
		c.gridx = 0;
		c.gridy = 0;
		runButton = new JButton("Run Monthly Update");
		display[4].add(runButton, c);
		RunMonthlyUpdate runMonthlyUpdate = new RunMonthlyUpdate();
		runButton.addActionListener(runMonthlyUpdate);
		c.gridy++;
		rQuitButton = new JButton("Quit");
		rQuitButton.addActionListener(quitButtonHandler);
		display[4].add(rQuitButton, c);
		c.gridy++;
		rOutput = new JLabel("Output");
		display[4].add(rOutput, c);

		// Tab6
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 0;

		iAccountNoLabel = new JLabel("Account Number:      ");
		display[5].add(iAccountNoLabel, c);
		c.gridx++;
		c.gridwidth++;
		iAccountNoField = new JTextField(15);
		display[5].add(iAccountNoField, c);
		c.gridx += 2;
		c.gridwidth--;
		iFindAccount = new JButton("Find Account");
		display[5].add(iFindAccount, c);
		FindIndexHandler findIndexHandler = new FindIndexHandler();
		iFindAccount.addActionListener(findIndexHandler);
		c.gridx = 0;
		c.gridy++;
		indexLabel = new JLabel("Index: ");
		display[5].add(indexLabel, c);
		c.gridx++;
		c.gridwidth++;
		iOutput = new JLabel("Output");
	
		
		display[5].add(iOutput, c);
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy++;
		iQuitButton = new JButton("Quit");
		display[5].add(iQuitButton, c);
		iQuitButton.addActionListener(quitButtonHandler);
		c.gridy = 0;

		// tab 7
		importDataButton = new JButton("Import Data");
		display[6].add(importDataButton, c);
		ImportDatabaseHandler importDataHandler = new ImportDatabaseHandler();
		try {
		importDataButton.addActionListener(importDataHandler);
		}catch(Exception ex) {
			
		}
		c.gridy++;
		sucessLabel = new JTextArea("Output");
		display[6].add(sucessLabel, c);

		c.gridy++;
		dataQuitButton = new JButton("Quit");
		display[6].add(dataQuitButton, c);
		dataQuitButton.addActionListener(quitButtonHandler);
	}
	
	/**
	 * will open the file
	 */
	public void openInputFile() {

		try {
			openFile = new Scanner(new File("bankData.txt"));
		} catch (Exception ex) {
			System.out.println("Not account imported");
		}

	}
	
	/**
	 * read record will take the data base and read the information and create bank accounts
	 */
	public void readRecords() {
		/**
		 * first name of account being created
		 */
		String firstName;
		/**
		 * last name of account being created
		 */
		String lastName;
		/**
		 * account number of account being created
		 */
		String accountNumber;
		/**
		 * email of account being created
		 */
		String emailAddress;
		/**
		 * balance of account being created
		 */
		String balance;
		/**
		 *phone number of account being created
		 */
		String phoneNumber;
		/**
		 * used to recognize if chequing or savings account is made
		 */
		String accType;
		/**
		 * minimum balance of account being created 
		 * (only if accType is s)
		 */
		String minBal = null;
		/**
		 * interest rate of account being created
		 * (only if accType is s)
		 */
		String interestRate = null;
		/**
		 * fee of the account being created
		 * (only if accType is c)
		 */
		String fee = null;
		sucessLabel.setText("");
		/*
		 *checks for errors and adds the information to the accounts if all 
		 *information is valid 
		 */
		while (openFile.hasNext()) {
			accType = openFile.next();
			if (!(accType.equalsIgnoreCase("S"))&&!(accType.equalsIgnoreCase("C"))) {
				sucessLabel.append( "\n" + accType+ " is not a valid account type");
			}
			accountNumber = openFile.next();
			if (!accountNumber.matches("^\\d{1,8}$")) {
				sucessLabel.append(accountNumber +" is not a valid account number\n")		;
				}
			firstName = openFile.next();
			if (!firstName.matches("[a-zA-Z]+")) {
				sucessLabel.append(firstName + " is not a valid name \n");
			}
			lastName = openFile.next();
			if (!lastName.matches("[a-zA-Z]+")) {
				sucessLabel.append(lastName + " is not a valid name \n");
			}
			phoneNumber = openFile.next();
			if (!phoneNumber.matches("^\\d{1,10}$")
					|| !(phoneNumber.length() == 10)) {
				sucessLabel.append( phoneNumber+" is not a valid phone number\n");
			}
			emailAddress = openFile.next();
			if (!emailAddress.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
				sucessLabel.append(emailAddress +" is not a valid email address\n");
			}
			balance = openFile.next();
			if (isDouble(balance) == false) {
				sucessLabel.append(balance +" is not an amount\n");
			}
			if (accType.equalsIgnoreCase("C")) {
				fee = openFile.next();
				if (isDouble(fee)== false || Double.parseDouble(fee)>Double.parseDouble(balance)||Double.parseDouble(fee)<0) {
					sucessLabel.append(fee +" is not an valid input (Must be between 1 and the balance) \n");
				}

			} else if (accType.equalsIgnoreCase("S")) {
				interestRate = openFile.next();
				if (isDouble(interestRate)== false || Double.parseDouble(interestRate)>1.0||Double.parseDouble(interestRate)<0) {
					sucessLabel.append(interestRate +" is not an valid input (Must be valid number between 0-1)\n");
				}
				minBal = openFile.next();
				try {
				if (isDouble(minBal)== false || Double.parseDouble(minBal)>Double.parseDouble(balance)||Double.parseDouble(minBal)<0) {
					sucessLabel.append(minBal +" is not an valid input (Must be valid number less than " + balance + ")\n");
				}
				}catch(NumberFormatException ex) {
					
				}
				


			}
			// if all information is valid then it will add the account
			if (accType.equalsIgnoreCase("S")
					&& accountNumber.matches("^\\d{1,8}$") 
					&& firstName.matches("[a-zA-Z]+")
					&& lastName.matches("[a-zA-Z]+")
					&&phoneNumber.matches("^\\d{1,10}$")&& (phoneNumber.length() == 10)
					&& emailAddress.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")
					&& isDouble(balance) == true 
					&&isDouble(minBal)== true&&Double.parseDouble(minBal)<Double.parseDouble(balance)&&Double.parseDouble(minBal)>0 
					&&isDouble(interestRate)== true&& Double.parseDouble(interestRate)<1.0&&Double.parseDouble(interestRate)>0 ){
				accounts.add(new SavingsAccount());
				accounts.get(numAccounts).accHolder = new Person(firstName, lastName, Double.valueOf(phoneNumber),
						emailAddress);
				accounts.get(numAccounts).balance = Double.parseDouble(balance);
				accounts.get(numAccounts).accountNumber =Double.parseDouble(accountNumber);
				accounts.get(numAccounts).setValues(Double.parseDouble(minBal), Double.parseDouble(interestRate));
				numAccounts++;
				sucessLabel.append("Success: " + numAccounts + " accounts has been imported\n");
				importDataButton.setVisible(false);


					}
				 
			else if (accType.equalsIgnoreCase("C")
					&& accountNumber.matches("^\\d{1,8}$") 
					&& firstName.matches("[a-zA-Z]+")
					&& lastName.matches("[a-zA-Z]+")
					&&phoneNumber.matches("^\\d{1,10}$")&& (phoneNumber.length() == 10)
					&& emailAddress.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")
					&& isDouble(balance) == true 
					&&isDouble(fee)== true&& Double.parseDouble(fee)<Double.parseDouble(balance)&&Double.parseDouble(fee)>0){
				accounts.add(new ChequingAccount());
				accounts.get(numAccounts).accHolder = new Person(firstName, lastName, Double.valueOf(phoneNumber),
						emailAddress);
				accounts.get(numAccounts).accountNumber = Integer.parseInt(accountNumber);
				accounts.get(numAccounts).setValues(Double.parseDouble(fee), Double.parseDouble(balance));
				numAccounts++;
				sucessLabel.append("Success: "+ numAccounts + " accounts has been imported\n");
				importDataButton.setVisible(false);

			}
			else {// if the account information for the line is not correct then it will say that line was not imported
				sucessLabel.append("The account has not been imported\n");
			}
			
			
			
		}
	}
	
	/**
	 * close the file after all information is created
	 */
	public void closeInputFile() {
		openFile.close();//close file


	}
	/**
	 * create a new file
	 */
	public void openOutputFile() {
		try {
			createFile = new Formatter("bankoutput.txt");
		} catch (FileNotFoundException ex) {
			System.out.println("File not created");
		}

	}
	/**
	 * close the file
	 */
	public void closeOutputFile() {
		createFile.close();
	}
	/**
	 * used to create an account 
	 * @return true if account is made successfully
	 * @return false is account isnot made sucessfully
	 */
	public boolean addAccount() {
		/*
		 * if numAccount is equal to the max size, then tell user no space in database
		 */
		if (numAccounts == maxSize) {
			System.out.println("No more accounts can be added, reached limit of database.");
			return false;
		}
		// create and set information for savings accounts
		if (addAccTypeChoice[0].isSelected()) {
			accounts.add(new SavingsAccount());
			accounts.get(numAccounts).addBankAccount(addAccountFirstNameField.getText(),
					addAccountLastNameField.getText(), addAccPhoneNumField.getText(), addAccountEmailField.getText());
			accounts.get(numAccounts).balance = Double.parseDouble(addAccBalanceField.getText());
			accounts.get(numAccounts).accountNumber = Integer.parseInt(addAccountNoField.getText());
			accounts.get(numAccounts).interestRate = Double.parseDouble(addAccDynamicField.getText());
			accounts.get(numAccounts).minimumBalance = Double.parseDouble(addAccMinBalField.getText());

			numAccounts += 1;
			return true;
			//set information for chequing account
		} else if (addAccTypeChoice[1].isSelected()) {
			accounts.add(new ChequingAccount());
			accounts.get(numAccounts).addBankAccount(addAccountFirstNameField.getText(),
					addAccountLastNameField.getText(), addAccPhoneNumField.getText(), addAccountEmailField.getText());
			accounts.get(numAccounts).balance = Double.parseDouble(addAccBalanceField.getText());
			accounts.get(numAccounts).accountNumber = Integer.parseInt(addAccountNoField.getText());
			accounts.get(numAccounts).fee = Double.parseDouble(addAccDynamicField.getText());

			numAccounts += 1;
			return true;
		}

		return true;
	}
	/**
	 * 
	 * class that changes the tab 1 interface based on chequing for savings selected
	 *
	 */
	private class AccTypeHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (addAccTypeChoice[0].isSelected()) {
				addAccDynamicLabel.setText("Interest Rate: ");
				addAccMinBalLabel.setVisible(true);
				addAccMinBalField.setVisible(true);
			} else {
				addAccDynamicLabel.setText("Monthly Fee: ");
				addAccMinBalLabel.setVisible(false);
				addAccMinBalField.setVisible(false);
			}
		}

	}
	/**
	 * 
	 *class used to quit button on every tab
	 *
	 */
	private class QuitButtonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}

	}
	/**
	 * 
	 * used to withdraw or deposit funds from a specific account
	 *
	 */
	
	private class WithdrawOrDepositHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
		try {
			if (numAccounts ==0 ) {
				wOutput.setText("Error: no Account exist");
			}else {
				if (wTransactionType[0].isSelected()) {
					
					for (int i = 0; i < numAccounts; i++) {
						if (accounts.get(i).accountNumber == Double.parseDouble(wAccountNoField.getText())) {
							wOutput.setText("Account Found: " + accounts.get(i).toString());
							if (Double.parseDouble(wAmountField.getText())>0 &&Double.parseDouble(wAmountField.getText())< accounts.get(i).balance) {
							accounts.get(i).withdraw(Double.parseDouble(wAmountField.getText()));
							wOutput.append("\nThe account balance is now: "+ decimalFormat.format(accounts.get(i).balance));
							break;
							}
							else {
								wOutput.setText("Error: amount is less than 0 or greater than balance");
							}

						}else {
							wOutput.setText("Transaction Failed");

						}
						
					}

				
					
				
			}
			
			if (wTransactionType[1].isSelected()) {
				
					for (int i = 0; i < numAccounts; i++) {
						if (accounts.get(i).accountNumber == Double.parseDouble(wAccountNoField.getText())) {
							wOutput.setText("Account Found: " + accounts.get(i).toString());
							if (Double.parseDouble(wAmountField.getText())>0) {
							accounts.get(i).deposit(Double.parseDouble(wAmountField.getText()));
							
							wOutput.append("\nThe account balance is now: "+ decimalFormat.format(accounts.get(i).balance));

							break;
							}
							else {
								wOutput.setText("Please enter a posit number");
							}

						}
						else {
							wOutput.setText("Transaction Failed");
						}
						
					}

				
			}
			wAccountNoField.setText("");
			wAccountNoField.setEditable(true);
			wTransactionChoiceLabel.setVisible(false);
			wTransactionType[0].setVisible(false);
			wTransactionType[1].setVisible(false);
			wAmountLabel.setVisible(false);
			wAmountField.setVisible(false);
			wAmountField.setText("");
			wEnterButton.setVisible(false);
			wAccountNoField.setEditable(true);
			
			
			}
		
		}catch (NumberFormatException ex) {
			wOutput.append("\nError: Enter a valid Number");
		}
		
		}
		
	}
	
	private class AddAccountHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			addAccountOutput.setText("");

			if (addAccountFirstNameField.getText().matches("")) {
				addAccountOutput.append("Invalid Input: Please enter a valid first name\n");
			}

			if (addAccountLastNameField.getText().matches("")) {
				addAccountOutput.append("Invalid Input: Please enter a valid last name\n");
			}
			if (!addAccountEmailField.getText().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
				addAccountOutput.append("Invalid Input: Please enter a valid email\n");
			}
			// https://stackoverflow.com/questions/3968049/regex-allow-a-string-to-only-contain-numbers-0-9-and-limit-length-to-45
			if (!addAccountNoField.getText().matches("^\\d{1,8}$")) {
				addAccountOutput.append(
						"Invalid input: Enter only numbers and a number length from 1-8 for the Account Number \n");
			}

			if (!addAccPhoneNumField.getText().matches("^\\d{1,10}$")
					|| !(addAccPhoneNumField.getText().length() == 10)) {
				addAccountOutput.append("Invalid input: Enter a valid 10 digit phone number");
			}

			if (isDouble(addAccBalanceField.getText()) == false) {
				addAccountOutput.append("Invalid Input: Enter only numbers for the Balance \n");
			}

			if (addAccTypeChoice[0].isSelected() && isDouble(addAccDynamicField.getText()) == true) {
				if (Double.parseDouble(addAccDynamicField.getText()) < 0
						|| Double.parseDouble(addAccDynamicField.getText()) > 1) {
					addAccountOutput.append("Invalid Input: Enter a value between 0-1 for the interest Rate\n");
				}
			}

			if (addAccTypeChoice[0].isSelected() && isDouble(addAccDynamicField.getText()) == false) {
				addAccountOutput.append("Invalid Input: Enter a value between 0-1 for the interest Rate\n");
			}

			if (addAccTypeChoice[1].isSelected() && isDouble(addAccDynamicField.getText()) == false) {
				addAccountOutput.append("Invalid Input: Enter a value between 1-10 for the monthly fee\n");
			}

			if (addAccTypeChoice[1].isSelected() && isDouble(addAccDynamicField.getText()) == true) {
				if (Double.parseDouble(addAccDynamicField.getText()) < 1
						|| Double.parseDouble(addAccDynamicField.getText()) > 10) {
					addAccountOutput.append("Invalid Input: Enter a value between 1-10 for the monthly fee\n");
				}
			}

			if (addAccTypeChoice[0].isSelected() && isDouble(addAccMinBalField.getText()) == true
					&& isDouble(addAccBalanceField.getText()) == true) {
				if (Double.parseDouble(addAccMinBalField.getText()) > Double.parseDouble(addAccBalanceField.getText())
						|| Double.parseDouble(addAccMinBalField.getText()) < 0) {
					addAccountOutput.append(
							"Invalid Input: Enter a min balance less than the account balance and greater than 0\n");
				}
			}
			if (addAccTypeChoice[0].isSelected() && isDouble(addAccMinBalField.getText()) == false) {

				addAccountOutput.append("Invalid Input: Enter a min balance less than the account balance\n");

			}

			if (addAccTypeChoice[0].isSelected() && !addAccountFirstNameField.getText().matches("")
					&& !addAccountLastNameField.getText().matches("")
					&& addAccountEmailField.getText().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")
					&& addAccountNoField.getText().matches("^\\d{1,8}$")
					&& isDouble(addAccBalanceField.getText()) == true && isDouble(addAccDynamicField.getText()) == true
					&& !(Double.parseDouble(addAccDynamicField.getText()) < 0
							|| Double.parseDouble(addAccDynamicField.getText()) > 1)
					&& isDouble(addAccMinBalField.getText()) == true
					&& !(Double.parseDouble(addAccMinBalField.getText()) > Double
							.parseDouble(addAccBalanceField.getText())
							|| Double.parseDouble(addAccMinBalField.getText()) < 0)
					&& (addAccPhoneNumField.getText().matches("^\\d{1,10}$")
							&& (addAccPhoneNumField.getText().length() == 10))) {
				addAccountOutput.append("Success");
				addAccount();
				addAccountFirstNameField.setText("");
				addAccountLastNameField.setText("");
				addAccountEmailField.setText("");
				addAccountNoField.setText("");
				addAccBalanceField.setText("");
				addAccDynamicField.setText("");
				addAccMinBalField.setText("");
				addAccPhoneNumField.setText("");

			}

			if (addAccTypeChoice[1].isSelected() && !addAccountFirstNameField.getText().matches("")
					&& !addAccountLastNameField.getText().matches("")
					&& addAccountEmailField.getText().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")
					&& addAccountNoField.getText().matches("^\\d{1,8}$")
					&& isDouble(addAccBalanceField.getText()) == true && isDouble(addAccDynamicField.getText()) == true
					&& !(Double.parseDouble(addAccDynamicField.getText()) < 1
							|| Double.parseDouble(addAccDynamicField.getText()) > 10)
					&& (addAccPhoneNumField.getText().matches("^\\d{1,10}$")
							&& (addAccPhoneNumField.getText().length() == 10))) {
				addAccountOutput.append("Success");
				addAccount();
				addAccountFirstNameField.setText("");
				addAccountLastNameField.setText("");
				addAccountEmailField.setText("");
				addAccountNoField.setText("");
				addAccBalanceField.setText("");
				addAccDynamicField.setText("");
				addAccMinBalField.setText("");
				addAccPhoneNumField.setText("");


			}

		}
		
	}
	private class ImportDatabaseHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			openInputFile();
			readRecords();
			closeInputFile();
			
			
		}
		
	}
	
	private class PrintAccountHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			pOutput.setText("");
			if (numAccounts ==0 ) {
				pOutput.setText("No Accounts exist");

			}
			else {
				
					/*
					 * Loops through array and prints all user information
					 */
					openOutputFile();
					
					for (int i = 0; i < numAccounts; i++) {
						pOutput.append(accounts.get(i).toString() +"\n");
						//Copied the file to the .txt file
						createFile.format("%s\n", accounts.get(i).toString());
					}
					closeOutputFile();

				
			}
			
		}
		
	}
	
	private class DisplayAccountHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			try {
				if (numAccounts == 0) {
					dOutput.setText("Error: No Account Exist");
				}else {
					for (int i= 0; i< numAccounts; i++) {
						if (accounts.get(i).accountNumber == Double.parseDouble(dAccountNoField.getText())) {
							dOutput.setText(accounts.get(i).toString());
							break;
						}else {
							dOutput.setText("Error account not found");
						}
					}
				}
			}catch(NumberFormatException ex) {
				dOutput.setText("Error: Enter a real number" );
			}
			
			
			}
		}
		
	
	
	/**
	 * Method process the monthly update on the bank account. It will only process
	 * it on the accountNo that the user inputed
	 * 
	 * @see {@link ChequingAccount.monthlyAccountUpdate()} and
	 *      {@link SavingsAccount.monthlyAccountUpdate()}
	 */
	public void monthlyUpdate() {
	
			for (int i = 0; i < accounts.size(); i++) {
			
					accounts.get(i).monthlyAccountUpdate();
				
			}
	
	}
	
	private class RunMonthlyUpdate implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (numAccounts ==0) {
				rOutput.setText("Error: No Accounts Exist");
				
			}else {
			month++;
			rOutput.setText(month + " months of the Monthly Update as been ran");
			monthlyUpdate();
			}
		}
		
	}
	
	public int findAccountIndex() {
		// method will only process if num accounts is not equal to 0

		 

		if (iAccountNoField.getText().matches("^\\d{1,8}$")) {
			
		
				
				// search through all account numbers for a match
				for (int i = 0; i < numAccounts; i++) {
					if (accounts.get(i).accountNumber == Double.parseDouble(iAccountNoField.getText())) {
						// return index
						return accounts.indexOf(accounts.get(i));
					}
				}
			
		
		}
		return -1;
		
	}
	
	private class FindIndexHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (numAccounts ==0) {
				iOutput.setText("Error: Account does not exist" );
			}else {
			int num = findAccountIndex();
			iOutput.setText(String.valueOf(num));
			}
		}
		
	}
	
	// https://stackoverflow.com/questions/3543729/how-to-check-that-a-string-is-parseable-to-a-double
			public boolean isDouble(String num) {
				try {
					Double.parseDouble(num);
					return true;
				} catch (NumberFormatException e) {
					return false;
				}
			}
}