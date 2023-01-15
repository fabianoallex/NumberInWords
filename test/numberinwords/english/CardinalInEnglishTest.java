package numberinwords.english;

import numberinwords.CardinalInWords;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CardinalInEnglishTest {
    @Test
    @DisplayName("inWords")
    void inWords() {
        Map<Long, String> testCases = new HashMap<>();
        testCases.put(0L, "zero");
        testCases.put(1L, "one");
        testCases.put(-1L, "negative one");
        testCases.put(10L, "ten");
        testCases.put(11L, "eleven");
        testCases.put(20L, "twenty");
        testCases.put(100L, "one hundred");
        testCases.put(202L, "two hundred two");
        testCases.put(220L, "two hundred twenty");
        testCases.put(234L, "two hundred thirty-four");
        testCases.put(349L, "three hundred forty-nine");
        testCases.put(1000L, "one thousand");
        testCases.put(1001L, "one thousand and one");
        testCases.put(1017L, "one thousand and seventeen");
        testCases.put(2099L, "two thousand and ninety-nine");
        testCases.put(30000L, "thirty thousand");
        testCases.put(80009L, "eighty thousand and nine");
        testCases.put(100000L, "one hundred thousand");
        testCases.put(482000L, "four hundred eighty-two thousand");
        testCases.put(1000000L, "one million");
        testCases.put(50000200L, "fifty million two hundred");
        testCases.put(60933300L, "sixty million nine hundred thirty-three thousand three hundred");
        testCases.put(1000000000L, "one billion");
        testCases.put(2000000000L, "two billion");
        testCases.put(20000000000L, "twenty billion");
        testCases.put(30000000000L, "thirty billion");
        testCases.put(60000000000L, "sixty billion");
        testCases.put(90000000000L, "ninety billion");
        testCases.put(100000000000L, "one hundred billion");
        testCases.put(200000000000L, "two hundred billion");
        testCases.put(300000000000L, "three hundred billion");
        testCases.put(600000000000L, "six hundred billion");
        testCases.put(900000000000L, "nine hundred billion");
        testCases.put(1000000000000L, "one trillion");
        testCases.put(10000000000000L, "ten trillion");
        testCases.put(100000000000000L, "one hundred trillion");
        testCases.put(1000000000000000L, "one quadrillion");
        testCases.put(10000000000000000L, "ten quadrillion");
        testCases.put(100000000000000000L, "one hundred quadrillion");
        testCases.put(1000000000000000000L, "one quintillion");
        testCases.put(-1000000000000000000L, "negative one quintillion");

        CardinalInWords cardinalNumber = new CardinalInEnglish.Builder()
                .build();

        testCases.forEach((number, expectedResult) ->
                assertEquals(
                        expectedResult,
                        cardinalNumber.inWords(number),
                        "retorno não esperado para o número " + number));
    }

    @Test
    @DisplayName("inWords (Comma and 'and' in hundred)")
    void inWordsWithCommaSeparator() {
        Map<Long, String> testCases = new HashMap<>();
        testCases.put(1000L, "one thousand");
        testCases.put(1017L, "one thousand and seventeen");
        testCases.put(2099L, "two thousand and ninety-nine");
        testCases.put(30000L, "thirty thousand");
        testCases.put(80009L, "eighty thousand and nine");
        testCases.put(100000L, "one hundred thousand");
        testCases.put(482000L, "four hundred and eighty-two thousand");
        testCases.put(1000000L, "one million");
        testCases.put(50000200L, "fifty million, two hundred");
        testCases.put(60933300L, "sixty million, nine hundred and thirty-three thousand, three hundred");
        testCases.put(1000000000L, "one billion");
        testCases.put(2000000000L, "two billion");
        testCases.put(90000000000L, "ninety billion");
        testCases.put(100000000000L, "one hundred billion");
        testCases.put(1000055000000000L, "one quadrillion, fifty-five billion");
        testCases.put(10000000000000000L, "ten quadrillion");
        testCases.put(100000000000000000L, "one hundred quadrillion");
        testCases.put(1000000000000000000L, "one quintillion");
        testCases.put(-1000000000000000000L, "negative one quintillion");

        CardinalInWords cardinalNumber = new CardinalInEnglish.Builder()
                .withCommaSeparator()
                .withAndInHundred()
                .build();

        testCases.forEach((number, expectedResult) ->
                assertEquals(
                        expectedResult,
                        cardinalNumber.inWords(number),
                        "retorno não esperado para o número " + number));
    }
}