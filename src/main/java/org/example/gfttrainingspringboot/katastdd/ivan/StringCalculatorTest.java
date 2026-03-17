import org.example.NegativeNumberException;
import org.example.StringCalculator;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StringCalculatorTest {

    @Test
    void testStringCalculator() throws NegativeNumberException {

        StringCalculator calculator = new StringCalculator();
        assertThat(calculator.sum("1\n2,3")).isEqualTo(6);
    }

    @Test
    void Diferentdelimiter() throws NegativeNumberException {
        StringCalculator calculator = new StringCalculator();
        assertThat(calculator.sum("//;\n2;3")).isEqualTo(5);
    }

    @Test
    void NegativeNumber() {
        StringCalculator calculator = new StringCalculator();
        assertThatThrownBy(() ->{calculator.sum("1,2,-2");}).isInstanceOf(NegativeNumberException.class).hasMessageContaining("-2");

    }

    @Test
    void GTThousand() throws NegativeNumberException {
        StringCalculator calculator = new StringCalculator();
        assertThat(calculator.sum("1,1000,10000,2")).isEqualTo(3);
    }

}
