package com.mycompany.assignment;

import javax.swing.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class message {
    
    private static final String JSON_FILE = "messages.json";
    private int totalMessages = 0;
    private Random random = new Random();

    // runs after successful login
    public void startMessaging() {
        // Welcome message
        JOptionPane.showMessageDialog(null, "Welcome to QuickChat.");

        int choice;
            // Main menu loop
        do {
            String menu = """
                    Please choose an option:
                    1) Send Message
                    2) Show Recently Sent Messages
                    3) Quit
                    """;
            // Get user choice
            String inputChoice = JOptionPane.showInputDialog(menu);
            if (inputChoice == null) return; // if user cancels
            try {
                choice = Integer.parseInt(inputChoice);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid option. Please enter a number.");
                continue;
            }
// Handle user choice
            switch (choice) {
                case 1 -> sendMessageFeature();
                case 2 -> JOptionPane.showMessageDialog(null, "Coming Soon.");
                case 3 -> {
                int confirm = JOptionPane.showConfirmDialog(
                        null,
                        "Are you sure you want to exit?",
                        "Confirm Exit",
                        JOptionPane.YES_NO_OPTION
                );
                if (confirm == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "Goodbye!");
                    System.exit(0);
                }
            }
                default -> JOptionPane.showMessageDialog(null, "Invalid option. Please try again.");
            }

        } while (true);
    }
    // Send Message feature
    private void sendMessageFeature() {

        // Get number of messages to send
        String inputNum = JOptionPane.showInputDialog("How many messages do you want to send?");
        if (inputNum == null) return;

        int numMessages;
        try {
            numMessages = Integer.parseInt(inputNum);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Enter a valid number.");
            return;
        }

        for (int i = 0; i < numMessages; i++) {
            // Get recipient cell number
            String recipient = JOptionPane.showInputDialog("Enter recipient cell number (+27XXXXXXXXX):");
            if (recipient == null) return;
            // Validate recipient number
            if (!checkRecipientCell(recipient)) {
                JOptionPane.showMessageDialog(null, "Invalid number, try again.");
                i--;
                continue;
            }
            // Get message text
            String messageText = JOptionPane.showInputDialog("Enter your message (max 250 characters):");
            if (messageText == null) return;
            // Validate message length
            if (messageText.length() > 250) {
                JOptionPane.showMessageDialog(null, "Message too long. Please limit to 250 characters.");
                i--;
                continue;
            }
// Generate message ID and hash
            String messageID = generateMessageID();
            String messageHash = createMessageHash(messageID, i + 1, messageText);
            // Show message summary
            JOptionPane.showMessageDialog(null,
                    "Message ID: " + messageID +
                    "\nMessage Hash: " + messageHash +
                    "\nRecipient: " + recipient +
                    "\nMessage: " + messageText,
                    "Message Summary",
                    JOptionPane.INFORMATION_MESSAGE
            );
            // Ask user what to do with the message
            String[] options = {"Send Message", "Disregard Message", "Store Message"};
            int action = JOptionPane.showOptionDialog(
                    null,
                    "Choose what to do with this message:",
                    "Message Options",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            String actionResult = sentMessage(action + 1); // +1 to match your switch numbering
            JOptionPane.showMessageDialog(null, actionResult);

            if (action == 0) totalMessages++; // count only sent messages
        }

        JOptionPane.showMessageDialog(null, "Total messages sent: " + totalMessages);
    }
    // Validate recipient cell number
    public boolean checkRecipientCell(String recipient) {
        return recipient.startsWith("+27") && recipient.length() <= 12;
    }
    // Generate random 10-digit message ID
    public String generateMessageID() {
        // Generate a random 10-digit number
        long id = 1000000000L + (long)(random.nextDouble() * 8999999999L);
        return String.valueOf(id);
    }
    // Create message hash
    public String createMessageHash(String messageID, int messageNum, String messageText) {
        
        String[] words = messageText.split(" ");
        String firstWord = words[0];
        String lastWord = words[words.length - 1];
        String firstTwoDigits = messageID.substring(0, 2);

        return (firstTwoDigits + ":" + messageNum + ":" + firstWord + lastWord).toUpperCase();
    }
// Handle message action
    public String sentMessage(int choice) {
        return switch (choice) {
            case 1 -> "Message successfully sent.";
            case 2 -> "Message disregarded.";
            case 3 -> "Message successfully stored.";
            default -> "Invalid option.";
        };
    }

    //Stores messages in a JSON array inside messages.json
    @SuppressWarnings("unchecked")
    public void storeMessage(String messageID, String messageHash, String recipient, String messageText) {
        JSONObject messageData = new JSONObject();
        messageData.put("MessageID", messageID);
        messageData.put("MessageHash", messageHash);
        messageData.put("Recipient", recipient);
        messageData.put("Message", messageText);

        JSONArray messagesArray = readExistingArray();

        messagesArray.add(messageData);

        try (FileWriter file = new FileWriter(JSON_FILE)) {
            file.write(messagesArray.toJSONString());
            file.flush();
            System.out.println("Message saved to " + JSON_FILE);
        } catch (IOException e) {
            System.out.println("Error saving message: " + e.getMessage());
        }
    }

    // Read existing JSON array from file if present. Return empty array when no file or parse error.
    private JSONArray readExistingArray() {
        JSONParser parser = new JSONParser();
        File f = new File(JSON_FILE);
        if (!f.exists()) {
            return new JSONArray();
        }

        try (FileReader reader = new FileReader(f)) {
            Object obj = parser.parse(reader);
            if (obj instanceof JSONArray) {
                return (JSONArray) obj;
            } else {
                return new JSONArray();
            }
        } catch (IOException | ParseException e) {
            // If parse error or IO error, overwrite with fresh array
            return new JSONArray();
        }
}
}
