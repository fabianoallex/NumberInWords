package numberinwords.portuguese;

import static org.junit.jupiter.api.Assertions.*;
import numberinwords.DecimalInWords;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

class DecimalInPortugueseTest {
    @Test
    @DisplayName("inWords")
    void inWords() {
        Map<BigDecimal, String> testCases = new HashMap<>();
        testCases.put(new BigDecimal("0.0"), "zero");
        testCases.put(new BigDecimal("0.1"), "um décimo");
        testCases.put(new BigDecimal("0.2"), "dois décimos");
        testCases.put(new BigDecimal("0.02"), "dois centésimos");
        testCases.put(new BigDecimal("1"), "um inteiro");
        testCases.put(new BigDecimal("1.0000"), "um inteiro");
        testCases.put(new BigDecimal("6.678"), "seis inteiros e seiscentos e setenta e oito milésimos");
        testCases.put(new BigDecimal("6.678000"), "seis inteiros e seiscentos e setenta e oito milésimos");

        testCases.put(new BigDecimal("10.01"), "dez inteiros e um centésimo");
        testCases.put(new BigDecimal("10.02"), "dez inteiros e dois centésimos");
        testCases.put(new BigDecimal("10.001"), "dez inteiros e um milésimo");
        testCases.put(new BigDecimal("10.002"), "dez inteiros e dois milésimos");
        testCases.put(new BigDecimal("10.0001"), "dez inteiros e um décimo de milésimo");
        testCases.put(new BigDecimal("10.0002"), "dez inteiros e dois décimos de milésimo");

        testCases.put(new BigDecimal("10.00001"), "dez inteiros e um centésimo de milésimo");
        testCases.put(new BigDecimal("10.00002"), "dez inteiros e dois centésimos de milésimo");
        testCases.put(new BigDecimal("10.000001"), "dez inteiros e um milionésimo");
        testCases.put(new BigDecimal("10.000002"), "dez inteiros e dois milionésimos");

        testCases.put(new BigDecimal("10.0000001"), "dez inteiros e um décimo de milionésimo");
        testCases.put(new BigDecimal("10.0000002"), "dez inteiros e dois décimos de milionésimo");

        testCases.put(new BigDecimal("1258963.125045038"),
                "um milhão duzentos e cinquenta e oito mil novecentos e sessenta e três inteiros e " +
                        "cento e vinte e cinco milhões quarenta e cinco mil e trinta e oito bilionésimos");

        DecimalInWords decimalNumber = new DecimalInPortuguese.Builder()
                .build();

        testCases.forEach((number, expectedResult) ->
                assertEquals(
                        expectedResult,
                        decimalNumber.inWords(number),
                        "retorno não esperado para o número " + number));
    }

    @Test
    @DisplayName("inWords (comma separator)")
    void inWordsWithCommaSeparator() {
        Map<BigDecimal, String> testCases = new HashMap<>();
        testCases.put(new BigDecimal("1258963.0"), "um milhão, duzentos e cinquenta e oito mil, novecentos e sessenta e três inteiros");
        testCases.put(new BigDecimal("1258963.38"),
                "um milhão, duzentos e cinquenta e oito mil, novecentos e sessenta e três inteiros e " +
                        "trinta e oito centésimos");
        testCases.put(new BigDecimal("1258963.000000038"),
                "um milhão, duzentos e cinquenta e oito mil, novecentos e sessenta e três inteiros e " +
                        "trinta e oito bilionésimos");
        testCases.put(new BigDecimal("1258963.125045038"),
                "um milhão, duzentos e cinquenta e oito mil, novecentos e sessenta e três inteiros e " +
                        "cento e vinte e cinco milhões, quarenta e cinco mil e trinta e oito bilionésimos");

        DecimalInWords decimalNumber = new DecimalInPortuguese.Builder()
                .withCommaSeparator(true)
                .build();

        testCases.forEach((number, expectedResult) ->
                assertEquals(
                        expectedResult,
                        decimalNumber.inWords(number),
                        "retorno não esperado para o número " + number));
    }
}