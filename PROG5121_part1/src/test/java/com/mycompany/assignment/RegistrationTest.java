package com.mycompany.assignment;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class RegistrationTest {
    registration reg = new registration();

    @Test
    void testValidUsername() {
        assertTrue(reg.checkUsername("kyl_1"));
    }

    @Test
    void testInvalidUsername() {
        assertFalse(reg.checkUsername("kyle!!!!!!!"));
    }

    @Test
    void testValidPassword() {
        assertTrue(reg.checkPassword("Ch&&sec@ke99!"));
    }

    @Test
    void testInvalidPassword() {
        assertFalse(reg.checkPassword("password"));
    }

    @Test
    void testValidCellNumber() {
        assertTrue(reg.checkCellNumber("+27838968976"));
    }

    @Test
    void testInvalidCellNumber() {
        assertFalse(reg.checkCellNumber("08966553"));
    }
}
