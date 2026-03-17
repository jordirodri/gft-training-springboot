package org.example.gfttrainingspringboot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorTest {

    StringCalculator stringCalculator = new StringCalculator();

    @Test
    void testSeparatedByComma(){
        assertEquals(6,stringCalculator.addStrings("1,2,3"));

    }
    @Test
    void testNewDelimiter() {
        assertEquals(6,stringCalculator.addStrings("//;\n1;2;3"));
    }

    @Test
    void testNegativeException(){
        assertThrows(IllegalArgumentException.class,() -> stringCalculator.addStrings("-1,2,3"));
    }

    @Test
    void testNewLine(){
        assertEquals(6,stringCalculator.addStrings("1\n2,3"));
    }

    @Test
    void testGreaterThanThousand(){
        assertEquals(6,stringCalculator.addStrings("1,5000,2,1000,3"));
    }
}