package org.exemple.project_one.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestArgon2 {
    private final String password="123456789";
    @Test
    public void doIt(){
        assertTrue(Argon2Utility.check(Argon2Utility.hash(password.toCharArray()),password.toCharArray()));
        assertFalse(Argon2Utility.check(Argon2Utility.hash(password.toCharArray()),(password+"h").toCharArray()));
        System.out.println(Argon2Utility.hash(password.toCharArray()));
    }
}
