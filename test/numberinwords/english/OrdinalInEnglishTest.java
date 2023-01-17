package numberinwords.english;

import numberinwords.OrdinalInWords;
import numberinwords.portuguese.OrdinalInPortuguese;
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
}