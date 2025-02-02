package org.tests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HelloTestTest {

    @Test
    void helloTestShouldPass() {
        HelloTest helloTest = new HelloTest();

        String actualResult = helloTest.helloTest();

        assertEquals("Hello Test", actualResult);
    }
}
