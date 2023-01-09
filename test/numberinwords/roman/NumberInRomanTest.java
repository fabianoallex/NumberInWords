package numberinwords.roman;

import numberinwords.CardinalInWords;
import numberinwords.portuguese.CardinalInPortuguese;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class NumberInRomanTest {
    @Test
    @DisplayName("inRoman")
    void inRoman() {
        Map<Integer, String> testCases = new HashMap<>();
        testCases.put(0, "");
        testCases.put(-1, "");
        testCases.put(1, "I");
        testCases.put(2, "II");
        testCases.put(3, "III");
        testCases.put(4, "IV");
        testCases.put(5, "V");
        testCases.put(6, "VI");
        testCases.put(7, "VII");
        testCases.put(8, "VIII");
        testCases.put(9, "IX");
        testCases.put(10, "X");
        testCases.put(11, "XI");
        testCases.put(12, "XII");
        testCases.put(13, "XIII");
        testCases.put(14, "XIV");
        testCases.put(15, "XV");
        testCases.put(16, "XVI");
        testCases.put(17, "XVII");
        testCases.put(18, "XVIII");
        testCases.put(19, "XIX");
        testCases.put(20, "XX");
        testCases.put(30, "XXX");
        testCases.put(40, "XL");
        testCases.put(50, "L");
        testCases.put(60, "LX");
        testCases.put(70, "LXX");
        testCases.put(80, "LXXX");
        testCases.put(90, "XC");
        testCases.put(100, "C");
        testCases.put(101, "CI");
        testCases.put(105, "CV");
        testCases.put(200, "CC");
        testCases.put(288, "CCLXXXVIII");
        testCases.put(309, "CCCIX");
        testCases.put(999, "CMXCIX");
        testCases.put(1002, "MII");
        testCases.put(2155, "MMCLV");
        testCases.put(2008, "MMVIII");
        testCases.put(2080, "MMLXXX");
        testCases.put(2800, "MMDCCC");
        testCases.put(3000, "MMM");
        testCases.put(4000, "MMMM");
        testCases.put(4100, "MMMMC");
        testCases.put(5000, "MMMMM");
        testCases.put(6000, "MMMMMM");
        testCases.put(10000, "MMMMMMMMMM");

        NumberInRoman numberInRoman = new NumberInRoman.Builder().build();
        testCases.forEach((number, expectedResult) ->
                assertEquals(
                        expectedResult,
                        numberInRoman.inRoman(number),
                        "retorno não esperado para o número " + number));
    }


    @Test
    @DisplayName("inRoman (AdditiveMethod)")
    void inRomanWithAdditiveMethod() {
        Map<Integer, String> testCases = new HashMap<>();
        testCases.put(0, "");
        testCases.put(-1, "");
        testCases.put(1, "I");
        testCases.put(2, "II");
        testCases.put(3, "III");
        testCases.put(4, "IIII");
        testCases.put(5, "V");
        testCases.put(6, "VI");
        testCases.put(7, "VII");
        testCases.put(8, "VIII");
        testCases.put(9, "VIIII");
        testCases.put(10, "X");
        testCases.put(11, "XI");
        testCases.put(12, "XII");
        testCases.put(13, "XIII");
        testCases.put(14, "XIIII");
        testCases.put(15, "XV");
        testCases.put(16, "XVI");
        testCases.put(17, "XVII");
        testCases.put(18, "XVIII");
        testCases.put(19, "XVIIII");
        testCases.put(20, "XX");
        testCases.put(30, "XXX");
        testCases.put(40, "XXXX");
        testCases.put(50, "L");
        testCases.put(60, "LX");
        testCases.put(70, "LXX");
        testCases.put(80, "LXXX");
        testCases.put(90, "LXXXX");
        testCases.put(100, "C");
        testCases.put(101, "CI");
        testCases.put(105, "CV");
        testCases.put(200, "CC");
        testCases.put(288, "CCLXXXVIII");
        testCases.put(309, "CCCVIIII");
        testCases.put(999, "DCCCCLXXXXVIIII");
        testCases.put(1002, "MII");
        testCases.put(2155, "MMCLV");
        testCases.put(2008, "MMVIII");
        testCases.put(2080, "MMLXXX");
        testCases.put(2800, "MMDCCC");
        testCases.put(3000, "MMM");
        testCases.put(4000, "MMMM");
        testCases.put(4100, "MMMMC");
        testCases.put(5000, "MMMMM");
        testCases.put(6000, "MMMMMM");
        testCases.put(10000, "MMMMMMMMMM");

        NumberInRoman numberInRoman = new NumberInRoman.Builder()
                .withAdditiveMethod()
                .build();

        testCases.forEach((number, expectedResult) ->
                assertEquals(
                        expectedResult,
                        numberInRoman.inRoman(number),
                        "retorno não esperado para o número " + number));
    }
}