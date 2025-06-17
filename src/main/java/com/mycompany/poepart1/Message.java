/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poepart1;

/**
 *
 * @author RC_Student_lab
 */

import java.io.FileWriter;
 import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import javax.swing.JOptionPane;

class Message {
   private String id;
    private String recipient;
    private String content;
    private String hash;

    public boolean checkRecipientCell(String cell) {
        return cell != null && cell.matches("^\\d{10}$");
    }

    public void generateMetadata() {
        this.id = String.format("%010d", new Random().nextInt(1000000000));
        
        String[] words = content.split("\\s+");
        String first = words.length > 0 ? words[0] : "";
        String last = words.length > 1 ? words[words.length-1] : first;
        this.hash = (id.substring(0, 2) + ":" + first + last).toUpperCase();
    }
    
    public boolean validateRecipient(String recipient) {
        return recipient != null && recipient.matches("\\d{10}");
    }
    
    public boolean validateContent(String content) {
        return content != null && !content.isEmpty() && content.length() <= 250;
    }
    
    public void storeToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("stored_messages.txt", true))) {
            writer.println(id + "|" + recipient + "|" + content + "|" + hash);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error storing message: " + e.getMessage());
        }
    }
    
    @Override
    public String toString() {
        return """
               Message Details:
               Message ID: """ + id + "\n" +
               "Message Hash: " + hash + "\n" +
               "Recipient: " + recipient + "\n" +
               "Message: " + content;
    }
    
    // Getters and setters
    public String getId() { return id; }
    public String getRecipient() { return recipient; }
    public String getContent() { return content; }
    public String getHash() { return hash; }
    public void setId(String id) { this.id = id; }
    public void setRecipient(String recipient) { this.recipient = recipient; }
    public void setContent(String content) { this.content = content; }
    public void setHash(String hash) { this.hash = hash; }
}
