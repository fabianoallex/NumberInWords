package numberinwords;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.*;

class FractionalTest {
    @Test
    void of() {
        Map<Fractional, List<Long>> testCases = new HashMap<>();

        testCases.put(
                Fractional.of(2),
                Arrays.asList(0L, 1L, 2L)); //expecteds: whole, numerator, denominator

        testCases.put(
                Fractional.of(5, 2),
                Arrays.asList(0L, 5L, 2L));

        testCases.put(
                Fractional.of(33,5, 2),
                Arrays.asList(33L, 5L, 2L));

        testCases.forEach((fractional, longs) -> {
            assertEquals(
                    longs.get(0),
                    fractional.getWholePart(),
                    "Parte inteira retornada é diferente da esperada para " + fractional);

            assertEquals(
                    longs.get(1),
                    fractional.getNumerator(),
                    "Numerador retornado é diferente do esperado para " + fractional);

            assertEquals(
                    longs.get(2),
                    fractional.getDenominator(),
                    "Denominador retornado é diferente do esperado para " + fractional);
        });
    }

    @Test
    void toDouble() {
        Map<Fractional, Double> testCases = new HashMap<>();

        testCases.put(
                Fractional.of(2),
                0.5);

        testCases.put(
                Fractional.of(5, 2),
                2.5);

        testCases.put(
                Fractional.of(2, 5),
                0.4);

        testCases.put(
                Fractional.of(33,5, 2),
                35.5);

        testCases.forEach((fractional, aDouble) ->
            assertEquals(
                    aDouble,
                    fractional.toDouble(),
                    "toDouble retornado é diferente do esperado para " + fractional));
    }

    @Test
    void toImproper() {
        Map<Fractional, Fractional> testCases = new HashMap<>();

        testCases.put(
                Fractional.of(2), // 1/2
                Fractional.of(2)); //quando não for misto (sem parte inteira) toImproper retorna a própria fração

        testCases.put(
                Fractional.of(2, 3), //2/3
                Fractional.of(2, 3)); //sem parte inteira também

        testCases.put(
                Fractional.of(19,2, 5),  //  19 2/5 = 19.4
                Fractional.of(97, 5));            //  97/5   = 19.4

        testCases.put(
                Fractional.of(33,5, 2),  // 33 5/2 = 35.5
                Fractional.of(71, 2));             // 71/2 = 35.5

        testCases.forEach((fractional1, fractional2) -> {
            assertEquals(
                    fractional1.toImproper().toDouble(),
                    fractional2.toDouble(),
                    "toImproper retonado não é o esperado. %s, %s".formatted(
                            fractional1.toImproper(), fractional2));
        });
    }

    @Test
    void toMixed() {
    }

    @Test
    void isMixed() {
    }

    @Test
    void isProper() {
    }

    @Test
    void isUnproper() {
    }
}