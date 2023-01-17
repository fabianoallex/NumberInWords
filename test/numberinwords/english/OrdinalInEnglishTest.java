package numberinwords.english;

import numberinwords.OrdinalInWords;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class OrdinalInEnglishTest {
    @Test
    @DisplayName("inWords")
    void inWords() {
        Map<Long, String> testCases = new HashMap<>();
        testCases.put(0L, ""); //zero
        testCases.put(-1L, ""); //negativo
        testCases.put(1L, "first");
        testCases.put(2L, "second");
        testCases.put(3L, "third");
        testCases.put(4L, "fourth");
        testCases.put(5L, "fifth");
        testCases.put(6L, "sixth");
        testCases.put(7L, "seventh");
        testCases.put(8L, "eighth");
        testCases.put(9L, "ninth");
        testCases.put(10L, "tenth");
        testCases.put(11L, "eleventh");
        testCases.put(12L, "twelfth");
        testCases.put(13L, "thirteenth");
        testCases.put(14L, "fourteenth");
        testCases.put(15L, "fifteenth");
        testCases.put(16L, "sixteenth");
        testCases.put(17L, "seventeenth");
        testCases.put(18L, "eighteenth");
        testCases.put(19L, "nineteenth");
        testCases.put(20L, "twentieth");
        testCases.put(21L, "twenty-first");
        testCases.put(99L, "ninety-ninth");
        testCases.put(100L, "one hundredth");
        testCases.put(101L, "one hundred first");
        testCases.put(120L, "one hundred twentieth");
        testCases.put(1120L, "one thousand one hundred twentieth");
        testCases.put(1000L, "one thousandth");
        testCases.put(1055L, "one thousand and fifty-fifth");
        testCases.put(500000L, "five hundred thousandth");
        testCases.put(7001L, "seven thousand and first");
        testCases.put(333002L, "three hundred thirty-three thousand and second");

        OrdinalInWords ordinalNumber = new OrdinalInEnglish.Builder()
                .build();

        testCases.forEach((number, expectedResult) ->
                assertEquals(
                        expectedResult,
                        ordinalNumber.inWords(number),
                        "retorno não esperado para o número " + number));
    }


    @Test
    @DisplayName("inWords (Number Representation)")
    void inWordsNumberRepresentation() {
        Map<Long, String> testCases = new HashMap<>();
        testCases.put(0L, ""); //zero
        testCases.put(-1L, ""); //negativo
        testCases.put(1L, "1st");
        testCases.put(2L, "2nd");
        testCases.put(3L, "3rd");
        testCases.put(4L, "4th");
        testCases.put(5L, "5th");
        testCases.put(6L, "6th");
        testCases.put(7L, "7th");
        testCases.put(8L, "8th");
        testCases.put(9L, "9th");
        testCases.put(10L, "10th");
        testCases.put(11L, "11th");
        testCases.put(12L, "12th");
        testCases.put(13L, "13th");
        testCases.put(14L, "14th");
        testCases.put(15L, "15th");
        testCases.put(16L, "16th");
        testCases.put(17L, "17th");
        testCases.put(18L, "18th");
        testCases.put(19L, "19th");
        testCases.put(20L, "20th");
        testCases.put(21L, "21st");
        testCases.put(99L, "99th");
        testCases.put(100L, "100th");
        testCases.put(101L, "101st");
        testCases.put(120L, "120th");
        testCases.put(1120L, "1120th");
        testCases.put(1000L, "1000th");
        testCases.put(1055L, "1055th");
        testCases.put(500000L, "500000th");
        testCases.put(7001L, "7001st");
        testCases.put(333002L, "333002nd");

        OrdinalInWords ordinalNumber = new OrdinalInEnglish.Builder()
                .withNumberRepresentation()
                .build();

        testCases.forEach((number, expectedResult) ->
                assertEquals(
                        expectedResult,
                        ordinalNumber.inWords(number),
                        "retorno não esperado para o número " + number));
    }
}