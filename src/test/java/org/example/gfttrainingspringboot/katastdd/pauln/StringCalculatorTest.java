package org.example.gfttrainingspringboot.katastdd.pauln;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorTest {

    StringCalculator calculator = new StringCalculator();

    @Test
    void testEmptyStringReturnsZero() {
        assertEquals(0, calculator.add(""));
    }

    @Test
    void testSingleNumberReturnsValue() {
        assertEquals(1, calculator.add("1"));
        assertEquals(5, calculator.add("5"));
    }

    @Test
    void testCommaSeparatedNumbers() {
        assertEquals(6, calculator.add("1,2,3"));
    }

    @Test
    void testNewLineAsDelimiter() {
        assertEquals(6, calculator.add("1\n2,3"));
    }

    @Test
    void testCustomDelimiter() {
        assertEquals(3, calculator.add("//;\n1;2"));
    }

    @Test
    void testNegativeNumbersThrowException() {
        try {
            calculator.add("1,-2,-3");
            fail("Should have thrown an exception");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("-2"));
            assertTrue(e.getMessage().contains("-3"));
        }
    }

    @Test
    void testNumbersGreaterThanOneThousandAreIgnored() {
        assertEquals(2, calculator.add("2,1001"));
    }
}