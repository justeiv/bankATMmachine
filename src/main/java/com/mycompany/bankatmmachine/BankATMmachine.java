package com.mycompany.bankatmmachine;

import java.util.Scanner;

public class BankATMmachine {

    static Scanner input = new Scanner(System.in);  // create "input" scanner for user input

    public static void main(String[] args) {
        String[] usernames = {"Theo", "Mira", "Jasper", "John", "Amy", "Anna",}; // Array for valid username
        String[] passwords = {"pass1", "pass2", "pass3", "pass4", "pass4", "pass5"}; // Array for valid passwords
        double[] balances = {1200.50, 25070.80, 5060.70, 15000.10, 7050.15, 2970.30}; // Array for balances matching user & pass indexes
        boolean[] overdraft = {false, false, false, false, false}; // all users start with false overdraft
        
        int index = LogIn(usernames, passwords); // when login is successful, the index of username as password arrays is assigned to variable "index", this makes finding matching balance, etc easier
        
        menu(usernames, passwords, balances, index); // run menu method after successful login
    }

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
                System.out.println("User not Found Try Again"); // message notifying user username not found
            }
        } while (userFound == false); // do loop while person enters valid username

        int tryMax = 0; // to count the tries and exit when user exceed the 3 tries
        int tryCount = 3; // to count how many times user tries to guess password and print it out to them
        do {
            System.out.println("Enter Password: "); // ask user to enter username
            pass = input.nextLine(); // input for password to compare it to the original password
            if (pass.equals(passwords[loggedUserIndex])) {// if password matches quit the loop
                System.out.println("Login was succesful"); // user notified of successful login
                validLogin = true; // validLogin switch to true
                break; // exit loop
            } else {
                tryMax++;//if passwords doesnt match increaase the max count
                tryCount--;// if password doesnt match decrease tries 
                System.out.println("Incorrect Password, try again"); // user notified incorrect password
                System.out.println("You have " + tryCount + " tries"); // print out to user ow many tries they have left
                if (tryMax >= 3) { // if user exceeds the try limit program stops
                    System.out.println("You exceeded the maximum allowed tries. Contact the bank "); // user notified of maximum login attempt
                    System.exit(0); // closes entire program after max tries exceeded
                }
            }
        } while (validLogin == false && tryMax < 3); // keep looping while conditions are met
        return loggedUserIndex; // this returns the index of user after successful login
    }

    static void menu(String[] usernames, String[] passwords, double[] balances, int index) {
        int choice;
        boolean exit = false;
        do {
            System.out.println("Welcome " + usernames[index]);
            System.out.println("Enter your choice");
            System.out.println("1.Bank Statement");
            System.out.println("2.Deposit");
            System.out.println("3.Withdraw");
            System.out.println("4.Change Password");
            System.out.println("5.Change Username");
            System.out.println("6.Exit");
            choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1: //  Francis - Statement Overdraft
                
                case 2: // Francis - deposit
                
                case 3: // Francis - withdraw

                case 4: // Juste - change password
                    changePass(usernames,passwords,index);

                case 5: // Juste - change username

                case 6: // Juste - Exit ATM
                    System.out.println("Thank you for using our ATM:"); // thank user for using ATM
                    exit = true; // change exit to true
                    
                default: // if user input doesn't equal 1-6, print invalid input
                    System.out.println("Invalid input");
            }
        } while (exit == false); // continue looping until exit = true
    }

    static void changePass(String[] usernames, String[] passwords, int index) { // method to change password
        String currPassword = null; // start current password as null to avoid conflicts
        String newPassword = null; // start new password as null to avoid conflicts
        boolean currPassMatch = false; // assume password starts as incorrect
        boolean newPassMatch = false;
        do {
            System.out.print("Enter Your Current Password: "); // ask user to enter current password
            currPassword = input.nextLine(); // assign currPassword to user input
            if (currPassword.equals(passwords[index])) { // checking if currPassword matches existing password
                currPassMatch = true; // change booleanto true if password is correct to exit loop
                System.out.println("Pass is correct"); // display that the password is correct
                break; // exit loop
            }else{
                System.out.println("Incorrect Password. Try again"); // if password is incorrect display incorrect password try again and continue the loop
            }
        } while (currPassMatch == false); // do block of code until password is correct and boolean is changed to true
        while(newPassMatch == false){ // loop for new password o this until person enter valid password and boolean is changed to true
            System.out.println("Enter new password"); // display to user enter new password
            newPassword=input.nextLine(); // assign user input to newPassword
            if(newPassword==passwords[index]){ // compare password to the old one
                System.out.println("Passwords cannot be same as the old one"); // if password is the same as the old one, display to user password can't be the same as odl one and try again
                continue; // start loop again
            }if(newPassword!=passwords[index]){ // if password doesn't match old password, replace old password with new one in array
                passwords[index]=newPassword; // boolean changed to tre to exit loop
                newPassMatch = true; // display user the the login was successful
                
            }
        }
    }



}
    



/*	**create  6 users  each user needs to have name password balance** overdraft(*overdraft for  3 users only* * no limit for overdraft*)
	do method for login that would return the position of the name in array(no more than 3 attempts to log in**
	** display menu with options (view bank statement, change password**, change name deposit money, withdraw money, exit. loop until user  chooses to exit**
	array must be updated  when user deposits or withdraws money
	users can only withraw more money than they have in their bank account if they have overdraft facility
( cannot be the password that was used before)
( cannot be more than 20 characters)
*/
