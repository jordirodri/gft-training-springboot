import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorTest {

    StringCalculator calc = new StringCalculator();

    @Test
    void emptyStringReturnsZero() {
        assertEquals(0, calc.add(""));
    }

    @Test
    void singleNumber() {
        assertEquals(5, calc.add("5"));
    }

    @Test
    void twoNumbers() {
        assertEquals(6, calc.add("1,5"));
    }

    @Test
    void multipleNumbers() {
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
    void negativeNumbersThrowException() {
        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> calc.add("1,-2,3,-4")
        );

        assertTrue(exception.getMessage().contains("-2"));
        assertTrue(exception.getMessage().contains("-4"));
    }

    @Test
    void ignoreNumbersGreaterThan1000() {
        assertEquals(2004, calc.add("1,1000,1,1000,2"));
    }
}
