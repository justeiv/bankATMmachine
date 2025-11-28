package com.mycompany.bankatmmachine;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BankATMmachine {

    static Scanner input = new Scanner(System.in);  // create "input" scanner for user input
    static final String ANSI_RESET = "\u001B[0m"; // create variable to reset text colour
    static final String ANSI_RED = "\u001B[31m"; // create variable to change text colour to red
    static final String ANSI_YELLOW = "\u001B[33m"; // create variable to change text colour to yellow
    static final String ANSI_GREEN = "\u001B[32m"; // create variable to change text colour to green
    static ArrayList<Double> debitStatement = new ArrayList<Double>();
    static ArrayList<Double> creditStatement = new ArrayList<Double>();


    public static void main(String[] args) {
        
    String[] usernames = {"Theo", "Mira", "Jasper", "John", "Amy", "Anna",}; // Array for valid username
    String[] passwords = {"pass1", "pass2", "pass3", "pass4", "pass4", "pass5"}; // Array for valid passwords
    double[] balances = {-1200.50, 25070.80, 5060.70, 15000.10, 7050.15, 2970.30}; // Array for balances matching user & pass indexes
    boolean[] overdraft = {false, true, true, false, false, true}; // overdraft facility assigned to users

    int index = LogIn(usernames, passwords); // when login is successful, the index of username as password arrays is assigned to variable "index", this makes finding matching balance, etc easier

    menu(usernames, passwords, overdraft, balances, index); // run menu method after successful login
    }

    /*
    ============================================================================
                              Methods below this line
    ============================================================================
    */
    
    /*
    ============================================================================
                              log in //Juste
    ============================================================================
    */
    static int LogIn(String[] usernames, String[] passwords) { // method to check for successful login
        String name = null; // to compare user input to the usernames array and find the name
        String pass = null; // to compare user input to the password that is assigned to the user
        boolean validLogin = false; // validLogin expected to fail from beginning
        boolean userFound = false; // userFound expected to fail from beginning
        int loggedUserIndex = -1; // starts as -1 to avoid invalid accidental login until it is overwritten after successful login
        do { // while loop to get persons username
            System.out.print("Enter your username: "); // asks user to enter username
            name = input.nextLine(); // input to compare users input to the array
            for (int i = 0; i < usernames.length; i++) { // loop through array to find username
                if (name.equals(usernames[i])) {
                    loggedUserIndex = i; // if user found take the index and save it 
                    userFound = true; // user found 
                    break; // exit loop
                }
            }
            if (!userFound) { // if user not found print message and continue loop
                System.out.println(ANSI_RED+"User not Found Try Again"+ANSI_RESET); // message notifying user username not found ADDED COLOUR
            }
        } while (userFound == false); // do loop while person enters valid username

        int tryMax = 0; // to count the tries and exit when user exceed the 3 tries
        int tryCount = 3; // to count how many times user tries to guess password and print it out to them
        do {
            System.out.print("Enter Password: "); // ask user to enter username
            pass = input.nextLine(); // input for password to compare it to the original password
            if (pass.equals(passwords[loggedUserIndex])) {// if password matches quit the loop
                System.out.println(ANSI_GREEN+"Login was succesful"+ANSI_RESET); // user notified of successful login ADDED COLOUR
                validLogin = true; // validLogin switch to true
                break; // exit loop
            } else {
                tryMax++;//if passwords doesnt match increaase the max count
                tryCount--;// if password doesnt match decrease tries 
                System.out.println(ANSI_RED+"Incorrect Password, try again"+ANSI_RED); // user notified incorrect password ADDED COLOUR
                System.out.println("You have " + tryCount + " tries"); // print out to user ow many tries they have left
                if (tryMax >= 3) { // if user exceeds the try limit program stops
                    System.out.println(ANSI_RED+"You exceeded the maximum allowed tries. Contact the bank "+ANSI_RESET); // user notified of maximum login attempt ADDED COLOUR
                    System.exit(0); // closes entire program after max tries exceeded
                }
            }
        } while (validLogin == false && tryMax < 3); // keep looping while conditions are met
        return loggedUserIndex; // this returns the index of user after successful login
    }

    /*
    ============================================================================
                              Menu //Juste
    ============================================================================
     */
    static void menu(String[] usernames, String[] passwords, boolean[] overdraft, double[] balances, int index) {
        int choice; // create choice variable, used for user input in menu
        boolean exit = false; // assume user doesn't want to exit once ATM starts
        boolean exitDep = false; // assume user doesn't want to exit deposit method from the start of depositOption();
        System.out.println("\nWelcome " + usernames[index]);// welcome user
        do {
            System.out.println("1.Bank Statement"); // view bank statement option
            System.out.println("2.Deposit"); // deposit money option
            System.out.println("3.Withdraw"); // withdraw money option
            System.out.println("4.Change Password"); // change password option
            System.out.println("5.Change Username"); // change username option
            System.out.println("6.Exit"); // exit ATM option
            System.out.print("\nEnter your choice: "); // ask user to enter choice
            choice = input.nextInt(); // assign user input to variable "choice"
            input.nextLine(); // clear user input to avoid conflict
            switch (choice) {
                case 1: //  Francis - Statement Overdraft
                    try {
                        bankStatement(); // run bankStatement method to view recent transactions
                    } catch (Exception e) {
                        System.out.println(ANSI_RED + "There was an unexpected issue. If the issue persists, please contact the bank for support" + ANSI_RESET);
                    }
                    System.out.println(ANSI_YELLOW + "Press enter to continue" + ANSI_RESET); // notify user to press enter to continue
                    input.nextLine(); // clear user input to avoid conflict
                    break; // return to start of menu
                    
                case 2: // Francis - deposit
                    deposit(balances, index); // run deposit method
                    break; // return to start of menu
                        
                case 3: // Francis - withdraw
                    if (overdraft[index] == false && balances[index] < 0) {
                        System.out.println(ANSI_RED + "Your balance is in overdraft. Please ammend to withdraw or contact the bank for support" + ANSI_RESET);
                        break;
                    } else {
                        withdraw(balances, overdraft, index); // run withdraw method
                        break; // return to start of menu
                    }
                case 4: // Juste - change password
                    changePass(usernames, passwords, index);
                    break;

                case 5: // Juste - change username
                    changeUsername(usernames, passwords, index);
                    break;

                case 6: // Juste - Exit ATM
                    System.out.println("Thank you for using our ATM"); // thank user for using ATM
                    exit = true; // change exit to true
                    break;

                default: // if user input doesn't equal 1-6, print invalid input |||||||| IF USER INPUT IS STRING THE PROGRAM CRASHES. FIX PLZZZZZZZZZZZZ-------------------------------------------------------------------
                    System.out.println(ANSI_RED+"Invalid input"+ANSI_RESET);//PRINT OUT THAT INPUT WAS NOT VALID + COL0UR
            }
        } while (exit == false); // continue looping until exit = true
    }

    /*
    ============================================================================
                              Change  password//Juste
    ============================================================================
     */
    static void changePass(String[] usernames, String[] passwords, int index) { // method to change password
        String currPassword = null; // start current password as null to avoid conflicts
        String newPassword = null; // start new password as null to avoid conflicts
        boolean currPassMatch = false; // assume password starts as incorrect
        boolean newPassMatch = false; // assume new passwords don't match from beginning

        do {
            System.out.print("Enter Your Current Password: "); // ask user to enter current password
            currPassword = input.nextLine(); // assign currPassword to user input
            if (currPassword.equals(passwords[index])) { // checking if currPassword matches existing password
                currPassMatch = true; // change boolean to true if password is correct to exit loop
                System.out.println(ANSI_GREEN+"Pass is correct"+ANSI_RESET); // display that the password is correct ADDED COLOUR
                break; // exit loop
            } else {
                System.out.println(ANSI_RED+"Incorrect Password. Try again"+ANSI_RESET); // if password is incorrect display incorrect password try again and continue the loop ADDED COLOUR
            }
        } while (currPassMatch == false); // do block of code until password is correct and boolean is changed to true

        while (newPassMatch == false) { // loop for new password o this until person enter valid password and boolean is changed to true
            System.out.println("Enter new password"); // display to user enter new password
            newPassword = input.nextLine(); // assign user input to newPassword
            if (newPassword.equals(passwords[index])) { // compare password to the old one
                System.out.println(ANSI_RED+"Passwords cannot be same as the old one"+ANSI_RESET); //ADDED COLOUR if password is the same as the old one, display to user password can't be the same as odl one and try again

            } else if (!newPassword.equals(passwords[index])) { // if password doesn't match old password, replace old password with new one in array
                passwords[index] = newPassword; // boolean changed to tre to exit loop
                newPassMatch = true; // display user the the login was successful
                return;
            }
        }
    }

    /*
    ============================================================================
                             Change Username// Juste
    ============================================================================
     */
    static void changeUsername(String[] usernames, String[] passwords, int index) {//method for chaging username
        String currentName = null;//so user could confirm their current username      
        String newName = null;// for person to input new name

        boolean validChange = false;//to exit loop  when user enter valid name
        boolean validName = false;//to exit loop when user types in valid name

        while (validName == false) {//loop to confirm current username

            System.out.println("Enter your current Username");// ask user to enter current username
            currentName = input.nextLine();//input line for user to enter current name
            if (currentName.equals(usernames[index])) {// checking if person entered valid  username
                System.out.println(ANSI_GREEN+"Username confirmed"+ANSI_RESET);//ADDED COLOUR if username is valid display message username confirmed
                validName = true; // changfin boolean value to true when found
                break;//exit loop
            } else {
                System.out.println(ANSI_RED+"Invalid Username. Try again"+ANSI_RESET);//ADDED COLOUR if username invalid display invalid username try again
            }
        }

        while (validChange == false) {//loop to change username
            System.out.println("Enter new username");// display to user enter new username
            newName = input.nextLine();//input line to enter new username
            if (newName.equals(currentName)) {//CHECK IF NAME  EQUALS CURRENT NAME
                System.out.println(ANSI_RED+"Username cannot be the same as the old one"+ANSI_RESET);//ADDED COLOUR IF NAME IS SAME AS OLD ONE DISPLAY MESSAGE USERNAME CANNOT BE SAME AS OLD ONE 
                continue;
            }
            if (newName.length() > 20) {// CHECK NAME LENGHT 
                System.out.println(ANSI_RED+"Name cannot have more than 20 characters"+ANSI_RESET);//ADDED COLOUR IF NAME IS MORE THAN 20 CHAR  DISPLAY MESSAGE THAT IT CANNOT BE MORE THAN 20 CHAR 

            } else {
                usernames[index] = newName;// IF NAME IS VALID OVERWRITE CURRENT USERNAME WITH NEW USERNAME
                validChange = true;// CHANGE  VALID CHANGE TO TRUE TO EXIT LOOP
                System.out.println(ANSI_GREEN+"Username was changed succesfully"+ANSI_RESET);// DISPLAY MESSAGE THAT NAME WAS CHANGED  SUCCESFULY
                System.out.println("Your new Username is : " + usernames[index]);// DISPLAY NEW USERNAME
                return;
            }
        }

    }


    /*
    ============================================================================
                              Deposit // Francis
    ============================================================================
     */
    
    static void deposit(double[] balances, int index) { // method giving user the ability to credit their account
        
        try { // try catch if user input is not valid, print error message and kick user back to menu
            double depositAmount; // create deposit variable

            System.out.println("\nYour current balance is: " + balances[index]); // print current user balance

            System.out.print("Enter deposit amount: "); // Ask user to enter how much to deposit
            depositAmount = input.nextDouble(); // assign user input to depositAmount
            creditStatement.add(depositAmount); // add depositAmount to creditStatement array list

            input.nextLine(); // clear user input to avoid conflicts

            balances[index] += depositAmount; // add depositAmount to user balance and overwrite balance

            System.out.println("\nUpdated Balance: " + balances[index] +"\n"); // print new user balance
            
            if (balances[index] < 0) { // if balance is less than 0, print notification for user
                System.out.println(ANSI_RED + "There is an overdraft on your account. For payment support, contact the bank" + ANSI_GREEN); // if user balance is < 0, print notification in red
            }
        } catch (InputMismatchException e) { // if user input is not numeric, print the following
            System.out.println(ANSI_RED + "Something went wrong. Please contact the bank for support" + ANSI_RESET);
        }
    }

    /*
    ============================================================================
                              Withdraw // Francis
    ============================================================================
    */

    static void withdraw(double[] balances, boolean[] overdraft, int index) { // method giving user the ability to debit their account

        try { // try catch if user input is not valid, print error message and kick user back to menu
            double withdrawAmount; // create withdraw variable

            System.out.println("\nYour current balance is: " + balances[index]); // print current user balance

            System.out.print("Enter withdrawal amount: "); // Ask user to enter how much to deposit
            withdrawAmount = input.nextDouble(); // assign userinput to depositAmount
            debitStatement.add(withdrawAmount); // add withdrawAmount to debitStatement array list

//            input.nextLine(); // clear user input to avoid conflict

            if(withdrawAmount > balances[index] && overdraft[index] == false) { // if user doesn't have overdraft perms and tries withdrawing more than their balance, print below notification
                System.out.println(ANSI_RED + "\nInsufficient funds\n" + ANSI_RESET); // user notified of insufficient funds
                return; // return to menu
            } else {
                balances[index] -= withdrawAmount; // add withdrawAmount to user balance and overwrite balance
            }
            System.out.println("\nUpdated Balance: " + balances[index] + "\n"); // print new user balance

            if (balances[index] < 0) { // check if the user balance is less than 0
                System.out.println(ANSI_RED + "There is an overdraft on your account. For payment support, contact the bank" + ANSI_RESET); // if user balance is < 0, print notification in red
            }
        } catch (InputMismatchException e) { // if user input is not numeric, print the following
            System.out.println(ANSI_RED + "Something went wrong. If the issue persists, please contact the bank for support" + ANSI_RESET);
        }
    }
    // I FORGOT TO ADD LINES TO CHECK IF WITHDRAW IS GREATER THAN BALANCE & NO OVERDRAFT PERMS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    

    /*
    ============================================================================
                              Bank Statement // Francis
    ============================================================================
    */
    static void bankStatement() {
        System.out.println("\n ===================================================");
        System.out.println(" -=== Please see your recent transactions below ===-");

        // Credit Section
        System.out.println("\n\t\t -==Credit==-");
        if (creditStatement.isEmpty()) {
            System.out.println("\tNo recent credit transactions");
        } else {
            for (int i = 0; i < creditStatement.size(); i++) {
                System.out.println("Transaction " + (i + 1) + ": " + ANSI_GREEN + creditStatement.get(i) + ANSI_RESET);
            }
        }

        // Debit Section
        System.out.println("\n\t\t -==Debit==-");
        if (debitStatement.isEmpty()) {
            System.out.println("\tNo recent debit transactions");
        } else {
            for (int i = 0; i < debitStatement.size(); i++) {
                System.out.println("Transaction " + (i + 1) + ": -" + ANSI_RED + debitStatement.get(i) + ANSI_RESET);
            }
        }

        System.out.println("\n===================================================");
    }

/*	**create  6 users  each user needs to have name password balance** overdraft(*overdraft for  3 users only* * no limit for overdraft*)
	do method for login that would return the position of the name in array(no more than 3 attempts to log in**
	** display menu with options (view bank statement, change password**, change name deposit money, withdraw money, exit. loop until user  chooses to exit**
	array must be updated  when user deposits or withdraws money
	users can only withraw more money than they have in their bank account if they have overdraft facility
( cannot be the password that was used before)
( cannot be more than 20 characters)


 */
}