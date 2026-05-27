package com.mycompany.assignment;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    message msg = new message();

    // -------------------------------------------------------
    // TEST 1: Message Length Validation (Success and Failure)
    // -------------------------------------------------------
    @Test
    public void testMessageLengthSuccess() {
        String text = "Hi Mike, can you join us for dinner tonight";
        assertTrue(text.length() <= 250, "Message ready to send.");
    }

    @Test
    public void testMessageLengthFailure() {
        String longText = "A".repeat(260);
        int excess = longText.length() - 250;
        assertTrue(excess > 0, "Message exceeds 250 characters by " + excess + ", please reduce size.");
    }

    // -------------------------------------------------------
    // TEST 2: Recipient Cell Number Format
    // -------------------------------------------------------
    @Test
    public void testRecipientCellSuccess() {
        assertTrue(msg.checkRecipientCell("+27718693002"),
                "Cell phone number successfully captured.");
    }

    @Test
    public void testRecipientCellFailure() {
        assertFalse(msg.checkRecipientCell("08966553"),
                "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.");
    }

    // -------------------------------------------------------
    // TEST 3: Message Hash Generation
    // -------------------------------------------------------
    @Test
    public void testCreateMessageHash() {
        // Given test data from POE: messageID auto-generated
        String hash = msg.createMessageHash("0012345678", 0, "Hi thanks");
        assertEquals("00:0:HITHANKS", hash);
    }

    // -------------------------------------------------------
    // TEST 4: Message ID Generation
    // -------------------------------------------------------
    @Test
    public void testGenerateMessageID() {
        String id = msg.generateMessageID();
        assertEquals(10, id.length(), "Message ID generated successfully: " + id);
    }

    // -------------------------------------------------------
    // TEST 5: Send Message Options
    // -------------------------------------------------------
    @Test
    public void testSendMessageOption1() {
        assertEquals("Message successfully sent.", msg.sentMessage(1));
    }

    @Test
    public void testSendMessageOption2() {
        assertEquals("Press 0 to delete message.", msg.sentMessage(2));
    }

    @Test
    public void testSendMessageOption3() {
        assertEquals("Message successfully stored.", msg.sentMessage(3));
    }

    @Test
    public void testSendMessageInvalidOption() {
        assertEquals("Invalid option.", msg.sentMessage(9));
    }
}
