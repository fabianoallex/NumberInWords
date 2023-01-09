package numberinwords.portuguese;

import numberinwords.CardinalInWords;
import numberinwords.NumberInWords;
import numberinwords.OrdinalInWords;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class OrdinalInPortugueseTest {
    @Test
    @DisplayName("inWords")
    void inWords() {
        Map<Long, String> testCases = new HashMap<>();
        testCases.put(0L, ""); //zero
        testCases.put(-1L, ""); //negativo
        testCases.put(1L, "primeiro");
        testCases.put(2L, "segundo");
        testCases.put(3L, "terceiro");
        testCases.put(4L, "quarto");
        testCases.put(5L, "quinto");
        testCases.put(10L, "décimo");
        testCases.put(11L, "décimo primeiro");
        testCases.put(12L, "décimo segundo");
        testCases.put(13L, "décimo terceiro");
        testCases.put(14L, "décimo quarto");
        testCases.put(15L, "décimo quinto");
        testCases.put(16L, "décimo sexto");
        testCases.put(17L, "décimo sétimo");
        testCases.put(18L, "décimo oitavo");
        testCases.put(19L, "décimo nono");
        testCases.put(21L, "vigésimo primeiro");
        testCases.put(29L, "vigésimo nono");
        testCases.put(100L, "centésimo");
        testCases.put(101L, "centésimo primeiro");
        testCases.put(748L, "septingentésimo quadragésimo oitavo");
        testCases.put(1000L, "milésimo");
        testCases.put(1030L, "milésimo trigésimo");
        testCases.put(1530L, "milésimo quingentésimo trigésimo");
        testCases.put(1536L, "milésimo quingentésimo trigésimo sexto");
        testCases.put(2000L, "segundo milésimo");
        testCases.put(2536L, "segundo milésimo quingentésimo trigésimo sexto");
        testCases.put(10000L, "décimo milésimo");
        testCases.put(1000000L, "milionésimo");
        testCases.put(2000000L, "segundo milionésimo");
        testCases.put(1000000000L, "bilionésimo");
        testCases.put(1000002536L, "bilionésimo segundo milésimo quingentésimo trigésimo sexto");
        testCases.put(1000000000000L, "trilionésimo");
        testCases.put(1000000000000000L, "quatrilionésimo");
        testCases.put(1000000000000000000L, "quintilionésimo");

        OrdinalInWords ordinalNumber = new OrdinalInPortuguese.Builder().build();
        testCases.forEach((number, expectedResult) ->
                assertEquals(
                        expectedResult,
                        ordinalNumber.inWords(number),
                        "retorno não esperado para o numero " + number));
    }

    @Test
    @DisplayName("inWords (Female Gender)")
    void inWordsWithFemaleGender() {
        Map<Long, String> testCases = new HashMap<>();
        testCases.put(0L, ""); //zero
        testCases.put(-1L, ""); //negativo
        testCases.put(1L, "primeira");
        testCases.put(2L, "segunda");
        testCases.put(3L, "terceira");
        testCases.put(4L, "quarta");
        testCases.put(5L, "quinta");
        testCases.put(10L, "décima");
        testCases.put(11L, "décima primeira");
        testCases.put(12L, "décima segunda");
        testCases.put(13L, "décima terceira");
        testCases.put(14L, "décima quarta");
        testCases.put(15L, "décima quinta");
        testCases.put(16L, "décima sexta");
        testCases.put(17L, "décima sétima");
        testCases.put(18L, "décima oitava");
        testCases.put(19L, "décima nona");
        testCases.put(21L, "vigésima primeira");
        testCases.put(29L, "vigésima nona");
        testCases.put(100L, "centésima");
        testCases.put(101L, "centésima primeira");
        testCases.put(748L, "septingentésima quadragésima oitava");
        testCases.put(1000L, "milésima");
        testCases.put(1030L, "milésima trigésima");
        testCases.put(1530L, "milésima quingentésima trigésima");
        testCases.put(1536L, "milésima quingentésima trigésima sexta");
        testCases.put(2000L, "segunda milésima");
        testCases.put(2536L, "segunda milésima quingentésima trigésima sexta");
        testCases.put(10000L, "décima milésima");
        testCases.put(1000000L, "milionésima");
        testCases.put(2000000L, "segunda milionésima");
        testCases.put(1000000000L, "bilionésima");
        testCases.put(1000002536L, "bilionésima segunda milésima quingentésima trigésima sexta");
        testCases.put(1000000000000L, "trilionésima");
        testCases.put(1000000000000000L, "quatrilionésima");
        testCases.put(1000000000000000000L, "quintilionésima");

        OrdinalInWords ordinalNumber = new OrdinalInPortuguese.Builder()
                .withFemaleGender()
                .build();

        testCases.forEach((number, expectedResult) ->
                assertEquals(
                        expectedResult,
                        ordinalNumber.inWords(number),
                        "retorno não esperado para o numero " + number));
    }

    @Test
    @DisplayName("inWords (Comma Separator)")
    void inWordsWithCommaSeparator() {
        Map<Long, String> testCases = new HashMap<>();
        testCases.put(0L, ""); //zero
        testCases.put(-1L, ""); //negativo
        testCases.put(1L, "primeira");
        testCases.put(2L, "segunda");
        testCases.put(3L, "terceira");
        testCases.put(4L, "quarta");
        testCases.put(5L, "quinta");
        testCases.put(10L, "décima");
        testCases.put(11L, "décima primeira");
        testCases.put(12L, "décima segunda");
        testCases.put(13L, "décima terceira");
        testCases.put(14L, "décima quarta");
        testCases.put(15L, "décima quinta");
        testCases.put(16L, "décima sexta");
        testCases.put(17L, "décima sétima");
        testCases.put(18L, "décima oitava");
        testCases.put(19L, "décima nona");
        testCases.put(21L, "vigésima primeira");
        testCases.put(29L, "vigésima nona");
        testCases.put(100L, "centésima");
        testCases.put(101L, "centésima primeira");
        testCases.put(748L, "septingentésima quadragésima oitava");
        testCases.put(1000L, "milésima");
        testCases.put(1030L, "milésima, trigésima");
        testCases.put(1530L, "milésima, quingentésima trigésima");
        testCases.put(1536L, "milésima, quingentésima trigésima sexta");
        testCases.put(2000L, "segunda milésima");
        testCases.put(2536L, "segunda milésima, quingentésima trigésima sexta");
        testCases.put(10000L, "décima milésima");
        testCases.put(1000000L, "milionésima");
        testCases.put(2000000L, "segunda milionésima");
        testCases.put(1000000000L, "bilionésima");
        testCases.put(1000002536L, "bilionésima, segunda milésima, quingentésima trigésima sexta");
        testCases.put(1000000000000L, "trilionésima");
        testCases.put(1000000000000000L, "quatrilionésima");
        testCases.put(1000000000000000000L, "quintilionésima");

        OrdinalInWords ordinalNumber = new OrdinalInPortuguese.Builder()
                .withFemaleGender()
                .withCommaSeparator()
                .build();

        testCases.forEach((number, expectedResult) ->
                assertEquals(
                        expectedResult,
                        ordinalNumber.inWords(number),
                        "retorno não esperado para o numero " + number));
    }
}