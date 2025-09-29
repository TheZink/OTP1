package com.attendace.Utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class CryptoUtilsTest {

    CryptoUtils crypto = new CryptoUtils();

    @Test
    void testHash() {
        String plainData = "TestPassword123!";
        assertNotNull(crypto.hash(plainData));
    }

    @Test
    void testVerify() {
        String plainData = "TestPassword123!";
        String hashedData = crypto.hash(plainData);
        assertEquals(true, crypto.verify("TestPassword123!", hashedData));
    }
}
