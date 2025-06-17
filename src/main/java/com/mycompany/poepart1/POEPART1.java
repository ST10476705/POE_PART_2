/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.poepart1;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author RC_Student_lab
 */


public class POEPART1 {
    private static final List<Message> messages = new ArrayList<>();
    private static final List<Message> sentMessages = new ArrayList<>();
    private static final List<Message> storedMessages = new ArrayList<>();
    private static final List<String> messageHashes = new ArrayList<>();
    private static final List<String> messageIDs = new ArrayList<>();
    private static int totalMessagesAllowed = 0;
    private static int messagesSent = 0;


    public static void main(String[] args)  throws IOException {
      // Creating instances
        Login objLogin = new Login();
        Message objMessage = new Message();

        // Registration Section
        String username = JOptionPane.showInputDialog("Please enter username");
        if (objLogin.CheckUserName(username)) {
            JOptionPane.showMessageDialog(null, "Thank you for username");
        } else {
            JOptionPane.showMessageDialog(null, "The username is incorrectly formatted");
        }

        String password = JOptionPane.showInputDialog("Please enter the password");
        if (objLogin.checkpasswordComplexity(password)) {
            JOptionPane.showMessageDialog(null, "Thank you for password");
        } else {
            JOptionPane.showMessageDialog(null, "Password is incorrectly formatted");
        }

        String CellPhoneNumber = JOptionPane.showInputDialog("Please enter a cellphone number");
        if (objLogin.checkCellPhoneNumber(CellPhoneNumber)) {
            JOptionPane.showMessageDialog(null, "Thank you for the cellphone number");
        } else {
            JOptionPane.showMessageDialog(null, "The cellphone number is incorrectly formatted");
        }

        // Additional information
        String firstName = JOptionPane.showInputDialog("Enter your first name:");
        String lastName = JOptionPane.showInputDialog("Enter your last name:");

        // Login Section
        boolean loggedIn = false;
        int attempts = 0;
        final int MAX_ATTEMPTS = 3;

        while (!loggedIn && attempts < MAX_ATTEMPTS) {
            JOptionPane.showMessageDialog(null, "=============LOGIN=============");
            objLogin.registerUser(username, password);
            String verifyusername = JOptionPane.showInputDialog("Please enter the username you signed up with");
            String verifyPassword = JOptionPane.showInputDialog("Please enter the password you signed up with");

            if (objLogin.loginUser(username, password, verifyusername, verifyPassword)) {
                JOptionPane.showMessageDialog(null, "Login Successful " + firstName + " " + lastName);
                loggedIn = true;
                objLogin.returnloginstatus(username, password, verifyusername, verifyPassword);
            } else {
                attempts++;
                int remainingAttempts = MAX_ATTEMPTS - attempts;
                if (remainingAttempts > 0) {
                    JOptionPane.showMessageDialog(null, "Login Unsuccessful. " + remainingAttempts + " attempts remaining.");
                }
            }
        }

        if (!loggedIn) {
            JOptionPane.showMessageDialog(null, "Too many failed attempts. Exiting.");
            System.exit(0);
        }

        // Load any stored messages
        loadStoredMessages();
        JOptionPane.showMessageDialog(null, "Welcome to QuickChat.");
        mainMenu();
    }

