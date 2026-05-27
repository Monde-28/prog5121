
package com.mycompany.assignment;

import java.util.Scanner;

public class login {


    Scanner scan = new Scanner(System.in);
    registration register;

    // constructor takes an existing registration object
    public login(registration register) {
        this.register = register;
    }
    public void logged() {

        String username;
        String password;
            System.out.println("lets log in :");

        

        do {
            
            System.out.println("enter your username");
            username = scan.nextLine();
            
                    System.out.println("enter your password");
                    password = scan.nextLine();
        } while (!validateInfo(username,password));
        message log = new message();
         log.startMessaging();
    }
    
    public boolean validateInfo(String username,String password) {
        if (username.equals(register.RegUsername) && password.equals(register.RegPassword)) {
            System.out.println("Welcome " + register.name + " " + register.surname + " it is great to see you again.");
            return true;
        } else {
            System.out.println("incoorect details");
            return false;
        }
    }


}
