package org.example.gfttrainingspringboot;

import java.util.ArrayList;

public class StringCalculator {

    public Integer addStrings(String numbersString){
        String[] separatedNumbers;
        String delimiter = declareDelimiter(numbersString);
        String numbersStringDepurated = depurateString(numbersString);

        String regex;

        if(numbersString.startsWith("//")){
            regex = delimiter + "|\n";
        } else{
            regex = ",|\n";
        }

        separatedNumbers = numbersStringDepurated.split(regex);

        return addUpNumbers(separatedNumbers);
    }

    private static int addUpNumbers(String[] separatedNumbers) {
        int total = 0;
        ArrayList<Integer> negativeNumbersFounded = new ArrayList<>();
        for(String num: separatedNumbers){
            int parsedNum = Integer.parseInt(num);
            if (parsedNum < 0) negativeNumbersFounded.add(parsedNum);
            if (parsedNum < 1000) total += parsedNum;
        }
        if(!negativeNumbersFounded.isEmpty()) throw new IllegalArgumentException("NEGATIVE NUMBERS: " + negativeNumbersFounded);
        return total;
    }

    public String declareDelimiter(String numbersString) {
        if (numbersString.startsWith("//")) {
            int end = numbersString.indexOf("\n");
            return numbersString.substring(2, end);
        }
        return ",";
    }

    public String depurateString(String numbersString){
        if (numbersString.startsWith("//")) {
            int endDelimiterDeclaration = numbersString.indexOf("\n");
            return numbersString.substring(endDelimiterDeclaration + 1);
        }
        return numbersString;
    }
}
