package com.mycompany.bankatmmachine;
import java.util.Scanner;
public class BankATMmachine {
static Scanner input = new Scanner(System.in);  
public static void main(String[] args) {
    String[] usernames ={"Theo","Mira","Jasper","John","Amy","Anna",};
    String[] passwords={"pass1","pass2","pass3","pass4","pass4","pass5"};
    double[] balances={1200.50, 25070.80, 5060.70, 15000.10, 7050.15, 2970.30};
       int index = LogIn(usernames, passwords);
      menu( usernames, passwords, balances,index);
    }
    static int LogIn(String[]usernames,String[]passwords){   
         String name=null;// to compare user input to the usernames array and find the name
         String pass= null;// to compare user input to the password that is assigned to the user
        boolean validLogin = false;
        boolean userFound = false;
        int loggedUserIndex=-1;  
         do{//while loop to get persons username
        System.out.println("Enter your username");
        name=input.nextLine();//input to compare users input to the array
        for(int i = 0 ; i< usernames.length;i++){//loop through array to find username
           if(name.equals(usernames[i])){
               loggedUserIndex=i;//if user found take the index and save it 
               userFound=true;//user found 
               break;//exit loop
           }
       }
        if(!userFound){//if user not found print message and continue loop
        System.out.println("User not Found Try Again");
        }
        }while(userFound==false);//do loop while person enters valid username
        
         
         
         int tryMax=0;//to count the tries and exit when user exceed the 3 tries
         int tryCount=3;//to count how many times user tries to guess password and print it out to them
         do{
         System.out.println("Enter Password");
         pass=input.nextLine();//input for password to compare it to the original password
         if(pass.equals(passwords[loggedUserIndex])){// if password matches quit the loop
             System.out.println("Login was succesful");
         validLogin=true;
         break;
         }else{
             tryMax++;//iff passwords doesnt match increaase the max count
             tryCount--;// if password doesnt match decrease tries 
             System.out.println("Incorrect Password, try again");
             System.out.println("You have "+tryCount+" tries");//print out to user ow many tries they have left
             if(tryMax>=3){//if user exceeds the try limit program stops
                 System.out.println("You exceeded the maximum allowed tries. Contact the bank ");
                 System.exit(0);
             }
         }
         }while(validLogin==false&&tryMax<3);
       return  loggedUserIndex;  
    }
    
    static void menu( String[]usernames, String[] passwords, double[] balances,int index){
        int choice;
        boolean exit =false;
        do{
        System.out.println("Welcome "+usernames[index]);
        System.out.println("Enter your choice");
        System.out.println("1.Bank Statement");
        System.out.println("2.Deposit");
        System.out.println("3.Withdraw");
        System.out.println("4.Change Password");
        System.out.println("5.Change Username");
        System.out.println("6.Exit");
        choice=input.nextInt();
        switch(choice){
            case 1:///bank statement
                
            case 2:/// deposit
                
            case 3://withdraw
                
            case 4://change pw
                
            case 5:// change username
                
            case 6: 
                System.out.println("Thank you for using our ATM:");
                        exit=true; 
                        break;
            
            default: System.out.println("Invalid input");    
        }
        }while(exit==false);
    }
    
    static void password(String[]usernames,String[]passwords, int index){
        String currPassword = null;
        String newPasswords= null;
        boolean currpass =false;
        do{
        System.out.println("Enter Your Current Password");
        currPassword=input.nextLine();
        if(currPassword.equals(passwords[index])){
         currpass=true;
            System.out.println("");
        }
        }while(currpass==false);
    }



}
    



/*	**create  6 users  each user needs to have name password balance** overdraft(*overdraft for  3 users only* * no limit for overdraft*)
	do method for login that would return the position of the name in array(no more than 3 attempts to log in**
	** display menu with options (view bank statement, change password**, change name deposit money, withdraw money, exit. loop until user  chooses to exit**
	array must be updated  when user deposits or withdraws money
	users can only withraw more money than they have in their bank account if they have overdraft facility
( cannot be the password that was used before)
( cannot be more than 20 characters)

// Francis  deposit , withdraw  , statement overdraft
//Juste *login, *menu, change password,  change username
*/