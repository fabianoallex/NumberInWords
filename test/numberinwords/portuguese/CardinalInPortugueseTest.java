package numberinwords.portuguese;

import numberinwords.NumberInWords;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CardinalInPortugueseTest {

    @Test
    @DisplayName("inWords")
    void inWords() {
        Map<Long, String> testCases = new HashMap<>();
        testCases.put(0L, "zero");
        testCases.put(1L, "um");
        testCases.put(-1L, "menos um");
        testCases.put(10L, "dez");
        testCases.put(11L, "onze");
        testCases.put(20L, "vinte");
        testCases.put(100L, "cem");
        testCases.put(202L, "duzentos e dois");
        testCases.put(220L, "duzentos e vinte");
        testCases.put(349L, "trezentos e quarenta e nove");
        testCases.put(1000L, "mil");
        testCases.put(1017L, "mil e dezessete");
        testCases.put(2099L, "dois mil e noventa e nove");
        testCases.put(30000L, "trinta mil");
        testCases.put(80009L, "oitenta mil e nove");
        testCases.put(100000L, "cem mil");
        testCases.put(482000L, "quatrocentos e oitenta e dois mil");
        testCases.put(1000000L, "um milhão");
        testCases.put(50000200L, "cinquenta milhões e duzentos");
        testCases.put(60933300L, "sessenta milhões novecentos e trinta e três mil e trezentos");
        testCases.put(1000000000L, "um bilhão");
        testCases.put(2000000000L, "dois bilhões");
        testCases.put(20000000000L, "vinte bilhões");
        testCases.put(30000000000L, "trinta bilhões");
        testCases.put(60000000000L, "sessenta bilhões");
        testCases.put(90000000000L, "noventa bilhões");
        testCases.put(100000000000L, "cem bilhões");
        testCases.put(200000000000L, "duzentos bilhões");
        testCases.put(300000000000L, "trezentos bilhões");
        testCases.put(600000000000L, "seiscentos bilhões");
        testCases.put(900000000000L, "novecentos bilhões");
        testCases.put(1000000000000L, "um trilhão");
        testCases.put(10000000000000L, "dez trilhões");
        testCases.put(100000000000000L, "cem trilhões");
        testCases.put(1000000000000000L, "um quatrilhão");
        testCases.put(10000000000000000L, "dez quatrilhões");
        testCases.put(100000000000000000L, "cem quatrilhões");
        testCases.put(1000000000000000000L, "um quintilhão");
        testCases.put(-1000000000000000000L, "menos um quintilhão");
        testCases.put(
                Long.MAX_VALUE,
                "nove quintilhões duzentos e vinte e três quatrilhões trezentos e setenta" +
                        " e dois trilhões trinta e seis bilhões oitocentos e cinquenta e quatro" +
                        " milhões setecentos e setenta e cinco mil oitocentos e sete");

        //testCases.put(10000000000000000000L, "dez quintilhões"); //não pode ser testado, pois valor maior que Long.MAX_VALUE

        NumberInWords<Long> cardinalNumber = new CardinalInPortuguese.Builder().build();
        testCases.forEach((number, expectedResult) ->
                assertEquals(
                        expectedResult,
                        cardinalNumber.inWords(number),
                        "retorno não esperado para o numero " + number));
    }

    @Test
    @DisplayName("inWords (Female Gender)")
    void inWordsWithFemaleGender() {
        Map<Long, String> testCases = new HashMap<>();
        testCases.put(1L, "uma");
        testCases.put(2L, "duas");
        testCases.put(200L, "duzentas");
        testCases.put(300L, "trezentas");
        testCases.put(400L, "quatrocentas");
        testCases.put(500L, "quinhentas");
        testCases.put(600L, "seiscentas");
        testCases.put(700L, "setecentas");
        testCases.put(800L, "oitocentas");
        testCases.put(900L, "novecentas");
        testCases.put(1000L, "mil");
        testCases.put(1001L, "mil e uma");
        testCases.put(2002L, "duas mil e duas");
        testCases.put(202002002L, "duzentos e dois milhões duas mil e duas");
        testCases.put(500202002002L, "quinhentos bilhões duzentos e dois milhões duas mil e duas");

        NumberInWords<Long> cardinalNumber = new CardinalInPortuguese.Builder()
                .withFemaleGender()
                .build();

        testCases.forEach((number, expected) ->
            assertEquals(
                    expected,
                    cardinalNumber.inWords(number),
                    String.format("Retorno não esperado para o número %d.", number)));
    }

    @Test
    @DisplayName("inWords (Comma Separator)")
    void inWordsWithCommaSeparator() {
        NumberInWords<Long> cardinalNumber = new CardinalInPortuguese.Builder()
                .withCommaSeparator()
                .build();

        assertEquals(
                "cem milhões, cento e vinte e cinco mil e um",
                cardinalNumber.inWords(100125001L),
                "#1 retorno não esperado para o numero 100.125.001.");

        //não tem vírgula
        assertEquals(
                "cem milhões e vinte e cinco mil",
                cardinalNumber.inWords(100025000L),
                "#2 retorno não esperado para o numero 100.025.000.");
    }

    @Test
    @DisplayName("inWords (Negative Signal Description)")
    void inWordsWithNegativeSignalDescription() {
        NumberInWords<Long> cardinalNumber = new CardinalInPortuguese.Builder()
                .withNegativeSignalDescription("(negativo)")
                .build();

        assertEquals(
                "(negativo) um",
                cardinalNumber.inWords(-1L),
                "#1 retorno não esperado para o numero -1.");

        //não tem vírgula
        assertEquals(
                "um",
                cardinalNumber.inWords(1L),
                "#2 retorno não esperado para o numero 1.");
    }

    @Test
    @DisplayName("inWords (Positive Signal Description)")
    void inWordsWithPositiveSignalDescription() {
        NumberInWords<Long> cardinalNumber = new CardinalInPortuguese.Builder()
                .withPositiveSignalDescription("+++")
                .build();

        assertEquals(
                "+++ um",
                cardinalNumber.inWords(1L),
                "#1 retorno não esperado para o numero 1.");

        assertEquals(
                "menos um",
                cardinalNumber.inWords(-1L),
                "#2 retorno não esperado para o numero -1.");
    }

    @Test
    @DisplayName("inWords (Zero Description)")
    void inWordsWithZeroDescription() {
        NumberInWords<Long> cardinalNumber = new CardinalInPortuguese.Builder()
                .withZeroDescription("nenhum")
                .build();

        assertEquals(
                "nenhum",
                cardinalNumber.inWords(0L),
                "#1 retorno não esperado para o numero 0.");

    }
}