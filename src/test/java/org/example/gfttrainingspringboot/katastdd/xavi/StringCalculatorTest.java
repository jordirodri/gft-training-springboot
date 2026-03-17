package org.example.gfttrainingspringboot.katastdd.xavi;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringCalculatorTest {

    @Test
    public void shouldReturn0WhenInputIsEmpty() {
        assertEquals(0, StringCalculator.add(""));
    }

    @Test
    public void shouldSumCommaSeparatedNumbers() {
        assertEquals(6, StringCalculator.add("1,2,3"));
    }

    @Test
    public void shouldHandleNewLinesBetweenNumbers() {
        assertEquals(6, StringCalculator.add("1\n2,3"));
    }

    @Test
    public void shouldSupportCustomDelimiter() {
        assertEquals(3, StringCalculator.add("//;\n1;2"));
    }

    @Test
    public void shouldIgnoreNumbersGreaterThan1000() {
        assertEquals(4, StringCalculator.add("1,1000,1,1000,2"));
    }

    @Test
    public void shouldThrowExceptionForNegativeNumbers() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> StringCalculator.add("1,-2,3")
        );

        assertTrue(ex.getMessage().contains("-2"));
    }
}