    private static void loadStoredMessages() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("stored_messages.txt"));
            for (String line : lines) {
                if (!line.trim().isEmpty()) {
                    String[] parts = line.split("\\|");
                    if (parts.length == 4) {
                        Message msg = new Message();
                        msg.setId(parts[0]);
                        msg.setRecipient(parts[1]);
                        msg.setContent(parts[2]);
                        msg.setHash(parts[3]);
                        storedMessages.add(msg);
                    }
                }
            }
        } catch (IOException e) {
            // No existing file is okay
        }
    }

    private static void saveStoredMessages() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("stored_messages.txt"))) {
            for (Message msg : storedMessages) {
                writer.println(msg.getId() + "|" +
                        msg.getRecipient() + "|" +
                        msg.getContent() + "|" +
                        msg.getHash());
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving stored messages: " + e.getMessage());
        }
    }

    private static void mainMenu() {
        while (true) {
            String[] options = {"Simple Messaging", "Advanced Messaging", "Exit"};
            int choice = JOptionPane.showOptionDialog(null,
                    "Main Menu - Select Mode",
                    "QuickChat",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    options,
                    options[0]);

            switch (choice) {
                case 0:
                    simpleMessagingMenu();
                    break;
                case 1:
                    advancedMessagingMenu();
                    break;
                case 2:
                    saveStoredMessages();
                    JOptionPane.showMessageDialog(null, "Thank you for using QuickChat!");
                    System.exit(0);
                default:
                    break;
            }
        }
    }

    private static void simpleMessagingMenu() {
        while (true) {
            String[] options = {"Send Messages", "Show recently sent messages", "Back to Main Menu"};
            int choice = JOptionPane.showOptionDialog(null,
                    "Simple Messaging",
                    "QuickChat",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    options,
                    options[0]);

            switch (choice) {
                case 0 -> {
                    if (totalMessagesAllowed == 0) {
                        String numMsg = JOptionPane.showInputDialog("How many messages do you want to send?");
                        try {
                            totalMessagesAllowed = Integer.parseInt(numMsg);
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Please enter a valid number.");
                            continue;
                        }
                    }
                    simpleSendMessages();
                }
                case 1 -> showRecentMessages();
                case 2 -> {
                    return;
                }
                default -> {
                }
            }
        }
    }

    private static void simpleSendMessages() {
        while (messagesSent < totalMessagesAllowed) {
            Message message = new Message();

            String recipient = JOptionPane.showInputDialog("Enter recipient's cell number (10 digits):");
            while (!message.validateRecipient(recipient)) {
                recipient = JOptionPane.showInputDialog("Invalid cell number. Must be exactly 10 digits.\nEnter recipient's cell number:");
            }
            message.setRecipient(recipient);

            String content = JOptionPane.showInputDialog("Enter your message (max 250 chars):");
            while (content != null && content.length() > 250) {
                content = JOptionPane.showInputDialog("Message too long. Maximum 250 characters.\nEnter your message:");
            }
            if (content == null) continue;

            message.setContent(content);
            message.generateMetadata();

            String[] actions = {"Send Message", "Store Message to send later"};
            int action = JOptionPane.showOptionDialog(null,
                    "What would you like to do with this message?",
                    "Message Options",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    actions,
                    actions[0]);

            if (action == 0) {
                sentMessages.add(message);
                messageIDs.add(message.getId());
                messageHashes.add(message.getHash());
                messagesSent++;
                JOptionPane.showMessageDialog(null, message.toString());
            } else if (action == 1) {
                storedMessages.add(message);
                message.storeToFile();
                JOptionPane.showMessageDialog(null, "Message stored successfully!");
            }
        }

        JOptionPane.showMessageDialog(null, "All messages sent. Total messages: " + messagesSent);
        totalMessagesAllowed = 0;
        messagesSent = 0;
    }

    private static void showRecentMessages() {
        if (sentMessages.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No messages sent yet.");
            return;
        }

        StringBuilder sb = new StringBuilder("=== Recent Messages ===\n");
        int count = Math.min(sentMessages.size(), 5);
        for (int i = sentMessages.size() - count; i < sentMessages.size(); i++) {
            Message msg = sentMessages.get(i);
            sb.append("To: ").append(msg.getRecipient())
              .append("\nMessage: ").append(msg.getContent())
              .append("\n---\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    private static void advancedMessagingMenu() {
        while (true) {
            String[] options = {
                "Send Message",
                "Display senders/recipients",
                "Display longest message",
                "Search by message ID",
                "Search messages by recipient",
                "Delete message by hash",
                "Display full report",
                "Back to Main Menu"
            };
            
            int choice = JOptionPane.showOptionDialog(null,
                "Advanced Messaging Operations",
                "Advanced Mode",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]);
            
            switch (choice) {
                case 0 -> sendAdvancedMessage();
                case 1 -> displaySendersAndRecipients();
                case 2 -> displayLongestMessage();
                case 3 -> searchByMessageId();
                case 4 -> searchByRecipient();
                case 5 -> deleteByHash();
                case 6 -> displayFullReport();
                case 7 -> {
                    return;
                }
                default -> {
                }
            }
        }
    }

    private static void sendAdvancedMessage() {
        Message message = new Message();

        String recipient;
        do {
            recipient = JOptionPane.showInputDialog("Enter recipient (10 digits):");
            if (recipient == null) return;
        } while (!message.validateRecipient(recipient));
        message.setRecipient(recipient);

        String content;
        do {
            content = JOptionPane.showInputDialog("Enter message (max 250 chars):");
            if (content == null) return;
        } while (!message.validateContent(content));
        message.setContent(content);

        message.generateMetadata();

        String[] actions = {"Send Message", "Disregard Message", "Store Message"};
        int action = JOptionPane.showOptionDialog(null,
            "What would you like to do with this message?",
            "Message Action",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            actions,
            actions[0]);
        
        switch (action) {
            case 0 -> {
                sentMessages.add(message);
                messageIDs.add(message.getId());
                messageHashes.add(message.getHash());
                JOptionPane.showMessageDialog(null, "Message sent successfully!\n" + message);
             }
            case 1 -> JOptionPane.showMessageDialog(null, "Message disregarded.");
            case 2 -> {
                storedMessages.add(message);
                message.storeToFile();
                JOptionPane.showMessageDialog(null, "Message stored successfully!");
             }
            default -> {
             }
        }
    }

    private static void displaySendersAndRecipients() {
        StringBuilder sb = new StringBuilder("=== Senders and Recipients ===\n");
        for (Message msg : sentMessages) {
            sb.append("To: ").append(msg.getRecipient()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    private static void displayLongestMessage() {
        if (sentMessages.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No sent messages!");
            return;
        }
        
        Message longest = Collections.max(sentMessages, 
            Comparator.comparingInt(m -> m.getContent().length()));
        
        JOptionPane.showMessageDialog(null,
            "Longest message (" + longest.getContent().length() + " chars):\n" +
            longest.getContent());
    }

    private static void searchByMessageId() {
        String id = JOptionPane.showInputDialog("Enter message ID to search:");
        if (id == null) return;
        
        for (Message msg : sentMessages) {
            if (msg.getId().equals(id)) {
                JOptionPane.showMessageDialog(null,
                    "Message found:\n" + msg);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Message ID not found!");
    }

    private static void searchByRecipient() {
        String recipient = JOptionPane.showInputDialog("Enter recipient to search:");
        if (recipient == null) return;
        
        StringBuilder sb = new StringBuilder("Messages to " + recipient + ":\n");
        boolean found = false;
        
        for (Message msg : sentMessages) {
            if (msg.getRecipient().equals(recipient)) {
                sb.append("- ID: ").append(msg.getId())
                  .append(", Content: ").append(msg.getContent()).append("\n");
                found = true;
            }
        }
        
        JOptionPane.showMessageDialog(null, 
            found ? sb.toString() : "No messages found for this recipient.");
    }

    private static void deleteByHash() {
        String hash = JOptionPane.showInputDialog("Enter message hash to delete:");
        if (hash == null) return;
        
        Iterator<Message> iterator = sentMessages.iterator();
        while (iterator.hasNext()) {
            Message msg = iterator.next();
            if (msg.getHash().equals(hash)) {
                iterator.remove();
                messageIDs.remove(msg.getId());
                messageHashes.remove(hash);
                JOptionPane.showMessageDialog(null, "Message deleted successfully!");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Message hash not found!");
    }

    private static void displayFullReport() {
        StringBuilder sb = new StringBuilder("=== Full Message Report ===\n");
        sb.append(String.format("%-12s %-12s %-15s %s\n",
            "Message ID", "Hash", "Recipient", "Content"));
        
        for (Message msg : sentMessages) {
            String shortContent = msg.getContent().length() > 30 ?
                msg.getContent().substring(0, 27) + "..." : msg.getContent();
            sb.append(String.format("%-12s %-12s %-15s %s\n",
                msg.getId(), msg.getHash(), msg.getRecipient(), shortContent));
        }
        
        sb.append("\nTotal messages: ").append(sentMessages.size());
        JOptionPane.showMessageDialog(null, sb.toString());
    }
}

        
        
        
    
       