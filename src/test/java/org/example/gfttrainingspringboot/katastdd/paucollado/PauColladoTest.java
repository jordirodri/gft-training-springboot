package org.example.gfttrainingspringboot.katastdd.paucollado;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class PauColladoTest {
    @ParameterizedTest
    @CsvSource({"1;2;3", "'//:\n1:2:3'", "'//,\n1\n2,3'", "'1001;1;1002;2;1003;3'"})
    void test1 (String input) throws Exception {
        int sum = stringCalculator(input);
        assertThat(sum).isEqualTo(6);
    }

    @ParameterizedTest
    @CsvSource({"-1;2;3"})
    void test2 (String input) {
        try {
            int sum = stringCalculator(input);
        } catch (Exception e) {
            assertThat(e.getMessage()).isEqualTo("Negative number -1 detected.");
        }
    }

    @ParameterizedTest
    @CsvSource({"-1;-2;-3"})
    void test3 (String input) {
        try {
            int sum = stringCalculator(input);
        } catch (Exception e) {
            assertThat(e.getMessage()).isEqualTo("Negative numbers detected: -1, -2, -3.");
        }
    }

    int stringCalculator (String input) throws Exception {
        String divider = ";";
        int sum = 0;
        List<Integer> listOfNegatives = new ArrayList<>();

        if (input.contains("//")) {
            divider = input.charAt(2)+"";
            input = input.replaceAll("//"+divider+"\n","");
        }

        input = input.replaceAll("\n", divider);

        String[] slicedInput = input.split(divider);

        for (String word : slicedInput) {
            int number = Integer.parseInt(word);
            if (number < 0) {
                listOfNegatives.add(number);
            }

            if (number <= 1000) {
                sum += number;
            }
            else {
                sum += 0;
            }
        }

        switch (listOfNegatives.size()) {
            case 1:
                throw new Exception("Negative number "+listOfNegatives.get(0)+" detected.");
            case 0:
                break;
            default:
                String response = "";
                boolean isFirst = true;

                for (Integer listItem : listOfNegatives) {
                    if (isFirst) {
                        response += listItem;
                        isFirst = false;
                    }
                    else {
                        response += ", "+listItem;
                    }

                }
                throw new Exception("Negative numbers detected: "+response+".");
        }

        return sum;
    }
}
