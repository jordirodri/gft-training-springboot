package org.example.gfttrainingspringboot.katastdd.pauln;

import java.util.ArrayList;
import java.util.List;

/*
 * Handle empty string
 * Support single and multiple numbers
 * Support multiple delimiters
 * Support custom delimiter syntax
 * Negative numbers throw exception with list
 * Ignore numbers greater than 1000
 */

public class StringCalculator {

    public int add(String numbers) {
        if (numbers.isEmpty()) return 0;

        String delimiter = ",|\n";
        String numbersWithoutHeader = numbers;

        if (numbers.startsWith("//")) {
            delimiter = Character.toString(numbers.charAt(2));
            numbersWithoutHeader = numbers.substring(4);
        }

        return calculateSum(numbersWithoutHeader, delimiter);
    }

    private int calculateSum(String input, String delimiter) {
        String[] parts = input.split(delimiter);
        int total = 0;
        List<Integer> negatives = new ArrayList<>();

        for (String part : parts) {
            int num = Integer.parseInt(part.trim());

            if (num < 0) {
                negatives.add(num);
            } else if (num <= 1000) {
                total += num;
            }
        }

        if (!negatives.isEmpty()) {
            throw new IllegalArgumentException("negatives not permitted: " + negatives);
        }

        return total;
    }
}