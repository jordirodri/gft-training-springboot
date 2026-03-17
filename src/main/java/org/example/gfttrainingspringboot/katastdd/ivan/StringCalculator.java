package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class StringCalculator {

    public int sum(String input) throws NegativeNumberException {
        /*
            2\n,3,4
         */

        String[] nums = getNums(input);

        return hacerSuma(nums);

    }

    private int hacerSuma(String[] nums) throws NegativeNumberException {

        List<Integer> negativeNums = new ArrayList<>();


        int res = 0;

        for  (String num : nums) {

            int numero =  Integer.parseInt(num);

            if(numero < 0)
                negativeNums.add(numero);

            if(numero < 1000)
                res += Integer.parseInt(num);

        }

        if(!negativeNums.isEmpty()){

            String negativeToString = negativeNums.toString().replace("[", "").replace("]", "") ;

            throw new NegativeNumberException(
                    negativeNums.size() == 1 ? "There is a negative number " + negativeToString : "There are negative numbers"  + negativeNums
            );
        }

        return res;

    }

    private String[] getNums(String input) {

        String delimiter;

        if (input.startsWith("//")) {

            int indexDelimiter = input.indexOf("\n");
            delimiter = input.substring(2, indexDelimiter);

            String numbers = input.substring(indexDelimiter + 1);

            return numbers.split(delimiter);




        }
        else{
            delimiter = "[,\n]";
            return input.split(delimiter);
        }

    }

}

