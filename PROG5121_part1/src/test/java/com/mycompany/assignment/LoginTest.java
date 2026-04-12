package com.mycompany.assignment;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginTest {
    private registration reg;
    private login log;

    @BeforeEach
    void setup() {
        // Create and populate a registration object
        reg = new registration();
        reg.name = "Kyle";
        reg.surname = "Smith";
        reg.RegUsername = "kyl_1";
        reg.RegPassword = "Ch&&sec@ke99!";

        // Pass it into the login constructor
        log = new login(reg);
    }

    @Test
    void testLoginSuccess() {
        assertTrue(log.validateInfo("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    void testLoginFailureWrongPassword() {
        assertFalse(log.validateInfo("kyl_1", "wrongPass"));
    }

    @Test
    void testLoginFailureWrongUsername() {
        assertFalse(log.validateInfo("wrongUser", "Ch&&sec@ke99!"));
    }
}
