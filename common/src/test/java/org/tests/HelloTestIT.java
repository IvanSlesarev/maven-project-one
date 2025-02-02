package org.tests;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelloTestIT {

    @Test
    void helloTestShouldPass() {
        HelloTest helloTest = new HelloTest();

        String actualResult = helloTest.helloTest();

        assertNotNull(actualResult);
        assertTrue(actualResult.contains("Hello"));
        assertEquals("Hello Test", actualResult);
    }
}
