package numberinwords.english;

import numberinwords.TimeInWords;
import org.junit.jupiter.api.Test;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class TimeInEnglishTest {
    @Test
    void inWords() {
        Map<LocalTime, String> testCases = new HashMap<>();
        testCases.put(LocalTime.of(0, 0), "zero");
        testCases.put(LocalTime.of(0, 1), "zero one");
        testCases.put(LocalTime.of(0, 2), "zero two");
        testCases.put(LocalTime.of(10, 20), "ten twenty");
        testCases.put(LocalTime.of(12, 0), "twelve");
        testCases.put(LocalTime.of(13, 0), "one");
        testCases.put(LocalTime.of(13, 58), "one fifty-eight");
        testCases.put(LocalTime.of(10, 20, 22), "ten twenty");

        TimeInWords dateInWords = new TimeInEnglish.Builder()
                .build();

        testCases.forEach((time, expectedResult) ->
                assertEquals(
                        expectedResult,
                        dateInWords.inWords(time),
                        "retorno não esperado para a hora " + time));
    }

    @Test
    void inWordsWithMilitaryFormat() {
        Map<LocalTime, String> testCases = new HashMap<>();
        testCases.put(LocalTime.of(0, 0), "zero zero hundred hours");
        testCases.put(LocalTime.of(0, 1), "zero zero zero one hours");
        testCases.put(LocalTime.of(0, 2), "zero zero zero two hours");
        testCases.put(LocalTime.of(10, 20), "ten twenty hours");
        testCases.put(LocalTime.of(12, 0), "twelve hundred hours");
        testCases.put(LocalTime.of(13, 0), "thirteen hundred hours");
        testCases.put(LocalTime.of(13, 58), "thirteen fifty-eight hours");
        testCases.put(LocalTime.of(10, 20, 22), "ten twenty hours");

        TimeInWords dateInWords = new TimeInEnglish.Builder()
                .withMilitaryFormat()
                .build();

        testCases.forEach((time, expectedResult) ->
                assertEquals(
                        expectedResult,
                        dateInWords.inWords(time),
                        "retorno não esperado para a hora " + time));

        //teste 02
        testCases.clear();
        testCases.put(LocalTime.of(0, 0), "oh oh hundred");
        testCases.put(LocalTime.of(0, 1), "oh oh oh one");
        testCases.put(LocalTime.of(0, 2), "oh oh oh two");
        testCases.put(LocalTime.of(10, 20), "ten twenty");

        TimeInWords dateInWords2 = new TimeInEnglish.Builder()
                .withMilitaryFormat()
                .withOh()
                .build();

        testCases.forEach((time, expectedResult) ->
                assertEquals(
                        expectedResult,
                        dateInWords2.inWords(time),
                        "retorno não esperado para a hora " + time));
    }

    @Test
    void inWordsWithOhForMinutes() {
        Map<LocalTime, String> testCases = new HashMap<>();
        testCases.put(LocalTime.of(0, 0), "zero");
        testCases.put(LocalTime.of(0, 1), "zero oh one");
        testCases.put(LocalTime.of(0, 2), "zero oh two");
        testCases.put(LocalTime.of(10, 20), "ten twenty");
        testCases.put(LocalTime.of(10, 20, 22), "ten twenty");
        testCases.put(LocalTime.of(12, 0), "twelve");
        testCases.put(LocalTime.of(13, 0), "one");
        testCases.put(LocalTime.of(13, 58), "one fifty-eight");

        TimeInWords dateInWords = new TimeInEnglish.Builder()
                .withOh()
                .build();

        testCases.forEach((time, expectedResult) ->
                assertEquals(
                        expectedResult,
                        dateInWords.inWords(time),
                        "retorno não esperado para a hora " + time));

        //test 02
        testCases.clear();
        testCases.put(LocalTime.of(0, 1), "zero oh one");
        testCases.put(LocalTime.of(0, 2), "zero oh two");
        testCases.put(LocalTime.of(10, 5, 2), "ten oh five oh two AM");
        testCases.put(LocalTime.of(12, 9, 2), "noon oh nine oh two");
        testCases.put(LocalTime.of(13, 9, 2), "one oh nine oh two PM");

        TimeInWords dateInWords2 = new TimeInEnglish.Builder()
                .withOh()
                .withSeconds()
                .withAmPm()
                .withNoon()
                .build();

        testCases.forEach((time, expectedResult) ->
                assertEquals(
                        expectedResult,
                        dateInWords2.inWords(time),
                        "retorno não esperado para a hora " + time));


        //test 03
        testCases.clear();
        testCases.put(LocalTime.of(13, 9, 2), "thirteen nine");

        TimeInWords dateInWords3 = new TimeInEnglish.Builder()
                .withAmPm()
                .with24HoursFormat()
                .build();

        testCases.forEach((time, expectedResult) ->
                assertEquals(
                        expectedResult,
                        dateInWords3.inWords(time),
                        "retorno não esperado para a hora " + time));
    }

    @Test
    void inWordsWithQuarterAndHalf() {
        Map<LocalTime, String> testCases = new HashMap<>();
        testCases.put(LocalTime.of(0, 0), "zero");
        testCases.put(LocalTime.of(0, 15), "a quarter past zero");
        testCases.put(LocalTime.of(7, 45), "a quarter to eight");
        testCases.put(LocalTime.of(12, 5), "twelve five");
        testCases.put(LocalTime.of(12, 15), "a quarter past twelve");
        testCases.put(LocalTime.of(12, 30), "half past twelve");
        testCases.put(LocalTime.of(12, 35), "twelve thirty-five");
        testCases.put(LocalTime.of(12, 45), "a quarter to one");
        testCases.put(LocalTime.of(12, 55), "twelve fifty-five");
        testCases.put(LocalTime.of(13, 0), "one");

        TimeInWords dateInWords = new TimeInEnglish.Builder()
                .withQuarterAndHalf()
                .build();

        testCases.forEach((time, expectedResult) ->
                assertEquals(
                        expectedResult,
                        dateInWords.inWords(time),
                        "retorno não esperado para a hora " + time));

        //test 02
        testCases.clear();
        testCases.put(LocalTime.of(0, 15), "a quarter after zero");
        testCases.put(LocalTime.of(12, 45), "a quarter until one");

        TimeInWords dateInWords2 = new TimeInEnglish.Builder()
                .withQuarterAndHalf()
                .withAfterWordForPast()
                .withUntilWordForTo()
                .build();

        testCases.forEach((time, expectedResult) ->
                assertEquals(
                        expectedResult,
                        dateInWords2.inWords(time),
                        "retorno não esperado para a hora " + time));
    }

    @Test
    void inWordsWithPastAndToHours() {
        Map<LocalTime, String> testCases = new HashMap<>();
        testCases.put(LocalTime.of(0, 0), "zero");
        testCases.put(LocalTime.of(0, 15), "fifteen past zero");
        testCases.put(LocalTime.of(7, 45), "fifteen to eight");
        testCases.put(LocalTime.of(12, 5), "five past twelve");
        testCases.put(LocalTime.of(12, 15), "fifteen past twelve");
        testCases.put(LocalTime.of(12, 30), "thirty past twelve");
        testCases.put(LocalTime.of(12, 35), "twenty-five to one");
        testCases.put(LocalTime.of(12, 45), "fifteen to one");
        testCases.put(LocalTime.of(12, 55), "five to one");
        testCases.put(LocalTime.of(13, 0), "one");

        TimeInWords dateInWords = new TimeInEnglish.Builder()
                .withPastAndToHours()
                .build();

        testCases.forEach((time, expectedResult) ->
                assertEquals(
                        expectedResult,
                        dateInWords.inWords(time),
                        "retorno não esperado para a hora " + time));

        //test 02
        testCases.clear();
        testCases.put(LocalTime.of(0, 14), "fourteen after zero");
        testCases.put(LocalTime.of(12, 44), "sixteen until one");

        TimeInWords dateInWords2 = new TimeInEnglish.Builder()
                .withPastAndToHours()
                .withAfterWordForPast()
                .withUntilWordForTo()
                .build();

        testCases.forEach((time, expectedResult) ->
                assertEquals(
                        expectedResult,
                        dateInWords2.inWords(time),
                        "retorno não esperado para a hora " + time));
    }

    @Test
    void inWordsWithPastAndToHoursAndQuarterAndHalf() {
        Map<LocalTime, String> testCases = new HashMap<>();
        testCases.put(LocalTime.of(0, 0), "zero");
        testCases.put(LocalTime.of(0, 15), "a quarter past zero");
        testCases.put(LocalTime.of(7, 45), "a quarter to eight");
        testCases.put(LocalTime.of(12, 5), "five past twelve");
        testCases.put(LocalTime.of(12, 15), "a quarter past twelve");
        testCases.put(LocalTime.of(12, 30), "half past twelve");
        testCases.put(LocalTime.of(12, 35), "twenty-five to one");
        testCases.put(LocalTime.of(12, 45), "a quarter to one");
        testCases.put(LocalTime.of(12, 55), "five to one");
        testCases.put(LocalTime.of(13, 0), "one");

        TimeInWords dateInWords = new TimeInEnglish.Builder()
                .withPastAndToHours()
                .withQuarterAndHalf()
                .build();

        testCases.forEach((time, expectedResult) ->
                assertEquals(
                        expectedResult,
                        dateInWords.inWords(time),
                        "retorno não esperado para a hora " + time));
    }

    @Test
    void inWordsWithOClock() {
        Map<LocalTime, String> testCases = new HashMap<>();
        testCases.put(LocalTime.of(0, 0), "zero o'clock");
        testCases.put(LocalTime.of(0, 1), "zero one");
        testCases.put(LocalTime.of(0, 2), "zero two");
        testCases.put(LocalTime.of(10, 20), "ten twenty");
        testCases.put(LocalTime.of(10, 20, 22), "ten twenty");
        testCases.put(LocalTime.of(12, 0), "twelve o'clock");
        testCases.put(LocalTime.of(13, 0), "one o'clock");
        testCases.put(LocalTime.of(13, 30), "one thirty");
        testCases.put(LocalTime.of(13, 58), "one fifty-eight");

        TimeInWords dateInWords = new TimeInEnglish.Builder()
                .withOClock()
                .build();

        testCases.forEach((time, expectedResult) ->
                assertEquals(
                        expectedResult,
                        dateInWords.inWords(time),
                        "retorno não esperado para a hora " + time));

        //teste 02 - withMiddayAndMidnightPronuntiation
        testCases.clear();
        testCases.put(LocalTime.of(0, 0), "midnight");
        testCases.put(LocalTime.of(1, 0), "one o'clock");
        testCases.put(LocalTime.of(12, 0), "midday");
        testCases.put(LocalTime.of(12, 1), "midday one");
        TimeInWords dateInWords2 = new TimeInEnglish.Builder()
                .withOClock()
                .withMiddayAndMidnightPronuntiation()
                .build();

        testCases.forEach((time, expectedResult) ->
                assertEquals(
                        expectedResult,
                        dateInWords2.inWords(time),
                        "retorno não esperado para a hora " + time));

        //teste 03 - withNoon
        testCases.clear();
        testCases.put(LocalTime.of(0, 0), "zero o'clock");
        testCases.put(LocalTime.of(1, 0), "one o'clock");
        testCases.put(LocalTime.of(12, 0), "noon");
        testCases.put(LocalTime.of(12, 1), "noon one");
        TimeInWords dateInWords3 = new TimeInEnglish.Builder()
                .withOClock()
                .withNoon()
                .build();

        testCases.forEach((time, expectedResult) ->
                assertEquals(
                        expectedResult,
                        dateInWords3.inWords(time),
                        "retorno não esperado para a hora " + time));
    }

    @Test
    void inWordsWithPeriod() {
        Map<LocalTime, String> testCases = new HashMap<>();
        testCases.put(LocalTime.of(0, 0), "zero in the morning");
        testCases.put(LocalTime.of(0, 1), "zero one in the morning");
        testCases.put(LocalTime.of(0, 2), "zero two in the morning");
        testCases.put(LocalTime.of(10, 20), "ten twenty in the morning");
        testCases.put(LocalTime.of(11, 59), "eleven fifty-nine in the morning");
        testCases.put(LocalTime.of(12, 0), "twelve in the afternoon");
        testCases.put(LocalTime.of(13, 0), "one in the afternoon");
        testCases.put(LocalTime.of(13, 58), "one fifty-eight in the afternoon");
        testCases.put(LocalTime.of(16, 59), "four fifty-nine in the afternoon");
        testCases.put(LocalTime.of(17, 0), "five in the afternoon");
        testCases.put(LocalTime.of(17, 59), "five fifty-nine in the afternoon");
        testCases.put(LocalTime.of(18, 0), "six in the evening");
        testCases.put(LocalTime.of(20, 59), "eight fifty-nine in the evening");
        testCases.put(LocalTime.of(21, 0), "nine at night");
        testCases.put(LocalTime.of(23, 59), "eleven fifty-nine at night");

        TimeInWords dateInWords = new TimeInEnglish.Builder()
                .withPeriodPronuntiation()
                .build();

        testCases.forEach((time, expectedResult) ->
                assertEquals(
                        expectedResult,
                        dateInWords.inWords(time),
                        "retorno não esperado para a hora " + time));
    }

    @Test
    void inWordsWithMiddayAndMidnight() {
        Map<LocalTime, String> testCases = new HashMap<>();
        testCases.put(LocalTime.of(0, 0), "midnight");
        testCases.put(LocalTime.of(0, 1), "midnight one");
        testCases.put(LocalTime.of(0, 2), "midnight two");
        testCases.put(LocalTime.of(10, 20), "ten twenty");
        testCases.put(LocalTime.of(10, 20, 22), "ten twenty");
        testCases.put(LocalTime.of(12, 0), "midday");
        testCases.put(LocalTime.of(13, 0), "one");
        testCases.put(LocalTime.of(13, 58), "one fifty-eight");

        TimeInWords dateInWords = new TimeInEnglish.Builder()
                .withMiddayAndMidnightPronuntiation()
                .build();

        testCases.forEach((time, expectedResult) ->
                assertEquals(
                        expectedResult,
                        dateInWords.inWords(time),
                        "retorno não esperado para a hora " + time));


        //test 02
        testCases.clear();

        testCases.put(LocalTime.of(0, 14), "fourteen minutes past midnight");

        TimeInWords dateInWords2 = new TimeInEnglish.Builder()
                .withMiddayAndMidnightPronuntiation()
                .withUnits()
                .withPastAndToHours()
                .build();

        testCases.forEach((time, expectedResult) ->
                assertEquals(
                        expectedResult,
                        dateInWords2.inWords(time),
                        "retorno não esperado para a hora " + time));
    }

    @Test
    void inWordsWithNoon() {
        Map<LocalTime, String> testCases = new HashMap<>();
        testCases.put(LocalTime.of(0, 0), "zero");
        testCases.put(LocalTime.of(0, 1), "zero one");
        testCases.put(LocalTime.of(0, 2), "zero two");
        testCases.put(LocalTime.of(10, 20), "ten twenty");
        testCases.put(LocalTime.of(10, 20, 22), "ten twenty");
        testCases.put(LocalTime.of(12, 0), "noon");
        testCases.put(LocalTime.of(12, 1), "noon one");
        testCases.put(LocalTime.of(13, 0), "one");
        testCases.put(LocalTime.of(13, 58), "one fifty-eight");

        TimeInWords dateInWords = new TimeInEnglish.Builder()
                .withNoon()
                .build();

        testCases.forEach((time, expectedResult) ->
                assertEquals(
                        expectedResult,
                        dateInWords.inWords(time),
                        "retorno não esperado para a hora " + time));
    }

    @Test
    void inWordsWithNoonMiddayAndMidnight() {
        Map<LocalTime, String> testCases = new HashMap<>();
        testCases.put(LocalTime.of(0, 0), "midnight");
        testCases.put(LocalTime.of(0, 1), "midnight one");
        testCases.put(LocalTime.of(0, 2), "midnight two");
        testCases.put(LocalTime.of(10, 20), "ten twenty");
        testCases.put(LocalTime.of(10, 20, 22), "ten twenty");
        testCases.put(LocalTime.of(12, 0), "midday");
        testCases.put(LocalTime.of(12, 1), "midday one");
        testCases.put(LocalTime.of(13, 0), "one");
        testCases.put(LocalTime.of(13, 58), "one fifty-eight");

        TimeInWords dateInWords = new TimeInEnglish.Builder()
                .withNoon()
                .withMiddayAndMidnightPronuntiation()
                .build();

        testCases.forEach((time, expectedResult) ->
                assertEquals(
                        expectedResult,
                        dateInWords.inWords(time),
                        "retorno não esperado para a hora " + time));
    }

    @Test
    void inWordsWithUnits() {
        Map<LocalTime, String> testCases = new HashMap<>();
        testCases.put(LocalTime.of(0, 0), "zero hour");
        testCases.put(LocalTime.of(0, 1), "zero hour one minute");
        testCases.put(LocalTime.of(0, 2), "zero hour two minutes");
        testCases.put(LocalTime.of(10, 20), "ten hours twenty minutes");
        testCases.put(LocalTime.of(10, 20, 22), "ten hours twenty minutes");
        testCases.put(LocalTime.of(12, 0), "twelve hours");
        testCases.put(LocalTime.of(13, 0), "one hour");
        testCases.put(LocalTime.of(13, 58), "one hour fifty-eight minutes");

        TimeInWords dateInWords = new TimeInEnglish.Builder()
                .withUnits()
                .build();

        testCases.forEach((time, expectedResult) ->
                assertEquals(
                        expectedResult,
                        dateInWords.inWords(time),
                        "retorno não esperado para a hora " + time));
    }

    @Test
    void inWordsWithAmPm() {
        Map<LocalTime, String> testCases = new HashMap<>();
        testCases.put(LocalTime.of(0, 0), "zero");
        testCases.put(LocalTime.of(0, 1), "zero one");
        testCases.put(LocalTime.of(0, 2), "zero two");
        testCases.put(LocalTime.of(10, 20), "ten twenty AM");
        testCases.put(LocalTime.of(10, 20, 22), "ten twenty AM");
        testCases.put(LocalTime.of(12, 0), "twelve PM");
        testCases.put(LocalTime.of(13, 0), "one PM");
        testCases.put(LocalTime.of(13, 58), "one fifty-eight PM");

        TimeInWords dateInWords = new TimeInEnglish.Builder()
                .withAmPm()
                .build();

        testCases.forEach((time, expectedResult) ->
                assertEquals(
                        expectedResult,
                        dateInWords.inWords(time),
                        "retorno não esperado para a hora " + time));

        //teste 02 -
        testCases.clear();
        testCases.put(LocalTime.of(0, 0), "midnight");
        testCases.put(LocalTime.of(12, 0), "midday");

        TimeInWords dateInWords2 = new TimeInEnglish.Builder()
                .withMiddayAndMidnightPronuntiation()
                .withAmPm()
                .build();

        testCases.forEach((time, expectedResult) ->
                assertEquals(
                        expectedResult,
                        dateInWords2.inWords(time),
                        "retorno não esperado para a hora " + time));
    }

    @Test
    void inWordsUsingSeconds() {
        Map<LocalTime, String> testCases = new HashMap<>();
        testCases.put(LocalTime.of(0, 0), "zero");
        testCases.put(LocalTime.of(0, 0, 1), "zero and one second");
        testCases.put(LocalTime.of(0, 1, 20), "zero one and twenty seconds");
        testCases.put(LocalTime.of(10, 20, 22), "ten twenty and twenty-two seconds");
        testCases.put(LocalTime.of(12, 0, 1), "twelve and one second");
        testCases.put(LocalTime.of(13, 0), "one");
        testCases.put(LocalTime.of(13, 58, 59), "one fifty-eight and fifty-nine seconds");

        TimeInWords dateInWords = new TimeInEnglish.Builder()
                .withSeconds()
                .build();

        testCases.forEach((time, expectedResult) ->
                assertEquals(
                        expectedResult,
                        dateInWords.inWords(time),
                        "retorno não esperado para a hora " + time));

        //teste 2 - with am pm
        testCases.clear();
        testCases.put(LocalTime.of(13, 58, 59), "one fifty-eight and fifty-nine seconds PM");

        TimeInWords dateInWords2 = new TimeInEnglish.Builder()
                .withAmPm()
                .withSeconds()
                .build();

        testCases.forEach((time, expectedResult) ->
                assertEquals(
                        expectedResult,
                        dateInWords2.inWords(time),
                        "retorno não esperado para a hora " + time));

    }
}