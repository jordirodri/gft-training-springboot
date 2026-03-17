package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringCalculatorTest {

    StringCalculator calc = new StringCalculator();

    @Test
    void emptyStringReturnsZero() {
        assertEquals(0, calc.add(""));
    }

    @Test
    void singleNumberReturnsValue() {
        assertEquals(5, calc.add("5"));
    }

    @Test
    void twoNumbersReturnSum() {
        assertEquals(3, calc.add("1,2"));
    }

    @Test
    void multipleNumbersReturnSum() {
        assertEquals(10, calc.add("1,2,3,4"));
    }

    @Test
    void handlesNewLines() {
        assertEquals(6, calc.add("1\n2,3"));
    }

    @Test
    void customDelimiter() {
        assertEquals(3, calc.add("//;\n1;2"));
    }

    @Test
    void negativeNumberThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calc.add("1,-2,3");
        });
        assertTrue(exception.getMessage().contains("-2"));
    }

    @Test
    void multipleNegativesThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calc.add("1,-2,-3");
        });
        assertTrue(exception.getMessage().contains("-2"));
        assertTrue(exception.getMessage().contains("-3"));
    }

    @Test
    void ignoresNumbersGreaterThan1000() {
        assertEquals(4, calc.add("1,1000,1,1000,2"));
    }

}
