package org.example.gfttrainingspringboot.katastdd.albert;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StringCalculatorTest {

            /*

1. Create a function that receives a string and returns the sum.
2. If the string is empty, return 0.
3. If there is only one number, return that number.
4. Support numbers separated by commas.
5. Support an unknown amount of numbers.
6. Allow new lines (\n) as separators together with commas.
7. Allow a custom delimiter using the format: //;\n1;2
8. If there are negative numbers, throw an exception.
9. The exception message must include all negative numbers found.
10. Ignore numbers greater than 1000.
11. The number 1000 must still be counted.

     */

    private final StringCalculator calculator = new StringCalculator();

    @ParameterizedTest(name = "add(''{0}'') should return {1}")
    @MethodSource("validInputs")
    void shouldReturnExpectedSumForValidInputs(String input, int expected) {
        assertThat(calculator.add(input)).isEqualTo(expected);
    }

    @ParameterizedTest(name = "add(''{0}'') should throw with negatives {1}")
    @MethodSource("negativeInputs")
    void shouldThrowExceptionIncludingAllNegativeNumbers(String input, String negatives) {
        assertThatThrownBy(() -> calculator.add(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Negatives not allowed: " + negatives);
    }

    private static Stream<Arguments> validInputs() {
        return Stream.of(
                Arguments.of("", 0),
                Arguments.of("7", 7),
                Arguments.of("1,2", 3),
                Arguments.of("1,2,3,4,5", 15),
                Arguments.of("1\n2,3", 6),
                Arguments.of("//;\n1;2", 3),
                Arguments.of("2,1001", 2),
                Arguments.of("2,1000", 1002)
        );
    }

    private static Stream<Arguments> negativeInputs() {
        return Stream.of(
                Arguments.of("-2", "[-2]"),
                Arguments.of("1,-2,3,-4", "[-2, -4]")
        );
    }
}
