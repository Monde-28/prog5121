
package com.mycompany.assignment;
import java.util.Scanner;

public class registration {
    
    Scanner usrInput = new Scanner(System.in);
     String RegUsername;
     String name;
     String surname;
     String RegPassword;
     String cellphoneNum;
    public void collectInfo() {
        

        //prompt user for name 
        System.out.println("Please enter your name");
         name = usrInput.nextLine();

        //prompt user for surname 
        System.out.println("Please enter your surname");
         surname = usrInput.nextLine();

        //prompt user for username (cotains an underscore and is less than 5 characters long )
                do {
                    System.out.println("Please enter your user name");
                     RegUsername = usrInput.nextLine();
                 } while (!checkUsername(RegUsername));
        
        //prompt user for password( the password must be:
        // • At least eight characters long.
        // • Contain a capital letter.
        // • Contain a number.
        // • Contain a special character.)
        do {
            
            System.out.println("Please enter a password");
            RegPassword = usrInput.nextLine();
        } while (!checkPassword(RegPassword));

        //prompt user for cellphone number
        do {
            
            System.out.println("Please enter your phone number");
             cellphoneNum = usrInput.nextLine();
         } while (!checkCellNumber(cellphoneNum));
        
         login log = new login(this);
         log.logged();
    }
    
    public Boolean checkUsername(String RegUsername) {
        // Check if username contains an underscore and is less than 5 characters long
        if (RegUsername.contains("_") && RegUsername.length() <= 5) {
            System.out.println("Username successfully captured.");
            return true;
        } else {
            System.out.println("username is in correctly formatted ");
            return false;
        }
    }
    
    public boolean checkPassword(String RegPassword) {
        // Check if password is at least 8 characters long
        boolean hasMinLength = RegPassword.length() >= 8;

        // Check if it contains at least one uppercase letter
        boolean hasUppercase = RegPassword.matches(".*[A-Z].*");
        /*
         explaining how regex for uppercase
         .* -any number of characters before and after
         [A-Z] - matches any uppercase letter (A to Z)
         */

        // Check if it contains at least one digit
        boolean hasDigit = RegPassword.matches(".*\\d.*");
        /*
        explaining how regex for digit
        \\d - means a digit (0 to 9)
        */

        // Check if it contains at least one special character
        boolean hasSpecialChar = RegPassword.matches(".*[!@#$%^&*()].*");

        //check the password meets all the tests
        if (hasMinLength && hasUppercase && hasDigit && hasSpecialChar) {
            System.out.println("Password successfully captured.");
            return true;
        } else {
            System.out.println("Password is not correctly formatted.");
            return false;
        }
    }

    public Boolean checkCellNumber(String cellphoneNum) {
        boolean hasCountryCode = cellphoneNum.contains("+27");

        boolean hasDigit = cellphoneNum.matches(".*\\d{9}.*");


    if (hasCountryCode  && hasDigit) {
            System.out.println("cell number successfully captured.");

    return true;
} else {
            System.out.println("cellphone is not correctly formatted.");

    return false;
}
}

}

