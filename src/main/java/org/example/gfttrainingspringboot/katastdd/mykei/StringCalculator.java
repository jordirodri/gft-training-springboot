package org.example;

import java.util.ArrayList;
import java.util.List;

public class StringCalculator {

    public int add(String input) {
        if (input == null || input.isEmpty()) return 0;

        String delimiter = ",|\n";

        if (input.startsWith("//")) {
            int i = input.indexOf("\n");
            delimiter = input.substring(2, i);
            input = input.substring(i + 1);
        }

        int sum = 0;
        List<Integer> negatives = new ArrayList<>();
        for (String n : input.split(delimiter)) {
            int num = Integer.parseInt(n);
            if (num < 0) negatives.add(num);
            else if (num < 1000) sum += num;
        }

        if (!negatives.isEmpty()) {
            throw new IllegalArgumentException("Negativos: " + negatives);
        }

        return sum;
    }
}
