/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poepart1;

/**
 *
 * @author RC_Student_lab
 */

 import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Random;
import java.util.Scanner;

class Message {
    private String messageId;
    private int messageNumber;
    private String recipient;
    private String message;
    private String messageHash;
    private static int messageCounter = 1;
    private static final Scanner scanner = new Scanner(System.in);

    public boolean checkRecipientCell(String cell) {
        return cell != null && cell.matches("^\\d{10}$");
    }

    public void generateMessageId() {
        Random rand = new Random();
        this.messageId = String.format("%010d", rand.nextInt(1000000000));
        this.messageNumber = messageCounter++;
    }

    public String createMessageHash() {
        String[] words = message.split("\\s+");
        String firstWord = words.length > 0 ? words[0] : "";
        String lastWord = words.length > 0 ? words[words.length - 1] : "";
        
        this.messageHash = (messageId.substring(0, 2) + ":" + messageNumber + ":" + 
                         firstWord + lastWord).toUpperCase();
        return this.messageHash;
    }

    public String sentMessage() {
        System.out.println("\nMessage Options:");
        System.out.println("1) Send Message");
        System.out.println("2) Discard Message");
        System.out.println("3) Store Message for later");
        System.out.print("Choose an option (1-3): ");
        
        String choice = scanner.nextLine();
        
        return switch (choice) {
            case "1" -> "Send Message";
            case "3" -> "Store Message to send later";
            default -> "Discard Message";
        };
    }

    public String printMessage() {
        return "Message ID: " + messageId + "\n" +
               "Message #: " + messageNumber + "\n" +
               "Recipient: " + recipient + "\n" +
               "Message: " + message + "\n" +
               "Hash: " + messageHash;
    }

    public void storeMessage() throws IOException {
        String messageData = String.format(
            "{\"messageId\":\"%s\",\"messageNumber\":%d,\"recipient\":\"%s\",\"message\":\"%s\",\"messageHash\":\"%s\"}\n",
            messageId, messageNumber, recipient, message, messageHash);
        
        Files.write(Paths.get("stored_messages.json"),
                messageData.getBytes(),
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND);
    }

    // Getters
    public String getMessageId() { return messageId; }
    public String getMessageHash() { return messageHash; }
    public String getRecipient() { return recipient; }
    public String getMessage() { return message; }
    
    // Setters
    public void setRecipient(String recipient) { this.recipient = recipient; }
    public void setMessage(String message) { this.message = message; }
}   

