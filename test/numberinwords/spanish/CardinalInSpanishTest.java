package numberinwords.spanish;

import numberinwords.NumberInWords;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CardinalInSpanishTest {

    @Test
    @DisplayName("inWords")
    void inWords() {
        Map<Long, String> testCases = new HashMap<>();
        testCases.put(0L, "cero");
        testCases.put(1L, "uno");
        testCases.put(-1L, "menos uno");
        testCases.put(10L, "diez");
        testCases.put(11L, "once");
        testCases.put(20L, "veinte");
        testCases.put(100L, "cien");
        testCases.put(101L, "ciento uno");
        testCases.put(106L, "ciento seis");
        testCases.put(202L, "doscientos dos");
        testCases.put(220L, "doscientos veinte");
        testCases.put(349L, "trescientos cuarenta y nueve");
        testCases.put(1000L, "mil");
        testCases.put(1017L, "mil y diecisiete");
        testCases.put(1050L, "mil y cincuenta");
        testCases.put(1500L, "mil quinientos");
        testCases.put(2099L, "dos mil y noventa y nueve");
        testCases.put(7099L, "siete mil y noventa y nueve");
        testCases.put(30000L, "treinta mil");
        testCases.put(80009L, "ochenta mil y nueve");
        testCases.put(100000L, "cien mil");
        testCases.put(482000L, "cuatrocientos ochenta y dos mil");
        testCases.put(1000000L, "un millón");
        testCases.put(1500000L, "un millón quinientos mil");
        testCases.put(50000200L, "cincuenta millones doscientos");
        testCases.put(60933300L, "sesenta millones novecientos treinta y tres mil trescientos");
        testCases.put(1000000000L, "un billón");
        testCases.put(2000000000L, "dos billones");
        testCases.put(20000000000L, "veinte billones");
        testCases.put(30000000000L, "treinta billones");
        testCases.put(60000000000L, "sesenta billones");
        testCases.put(90000000000L, "noventa billones");
        testCases.put(100000000000L, "cien billones");
        testCases.put(200000000000L, "doscientos billones");
        testCases.put(300000000000L, "trescientos billones");
        testCases.put(600000000000L, "seiscientos billones");
        testCases.put(900000000000L, "novecientos billones");
        testCases.put(1000000000000L, "un trillón");
        testCases.put(10000000000000L, "diez trillones");
        testCases.put(100000000000000L, "cien trillones");
        testCases.put(1000000000000000L, "un cuatrillón");
        testCases.put(10000000000000000L, "diez cuatrillones");
        testCases.put(100000000000000000L, "cien cuatrillones");
        testCases.put(1000000000000000000L, "un quintillón");
        testCases.put(-1000000000000000000L, "menos un quintillón");
        testCases.put(Long.MAX_VALUE,
                "nueve quintillones doscientos veintitrés cuatrillones trescientos setenta y" +
                        " dos trillones treinta y seis billones ochocientos cincuenta y cuatro " +
                        "millones setecientos setenta y cinco mil ochocientos siete");

        //testCases.put(10000000000000000000L, "dez quintilhões"); //não pode ser testado, pois valor maior que Long.MAX_VALUE

        NumberInWords<Long> cardinalNumber = new CardinalInSpanish.Builder().build();
        testCases.forEach((number, expectedResult) ->
                assertEquals(
                        expectedResult,
                        cardinalNumber.inWords(number),
                        "retorno não esperado para o numero " + number));
    }
}