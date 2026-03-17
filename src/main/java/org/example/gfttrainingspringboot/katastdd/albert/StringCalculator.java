package org.example.gfttrainingspringboot.katastdd.albert;

import java.util.ArrayList;
import java.util.List;

public class StringCalculator {

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

    public int add(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }

        String numbersSection = input;
        String delimiterRegex = ",|\n";

        if (input.startsWith("//")) {
            int customDelimiterLineBreakIndex = input.indexOf('\n');
            String customDelimiter = input.substring(2, customDelimiterLineBreakIndex);
            delimiterRegex = java.util.regex.Pattern.quote(customDelimiter);
            numbersSection = input.substring(customDelimiterLineBreakIndex + 1);
        }

        String[] numberTokens = numbersSection.split(delimiterRegex);
        List<Integer> negativeNumbers = new ArrayList<>();
        int sum = 0;

        for (String numberToken : numberTokens) {
            int parsedNumber = Integer.parseInt(numberToken);

            if (parsedNumber < 0) {
                negativeNumbers.add(parsedNumber);
                continue;
            }

            if (parsedNumber <= 1000) {
                sum += parsedNumber;
            }
        }

        if (!negativeNumbers.isEmpty()) {
            throw new IllegalArgumentException("Negatives not allowed: " + negativeNumbers);
        }

        return sum;
    }
}
