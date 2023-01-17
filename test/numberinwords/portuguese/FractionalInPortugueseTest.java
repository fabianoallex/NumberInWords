package numberinwords.portuguese;

import numberinwords.CardinalInWords;
import numberinwords.Fractional;
import numberinwords.FractionalInWords;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FractionalInPortugueseTest {

    @Test
    void inWords() {
        Map<Fractional, String> testCases = new HashMap<>();
        testCases.put(Fractional.of( 2), "um meio");
        testCases.put(Fractional.of( 3), "um terço");
        testCases.put(Fractional.of(-2L), "menos um meio");
        testCases.put(Fractional.of(10L), "dez avos");
        testCases.put(Fractional.of(11L), "onze avos");
        testCases.put(Fractional.of(20L), "vinte avos");

        FractionalInWords fractionalInWords = new FractionalInPortuguese.Builder()
                .build();

        testCases.forEach((number, expectedResult) ->
                assertEquals(
                        expectedResult,
                        fractionalInWords.inWords(number),
                        "retorno não esperado para o numero " + number));
    }
}