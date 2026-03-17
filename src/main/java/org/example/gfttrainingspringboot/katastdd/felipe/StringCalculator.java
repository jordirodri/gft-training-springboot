package org.example.gfttrainingspringboot.katastdd.felipe;

package org.example;

import java.util.ArrayList;
import java.util.List;

public class StringCalculator {

    /**
     * Parses a string of numbers with configurable delimiters and returns their sum.
     *
     *
     *
     * Empty string returns 0
     * Numbers separated by comma or newline
     * Custom delimiter can be specified: "//[delimiter]\n[numbers]"
     * Negative numbers throw an exception with all negative numbers in message
     *º Numbers greater than 1000 are ignored
     */


    public int add(String input) {

        // If the input is empty, return 0
        if (input == null || input.isEmpty()) {
            return 0;
        }


        //Declare that "'" is the default delimiter
        String numbers = input;
        String delimiter = ",";

        // Check if custom delimiter is specified
        if (input.startsWith("//")) {

            // Extract the custom delimiter and the numbers part


            //Search the position of the first jump of line
            int newlineIndex = input.indexOf('\n');

            //If there is an jump of line, the string is divided in 2 parts
            //The delimiter and the numbers
            //Example: //:\n1;2;3
            //Delimiter = ; , numbers = 1;2;3
            if (newlineIndex != -1) {
                delimiter = input.substring(2, newlineIndex);
                numbers = input.substring(newlineIndex + 1);
            }
        }

        // Replace newlines with the delimiter to normalize the input
        numbers = numbers.replace("\n", delimiter);

        // Split all the numbers with the delimiter into this array, for then sum
        String[] parts = numbers.split(escapeRegexCharacters(delimiter), -1);

        //Empty arraylist to collect negative numbers to then print it in the exception
        List<Integer> negativeNumbers = new ArrayList<>();
        int sum = 0;

        //loops all the parts
        for (String part : parts) {
            if (part.isEmpty()) {
                continue;
            }

            //Takes the stings and becomes a number to be able to add it
            int number = Integer.parseInt(part.trim());

            // Check for negative numbers
            if (number < 0) {
                negativeNumbers.add(number);
            }

            // Ignore numbers greater than 1000
            if (number <= 1000 && number >= 0) {
                sum += number;
            }
        }

        // Throw exception if there are negative numbers
        if (!negativeNumbers.isEmpty()) {
            throw new IllegalArgumentException("Negative numbers not allowed: " + negativeNumbers);
        }

        return sum;
    }

    /**
     * Escapes regex special characters in the delimiter
     */
    private String escapeRegexCharacters(String input) {
        return input.replaceAll("[^a-zA-Z0-9]", "\\\\$0");
    }



}

