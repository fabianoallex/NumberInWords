package numberinwords.portuguese;

import numberinwords.CardinalInWords;
import numberinwords.Fractional;
import numberinwords.FractionalInWords;
import org.junit.jupiter.api.DisplayName;
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
        testCases.put(Fractional.of(2, 2), "dois meios");
        testCases.put(Fractional.of(2, 3), "dois terços");
        //tratar casos negativos
        //testCases.put(Fractional.of(-2L), "menos um meio");
        testCases.put(Fractional.of(10L), "um décimo");
        testCases.put(Fractional.of(11L), "um undécimo");
        testCases.put(Fractional.of(20L), "um vigésimo");
        testCases.put(Fractional.of(21L), "um vinte e um avos");
        testCases.put(Fractional.of(100L), "um centésimo");
        testCases.put(Fractional.of(101L), "um cento e um avos");
        testCases.put(Fractional.of(200L), "um ducentésimo");
        testCases.put(Fractional.of(3,400L), "três quadringentésimos");
        testCases.put(Fractional.of(3,404L), "três quatrocentos e quatro avos");
        testCases.put(Fractional.of(1000L), "um milésimo");
        testCases.put(Fractional.of(1000000L), "um milionésimo");
        testCases.put(Fractional.of(1000001L), "um um milhão e um avos");
        testCases.put(Fractional.of(1000000000L), "um bilionésimo");
        testCases.put(Fractional.of(1001000000L), "um um bilhão e um milhão de avos");
        testCases.put(Fractional.of(1000000000000L), "um trilionésimo");
        testCases.put(Fractional.of(1000000000000000L), "um quatrilionésimo");
        testCases.put(Fractional.of(1000000000000000000L), "um quintilionésimo");
        testCases.put(Fractional.of(2,1000000000000000000L), "dois quintilionésimos");
        testCases.put(Fractional.of(1001L), "um mil e um avos");
        testCases.put(Fractional.of(2,1001L), "dois mil e um avos");

        FractionalInWords fractionalInWords = new FractionalInPortuguese.Builder()
                .build();

        testCases.forEach((number, expectedResult) ->
                assertEquals(
                        expectedResult,
                        fractionalInWords.inWords(number),
                        "retorno não esperado para o numero " + number));
    }

    @Test
    @DisplayName("inWords (Decimal result)")
    void inWordsWithDecimalResult() {
        Map<Fractional, String> testCases = new HashMap<>();
        testCases.put(Fractional.of( 2), "cinco décimos");
        testCases.put(Fractional.of( 3), "trinta e três milhões trezentos e trinta e três mil trezentos e trinta e três centésimos de milionésimo");
        testCases.put(Fractional.of(2, 2), "um inteiro");
        testCases.put(Fractional.of(2, 3), "sessenta e seis milhões seiscentos e sessenta e seis mil seiscentos e sessenta e sete centésimos de milionésimo");
        testCases.put(Fractional.of(10L), "um décimo");
        testCases.put(Fractional.of(11L), "nove milhões noventa mil novecentos e nove centésimos de milionésimo");
        testCases.put(Fractional.of(20L), "cinco centésimos");
        testCases.put(Fractional.of(21L), "quatro milhões setecentos e sessenta e um mil novecentos e cinco centésimos de milionésimo");
        testCases.put(Fractional.of(100L), "um centésimo");
        testCases.put(Fractional.of(101L), "novecentos e noventa mil e noventa e nove centésimos de milionésimo");
        testCases.put(Fractional.of(200L), "cinco milésimos");
        testCases.put(Fractional.of(3,400L), "setenta e cinco décimos de milésimo");
        testCases.put(Fractional.of(3,404L), "setecentos e quarenta e dois mil quinhentos e setenta e quatro centésimos de milionésimo");
        testCases.put(Fractional.of(1000L), "um milésimo");
        testCases.put(Fractional.of(1000000L), "um milionésimo");
        testCases.put(Fractional.of(1000001L), "um milionésimo");
        testCases.put(Fractional.of(1000000000L), "zero");
        testCases.put(Fractional.of(1001000000L), "zero");
        testCases.put(Fractional.of(1000000000000L), "zero");
        testCases.put(Fractional.of(1000000000000000L), "zero");
        testCases.put(Fractional.of(1000000000000000000L), "zero");
        testCases.put(Fractional.of(2,1000000000000000000L), "zero");
        testCases.put(Fractional.of(1001L), "novecentos e noventa e nove milionésimos");
        testCases.put(Fractional.of(2,1001L), "mil novecentos e noventa e oito milionésimos");

        FractionalInWords fractionalInWords = new FractionalInPortuguese.Builder()
                .withDecimalResult(8)
                .build();

        testCases.forEach((number, expectedResult) ->
                assertEquals(
                        expectedResult,
                        fractionalInWords.inWords(number),
                        "retorno não esperado para o numero " + number));
    }

    @Test
    @DisplayName("inWords (Female)")
    void inWordsWithFemaleGender() {
        Map<Fractional, String> testCases = new HashMap<>();
        testCases.put(Fractional.of( 2), "uma metade");
        testCases.put(Fractional.of( 3), "uma terça parte");
        testCases.put(Fractional.of(2, 2), "duas metades");
        testCases.put(Fractional.of(2, 3), "duas terças partes");
        testCases.put(Fractional.of(10L), "uma décima parte");
        testCases.put(Fractional.of(11L), "uma undécima parte");
        testCases.put(Fractional.of(20L), "uma vigésima parte");
        testCases.put(Fractional.of(21L), "uma vinte e uma parte");
        testCases.put(Fractional.of(100L), "uma centésima parte");
        testCases.put(Fractional.of(101L), "uma cento e uma parte");
        testCases.put(Fractional.of(200L), "uma ducentésima parte");
        testCases.put(Fractional.of(3,400L), "três quadringentésimas partes");
        testCases.put(Fractional.of(3,404L), "três quatrocentas e quatro partes");
        testCases.put(Fractional.of(1000L), "uma milésima parte");
        testCases.put(Fractional.of(1000000L), "uma milionésima parte");
        testCases.put(Fractional.of(1000001L), "uma um milhão e uma parte");
        testCases.put(Fractional.of(1000000000L), "uma bilionésima parte");
        testCases.put(Fractional.of(1001000000L), "uma um bilhão e um milhão de parte");
        testCases.put(Fractional.of(1000000000000L), "uma trilionésima parte");
        testCases.put(Fractional.of(1000000000000000L), "uma quatrilionésima parte");
        testCases.put(Fractional.of(1000000000000000000L), "uma quintilionésima parte");
        testCases.put(Fractional.of(2,1000000000000000000L), "duas quintilionésimas partes");
        testCases.put(Fractional.of(1001L), "uma mil e uma parte");
        testCases.put(Fractional.of(2,1001L), "duas mil e uma partes");

        FractionalInWords fractionalInWords = new FractionalInPortuguese.Builder()
                .withFemaleGender()
                .build();

        testCases.forEach((number, expectedResult) ->
                assertEquals(
                        expectedResult,
                        fractionalInWords.inWords(number),
                        "retorno não esperado para o numero " + number));
    }
}