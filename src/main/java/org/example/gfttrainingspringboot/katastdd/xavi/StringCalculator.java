package org.example.gfttrainingspringboot.katastdd.xavi;

import java.util.ArrayList;
import java.util.List;

public class StringCalculator {

    public static int add(String input) {
        if (input.isEmpty())
            return 0;

        // Estos delimitadores son como si fueran por defecto
        String delimiter = "[,\n]";
        String numbers = input;

        if (input.startsWith("//")) {
            int delimiterEndIndex = input.indexOf("\n");
            delimiter = input.substring(2, delimiterEndIndex);
            numbers = input.substring(delimiterEndIndex + 1);
        }

        String[] parts = numbers.split(delimiter);
        int sum = 0;

        List<Integer> negatives = new ArrayList<>();

        for (String part : parts) {
            if (part.isEmpty()) continue;

            int num = Integer.parseInt(part);

            if (num < 0) {
                negatives.add(num);
            }

            if (num < 1000) {
                sum += num;
            }
        }

        if (!negatives.isEmpty()) {
            throw new IllegalArgumentException(
                    "Negativos no permitidos: " + negatives
            );
        }

        return sum;
    }
}