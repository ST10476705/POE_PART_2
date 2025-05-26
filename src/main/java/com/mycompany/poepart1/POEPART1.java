/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.poepart1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 *
 * @author RC_Student_lab
 */


public class POEPART1 {
     private static final List<Message> messages = new ArrayList<>();
    private static int totalMessagesAllowed = 0;
    private static int messagesSent = 0;
    private static final Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) throws IOException {
        
        //Creating an instance to create a class
        Login objLogin = new Login ();
        Message objMessage = new Message(); 
        
        
        //Creating a scanner class
        Scanner scan = new Scanner(System.in);
        
        // Promt the user to enter username
        System.out.println("please enter username");
        //user enters username
        String username=scan.nextLine();
        // using if statements to check the username
        if(objLogin.CheckUserName(username)== true){
            System.out.println("Thank you for username");
        }else{
            // Display the results
            System.out.println("the username is incorrectly formated");
        }
        // prompt the user to enter password
        System.out.println("please enter the password");
        // user enters a password
        String password=scan.nextLine();
        // using if statements to check password
        if(objLogin.checkpasswordComplexity(password)==true){
            System.out.println("Thank you for password");
        }else{
            System.out.println("password is incorrectly formated");
    
        }
        //prompt the user to enter cellphone number
        System.out.println("please enter a cellphone number");
        //user enters a cellphone number
        String CellPhoneNumber=scan.nextLine();
        
        //using if statements to checkCellphoneNumber
        if(objLogin.checkCellPhoneNumber(CellPhoneNumber)==true){
           System.out.println("thank you for the cellphone number");
        }else{
            System.out.println("the cellphone number is incorrectly formated");
        }
        
        
        //Additional information
        System.out.println("Enter your first name: ");
        String firstName = scan.nextLine();
        System.out.println("Enter your last name: ");
        String lastName = scan.nextLine();
        
        boolean loggedIn = false;
        int attempts = 0;
        final int MAX_ATTEMPTS = 3;
        
        
        //LOGIN SECTION
        while(!loggedIn && attempts < MAX_ATTEMPTS){
            System.out.println("=============LOGIN=============");
       objLogin.registerUser(username,password);
       System.out.println("please enter the username you signed up with");
       String verifyusername=scan.nextLine();
       
       System.out.println("please enter the password you signed up with");
       String verifyPassword=scan.nextLine();
       
       if (objLogin.loginUser(username, password, verifyusername, verifyPassword)){
           System.out.println("Login Succesfull " + firstName + " " + lastName);
           loggedIn = true;
           objLogin.returnloginstatus(username, password, verifyusername, verifyPassword);
       } else {
           attempts++;
           int remainingAttempts = MAX_ATTEMPTS - attempts;
           if(remainingAttempts > 0){
           System.out.println("Login Unsuccessful");
 
       }
       }
        }

        //POE_PART_2
         System.out.println("Welcome to QuickChat.");
        runMenu();
    }

    private static void runMenu() throws IOException {
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.print("Enter your choice: ");
            
            String input = scanner.nextLine();
            
            if (input == null || input.equals("3")) {
                System.out.println("Goodbye!");
                System.exit(0);
            }

            switch (input) {
                case "1" -> {
                    if (totalMessagesAllowed == 0) {
                        System.out.print("How many messages do you want to send? ");
                        String numMsg = scanner.nextLine();
                        try {
                            totalMessagesAllowed = Integer.parseInt(numMsg);
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter a valid number.");
                            continue;
                        }
                    }
                    sendMessages();
                }
                case "2" -> showRecentMessages();
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void sendMessages() throws IOException {
        while (messagesSent < totalMessagesAllowed) {
            Message message = new Message();
            
            System.out.print("\nEnter recipient's cell number (10 digits with international code): ");
            String recipient = scanner.nextLine();
            while (!message.checkRecipientCell(recipient)) {
                System.out.println("Invalid cell number. Must be exactly 10 digits.");
                System.out.print("Enter recipient's cell number: ");
                recipient = scanner.nextLine();
            }
            message.setRecipient(recipient);
            
            System.out.print("Enter your message (max 250 chars): ");
            String content = scanner.nextLine();
            while (content != null && content.length() > 250) {
                System.out.println("Message too long. Maximum 250 characters.");
                System.out.print("Enter your message: ");
                content = scanner.nextLine();
            }
            if (content == null) continue;
            
            message.setMessage(content);
            message.generateMessageId();
            message.createMessageHash();
            
            String action = message.sentMessage();
            switch (action) {
                case "Send Message" -> {
                    messages.add(message);
                    messagesSent++;
                    System.out.println("\n" + message.printMessage());
                    System.out.println("Message sent successfully!");
                }
                case "Store Message to send later" -> {
                    message.storeMessage();
                    System.out.println("Message stored for later.");
                }
                default -> System.out.println("Message discarded.");
            }
        }
        
        System.out.println("\nAll messages sent. Total messages: " + messages.size());
        totalMessagesAllowed = 0;
        messagesSent = 0;
    }

    private static void showRecentMessages() {
        if (messages.isEmpty()) {
            System.out.println("No messages sent yet.");
            return;
        }
        
        System.out.println("\nRecently Sent Messages:");
        System.out.println("-----------------------");
        for (Message msg : messages) {
            System.out.println(msg.printMessage());
            System.out.println("-----------------------");
        }
    }
}
    

 


