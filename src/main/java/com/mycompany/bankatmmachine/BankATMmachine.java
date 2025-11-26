package com.mycompany.bankatmmachine;

import java.util.Scanner;

public class BankATMmachine {

    static Scanner input = new Scanner(System.in);  // create "input" scanner for user input

    public static void main(String[] args) {
        String[] usernames = {"Theo", "Mira", "Jasper", "John", "Amy", "Anna",}; // Array for valid username
        String[] passwords = {"pass1", "pass2", "pass3", "pass4", "pass4", "pass5"}; // Array for valid passwords
        double[] balances = {1200.50, 25070.80, 5060.70, 15000.10, 7050.15, 2970.30}; // Array for balances matching user & pass indexes
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
        System.out.println("Welcome " + usernames[index]);// welcome user
        do {
            System.out.println("1.Bank Statement"); // view bank statement option
            System.out.println("2.Deposit"); // deposit money option
            System.out.println("3.Withdraw"); // withdraw money option
            System.out.println("4.Change Password"); // change password option
            System.out.println("5.Change Username"); // change username option
            System.out.println("6.Exit"); // exit ATM option
            System.out.print("Enter your choice: "); // ask user to enter choice
            choice = input.nextInt(); // assign user input to variable "choice"
            input.nextLine(); // clear user input to avoid conflict
            switch (choice) {
                case 1: //  Francis - Statement Overdraft

                case 2: // Francis - deposit
                    deposit(input, balances, overdraft, index);
                    break;
                case 3: // Francis - withdraw

                case 4: // Juste - change password
                    changePass(usernames, passwords, index);
                    break;

                case 5: // Juste - change username
                    changeUsername(usernames, passwords, index);
                    break;

                case 6: // Juste - Exit ATM
                    System.out.println("Thank you for using our ATM:"); // thank user for using ATM
                    exit = true; // change exit to true

                default: // if user input doesn't equal 1-6, print invalid input
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
        boolean newPassMatch = false;

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
                System.out.println(ANSI_GREN+"Username was changed succesfully"+ANSI_RESET);// DISPLAY MESSAGE THAT NAME WAS CHANGED  SUCCESFULY
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
    static void deposit(Scanner input, double[] balances, boolean[] overdraft, int index) {

        double depositAmount;
        boolean exitDep = false;

        System.out.println("Your current balance is: " + balances[index]); // print current user balance

        System.out.print("Enter deposit amount: "); // Ask user to enter how much to deposit
        depositAmount = input.nextDouble(); // assign userinput to depositAmount
        input.nextLine(); // clear user input to avoid conflict

        balances[index] += depositAmount; // add depositAmount to user balance and overwrite balance

        System.out.println("Balance: " + balances[index]); // print new user balance

    }
    /*
    ============================================================================
                              Withdraw // Francis
    ============================================================================
     */

 /*
    ============================================================================
                              Bank Statement // Francis
    ============================================================================
     */
}

/*	**create  6 users  each user needs to have name password balance** overdraft(*overdraft for  3 users only* * no limit for overdraft*)
	do method for login that would return the position of the name in array(no more than 3 attempts to log in**
	** display menu with options (view bank statement, change password**, change name deposit money, withdraw money, exit. loop until user  chooses to exit**
	array must be updated  when user deposits or withdraws money
	users can only withraw more money than they have in their bank account if they have overdraft facility
( cannot be the password that was used before)
( cannot be more than 20 characters)
 */
