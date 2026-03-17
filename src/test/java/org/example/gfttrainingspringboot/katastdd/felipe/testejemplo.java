package org.example.gfttrainingspringboot.katastdd.felipe;
package org.example;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class StringCalculatorTest {

    private StringCalculator calculator = new StringCalculator();


    @Test
    public void testEmptyString() {
        assertThat(calculator.add("")).isEqualTo(0);
    }

    @Test
    public void testSingleNumber() {
        assertThat(calculator.add("1")).isEqualTo(1);
    }

    @Test
    public void testTwoNumbers() {
        assertThat(calculator.add("1,2")).isEqualTo(3);
    }

    @Test
    public void testManyNumbers() {
        assertThat(calculator.add("1,2,3,4,5")).isEqualTo(15);
    }

    @Test
    public void testNewlineDelimiter() {
        assertThat(calculator.add("1\n2,3")).isEqualTo(6);
    }

    @Test
    public void testCustomDelimiter() {
        assertThat(calculator.add("//;\n1;2")).isEqualTo(3);
    }

    @Test
    public void testCustomDelimiterMultiple() {
        assertThat(calculator.add("//|\n1|2|3")).isEqualTo(6);
    }

    @Test
    public void testIgnoreNumbersBiggerThan1000() {
        assertThat(calculator.add("1,1000,1,1000,2")).isEqualTo(2005);
    }

    @Test
    public void testNewlinesWithCustomDelimiter() {
        assertThat(calculator.add("//x\n1x2\n3")).isEqualTo(6);
    }
}

