package numberinwords;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class MoneyInWordsBuilderChoosersTest {
    @Test
    void forBitcoinInPortuguese() {
        var bitcoin = new MoneyInWordsBuilderChoosers()
                .forBitcoinInPortuguese()
                .build();

        Map<BigDecimal, String> testCases = new HashMap<>();
        testCases.put(new BigDecimal("0.0"), "zero bitcoin");
        testCases.put(new BigDecimal("1.0"), "um bitcoin");
        testCases.put(new BigDecimal("2.0"), "dois bitcoins");
        testCases.put(new BigDecimal("2.1"), "dois bitcoins e dez milhões de satoshis");
        testCases.put(new BigDecimal("2.00000001"), "dois bitcoins e um satoshi");
        testCases.put(new BigDecimal("0.00000001"), "um satoshi");
        testCases.put(new BigDecimal("0.00000002"), "dois satoshis");
        testCases.put(new BigDecimal("0.000000001"), "um bilionésimo de bitcoin");
        testCases.put(new BigDecimal("0.01"), "um milhão de satoshis");
        testCases.put(new BigDecimal("0.00001"), "mil satoshis");

        testCases.forEach((number, expectedResult) ->
                assertEquals(
                        expectedResult,
                        bitcoin.inWords(number),
                        "retorno não esperado para o número " + number));
    }

    @Test
    void forDollarInPortuguese() {
        var dollar = new MoneyInWordsBuilderChoosers()
                .forDollarInPortuguese()
                .build();

        Map<BigDecimal, String> testCases = new HashMap<>();
        testCases.put(new BigDecimal("0.0"), "zero dólar");
        testCases.put(new BigDecimal("1.0"), "um dólar");
        testCases.put(new BigDecimal("2.0"), "dois dólares");
        testCases.put(new BigDecimal("2.02"), "dois dólares e dois centavos");
        testCases.put(new BigDecimal("2.1"), "dois dólares e dez centavos");
        testCases.put(new BigDecimal("0.01"), "um centavo de dólar");
        testCases.put(new BigDecimal("0.02"), "dois centavos de dólar");
        testCases.put(new BigDecimal("0.001"), "um milésimo de dólar");

        testCases.forEach((number, expectedResult) ->
                assertEquals(
                        expectedResult,
                        dollar.inWords(number),
                        "retorno não esperado para o número " + number));
    }

    @Test
    void forRealInPortuguese() {
        var real = new MoneyInWordsBuilderChoosers()
                .forRealInPortuguese()
                .build();

        Map<BigDecimal, String> testCases = new HashMap<>();
        testCases.put(new BigDecimal("0.0"), "zero real");
        testCases.put(new BigDecimal("1.0"), "um real");
        testCases.put(new BigDecimal("2.0"), "dois reais");
        testCases.put(new BigDecimal("2.02"), "dois reais e dois centavos");
        testCases.put(new BigDecimal("2.1"), "dois reais e dez centavos");
        testCases.put(new BigDecimal("0.01"), "um centavo de real");
        testCases.put(new BigDecimal("0.02"), "dois centavos de real");
        testCases.put(new BigDecimal("0.001"), "um milésimo de real");

        testCases.forEach((number, expectedResult) ->
                assertEquals(
                        expectedResult,
                        real.inWords(number),
                        "retorno não esperado para o número " + number));
    }

    @Test
    void forPoundInPortuguese() {
        var pound = new MoneyInWordsBuilderChoosers()
                .forPoundInPortuguese()
                .build();

        Map<BigDecimal, String> testCases = new HashMap<>();
        testCases.put(new BigDecimal("0.0"), "zero libra esterlina");
        testCases.put(new BigDecimal("1.0"), "uma libra esterlina");
        testCases.put(new BigDecimal("2.0"), "duas libras esterlinas");
        testCases.put(new BigDecimal("2.02"), "duas libras esterlinas e dois pence");
        testCases.put(new BigDecimal("2.1"), "duas libras esterlinas e dez pence");
        testCases.put(new BigDecimal("0.01"), "um penny");
        testCases.put(new BigDecimal("0.02"), "dois pence");
        testCases.put(new BigDecimal("0.001"), "um milésimo de libra esterlina");

        testCases.forEach((number, expectedResult) ->
                assertEquals(
                        expectedResult,
                        pound.inWords(number),
                        "retorno não esperado para o número " + number));
    }
}