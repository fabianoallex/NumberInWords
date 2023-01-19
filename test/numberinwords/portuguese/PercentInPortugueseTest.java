package numberinwords.portuguese;

import numberinwords.DecimalInWords;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PercentInPortugueseTest {
    @Test
    void inWords() {
        Map<BigDecimal, String> testCases = new HashMap<>();
        testCases.put(new BigDecimal("0.0"), "zero por cento");
        testCases.put(new BigDecimal("0.1"), "zero ponto um por cento");
        testCases.put(new BigDecimal("0.2"), "zero ponto dois por cento");
        testCases.put(new BigDecimal("0.02"), "zero ponto zero dois por cento");
        testCases.put(new BigDecimal("100"), "cem por cento");
        testCases.put(new BigDecimal("1.1"), "um ponto um por cento");
        testCases.put(new BigDecimal("15.22"), "quinze ponto vinte e dois por cento");
        testCases.put(new BigDecimal("1.0000"), "um por cento");
        testCases.put(new BigDecimal("6.678"), "seis ponto seiscentos e setenta e oito por cento");
        testCases.put(new BigDecimal("31.01"), "trinta e um ponto zero um por cento");

        DecimalInWords decimalNumber = new PercentInPortuguese();

        testCases.forEach((number, expectedResult) ->
                assertEquals(
                        expectedResult,
                        decimalNumber.inWords(number),
                        "retorno não esperado para o número " + number));
    }
}