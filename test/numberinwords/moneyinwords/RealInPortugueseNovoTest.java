package numberinwords.moneyinwords;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class RealInPortugueseNovoTest {
    @Test
    @DisplayName("inWords")
    void inWords() {
        Map<BigDecimal, String> testCases = new HashMap<>();
        testCases.put(new BigDecimal("0.0"), "zero real");
        testCases.put(new BigDecimal("0.01"), "um centavo de real");
        testCases.put(new BigDecimal("0.02"), "dois centavos de real");
        testCases.put(new BigDecimal("1.01"), "um real e um centavo");
        testCases.put(new BigDecimal("2.02"), "dois reais e dois centavos");
        testCases.put(new BigDecimal("1.00"), "um real");
        testCases.put(new BigDecimal("2.00"), "dois reais");
        testCases.put(new BigDecimal("1.001"), "um real e um milésimo");
        testCases.put(new BigDecimal("1.009"), "um real e nove milésimos");
        testCases.put(new BigDecimal("1.0001"), "um real e um décimo de milésimo");
        testCases.put(new BigDecimal("1.00001"), "um real e um centésimo de milésimo");
        testCases.put(new BigDecimal("1.000001"), "um real e um milionésimo");
        testCases.put(new BigDecimal("0.000001"), "um milionésimo de real");
        testCases.put(new BigDecimal("1.654321"), "um real e seiscentos e cinquenta e quatro mil trezentos e vinte e um milionésimos");
        testCases.put(new BigDecimal("0.654321"), "seiscentos e cinquenta e quatro mil trezentos e vinte e um milionésimos de real");
        testCases.put(new BigDecimal("1.10"), "um real e dez centavos");
        testCases.put(new BigDecimal("0.2"), "vinte centavos de real");
        testCases.put(new BigDecimal("20.2"), "vinte reais e vinte centavos");
        testCases.put(new BigDecimal("1000"), "mil reais");
        testCases.put(new BigDecimal("1000000"), "um milhão de reais");
        testCases.put(new BigDecimal("1100000"), "um milhão e cem mil reais");
        testCases.put(new BigDecimal("2000000"), "dois milhões de reais");
        testCases.put(new BigDecimal("1000000000"), "um bilhão de reais");
        testCases.put(new BigDecimal("1000000001"), "um bilhão e um reais");
        testCases.put(new BigDecimal("1234567890"),
                "um bilhão duzentos e trinta e quatro milhões " +
                        "quinhentos e sessenta e sete mil oitocentos e noventa reais");
        testCases.put(new BigDecimal("1234567890.000001"),
                "um bilhão duzentos e trinta e quatro milhões " +
                        "quinhentos e sessenta e sete mil oitocentos e noventa reais e um milionésimo");

        RealInPortuguese_novo decimalNumber = new RealInPortuguese_novo.Builder()
                .build();

        testCases.forEach((number, expectedResult) ->
                assertEquals(
                        expectedResult,
                        decimalNumber.inWords(number),
                        "retorno não esperado para o valor " + number));
    }

    @Test
    @DisplayName("inWords (comma separator)")
    void inWordsWithCommaSeparator() {
        Map<BigDecimal, String> testCases = new HashMap<>();
        testCases.put(new BigDecimal("1.654321"), "um real e seiscentos e cinquenta e quatro mil, trezentos e vinte e um milionésimos");
        testCases.put(new BigDecimal("0.654321"), "seiscentos e cinquenta e quatro mil, trezentos e vinte e um milionésimos de real");
        testCases.put(new BigDecimal("1234567890"),
                "um bilhão, duzentos e trinta e quatro milhões, " +
                        "quinhentos e sessenta e sete mil, oitocentos e noventa reais");
        testCases.put(new BigDecimal("1234567890.000001"),
                "um bilhão, duzentos e trinta e quatro milhões, " +
                        "quinhentos e sessenta e sete mil, oitocentos e noventa reais e um milionésimo");

        RealInPortuguese_novo decimalNumber = new RealInPortuguese_novo.Builder()
                .withCommaSeparator()
                .build();

        testCases.forEach((number, expectedResult) ->
                assertEquals(
                        expectedResult,
                        decimalNumber.inWords(number),
                        "retorno não esperado para o valor " + number));
    }
}