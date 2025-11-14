package com.attendace.testingfolder;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestOperationTest {

    @Test
    void add() {
        TestOperation testOperation = new TestOperation();
        assertEquals(5, testOperation.add(2, 3));
        assertNotEquals(6, testOperation.add(2, 3));
    }
}