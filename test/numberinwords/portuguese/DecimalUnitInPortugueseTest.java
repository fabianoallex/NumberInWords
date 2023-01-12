package numberinwords.portuguese;

import static org.junit.jupiter.api.Assertions.*;
import numberinwords.DecimalUnitInWords;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

class DecimalUnitInPortugueseTest {
    @Test
    @DisplayName("inWords")
    void inWords() {
        Map<BigDecimal, String> testCases = new HashMap<>();
        testCases.put(new BigDecimal("0.0"), "zero unidade");
        testCases.put(new BigDecimal("0.1"), "um décimo de unidade");
        testCases.put(new BigDecimal("0.2"), "dois décimos de unidade");
        testCases.put(new BigDecimal("0.02"), "dois centésimos de unidade");
        testCases.put(new BigDecimal("1"), "uma unidade");
        testCases.put(new BigDecimal("1.0000"), "uma unidade");
        testCases.put(new BigDecimal("2.2"), "duas unidades e dois décimos");
        testCases.put(new BigDecimal("6.678"), "seis unidades e seiscentos e setenta e oito milésimos");
        testCases.put(new BigDecimal("6.678000"), "seis unidades e seiscentos e setenta e oito milésimos");
        testCases.put(new BigDecimal("10.01"), "dez unidades e um centésimo");
        testCases.put(new BigDecimal("10.02"), "dez unidades e dois centésimos");
        testCases.put(new BigDecimal("10.001"), "dez unidades e um milésimo");
        testCases.put(new BigDecimal("10.002"), "dez unidades e dois milésimos");
        testCases.put(new BigDecimal("10.0001"), "dez unidades e um décimo de milésimo");
        testCases.put(new BigDecimal("10.0002"), "dez unidades e dois décimos de milésimo");
        testCases.put(new BigDecimal("10.00001"), "dez unidades e um centésimo de milésimo");
        testCases.put(new BigDecimal("10.00002"), "dez unidades e dois centésimos de milésimo");
        testCases.put(new BigDecimal("10.000001"), "dez unidades e um milionésimo");
        testCases.put(new BigDecimal("10.000002"), "dez unidades e dois milionésimos");
        testCases.put(new BigDecimal("10.0000001"), "dez unidades e um décimo de milionésimo");
        testCases.put(new BigDecimal("10.0000002"), "dez unidades e dois décimos de milionésimo");
        testCases.put(new BigDecimal("1258963.125045038"),
                "um milhão duzentas e cinquenta e oito mil novecentas e sessenta e três unidades e " +
                        "cento e vinte e cinco milhões quarenta e cinco mil e trinta e oito bilionésimos");

        DecimalUnitInWords decimalNumber = new DecimalUnitInPortuguese.Builder()
                .build();

        testCases.forEach((number, expectedResult) ->
                assertEquals(
                        expectedResult,
                        decimalNumber.inWords(number),
                        "retorno não esperado para o número " + number));
    }

    @Test
    @DisplayName("inWords (Units description and female)")
    void inWordsWithUnitDescriptionChangedAndFemale() {
        Map<BigDecimal, String> testCases = new HashMap<>();
        testCases.put(new BigDecimal("0.0"), "Nenhuma casa");
        testCases.put(new BigDecimal("0.1"), "um décimo de casa");
        testCases.put(new BigDecimal("0.2"), "dois décimos de casa");
        testCases.put(new BigDecimal("0.02"), "dois centésimos de casa");
        testCases.put(new BigDecimal("1"), "uma casa");
        testCases.put(new BigDecimal("1.0000"), "uma casa");
        testCases.put(new BigDecimal("2.2"), "duas casas e dois décimos");

        DecimalUnitInWords decimalNumber = new DecimalUnitInPortuguese.Builder()
                .withZeroDescription("Nenhuma")
                .withUnitDescription("casa")
                .build();

        testCases.forEach((number, expectedResult) ->
                assertEquals(
                        expectedResult,
                        decimalNumber.inWords(number),
                        "retorno não esperado para o número " + number));
    }

    @Test
    @DisplayName("inWords (Units description and male)")
    void inWordsWithUnitDescriptionChangedAndMale() {
        Map<BigDecimal, String> testCases = new HashMap<>();
        testCases.put(new BigDecimal("0.0"), "Nenhum carro");
        testCases.put(new BigDecimal("0.1"), "um décimo de carro");
        testCases.put(new BigDecimal("0.2"), "dois décimos de carro");
        testCases.put(new BigDecimal("0.02"), "dois centésimos de carro");
        testCases.put(new BigDecimal("1"), "um carro");
        testCases.put(new BigDecimal("1.0000"), "um carro");
        testCases.put(new BigDecimal("2.2"), "dois carros e dois décimos");

        DecimalUnitInWords decimalNumber = new DecimalUnitInPortuguese.Builder()
                .withMaleGender()
                .withZeroDescription("Nenhum")
                .withUnitDescription("carro")
                .build();

        testCases.forEach((number, expectedResult) ->
                assertEquals(
                        expectedResult,
                        decimalNumber.inWords(number),
                        "retorno não esperado para o número " + number));
    }
}