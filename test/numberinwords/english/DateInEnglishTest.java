package numberinwords.english;

import jdk.jfr.Description;
import numberinwords.DateInWords;
import numberinwords.portuguese.DateInPortuguese;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DateInEnglishTest {
    @Test
    void inWords() {
        Map<LocalDate, String> testCases = new HashMap<>();
        testCases.put(LocalDate.of(2023, 12, 31), "thirty-first December two thousand and twenty-three");
        testCases.put(LocalDate.of(1990, 1, 1), "first January one thousand nine hundred ninety");
        testCases.put(LocalDate.of(2000, 8, 21), "twenty-first August two thousand");

        DateInWords dateInWords = new DateInEnglish.Builder()
                .build();

        testCases.forEach((date, expectedResult) ->
                assertEquals(
                        expectedResult,
                        dateInWords.inWords(date),
                        "retorno não esperado para a data " + date));
    }

    @Test
    @DisplayName("inWords ('of' after day)")
    void inWordsWithOfAfterDay() {
        Map<LocalDate, String> testCases = new HashMap<>();
        testCases.put(LocalDate.of(2023, 12, 31), "thirty-first of December two thousand and twenty-three");
        testCases.put(LocalDate.of(1990, 1, 1), "first of January one thousand nine hundred ninety");
        testCases.put(LocalDate.of(2000, 8, 21), "twenty-first of August two thousand");

        DateInWords dateInWords = new DateInEnglish.Builder()
                .withOfAfterDay()
                .build();

        testCases.forEach((date, expectedResult) ->
                assertEquals(
                        expectedResult,
                        dateInWords.inWords(date),
                        "retorno não esperado para a data " + date));
    }

    @Test
    @DisplayName("inWords (Cardinal for day)")
    void inWordsWithCardinalForDay() {
        Map<LocalDate, String> testCases = new HashMap<>();
        testCases.put(LocalDate.of(2023, 12, 31), "thirty-one of December two thousand and twenty-three");
        testCases.put(LocalDate.of(1990, 1, 1), "one of January one thousand nine hundred ninety");
        testCases.put(LocalDate.of(2000, 8, 21), "twenty-one of August two thousand");

        DateInWords dateInWords = new DateInEnglish.Builder()
                .withOfAfterDay()
                .withCardinalForDay()
                .build();

        testCases.forEach((date, expectedResult) ->
                assertEquals(
                        expectedResult,
                        dateInWords.inWords(date),
                        "retorno não esperado para a data " + date));
    }


    @Test
    @DisplayName("inWords (Using 'the' before day)")
    void inWordsWithTheBeforDay() {
        Map<LocalDate, String> testCases = new HashMap<>();
        testCases.put(LocalDate.of(2023, 12, 31), "the thirty-first of December two thousand and twenty-three");
        testCases.put(LocalDate.of(1990, 1, 1), "the first of January one thousand nine hundred ninety");
        testCases.put(LocalDate.of(2000, 8, 21), "the twenty-first of August two thousand");

        DateInWords dateInWords = new DateInEnglish.Builder()
                .withTheBeforeDay()
                .withOfAfterDay()
                .build();

        testCases.forEach((date, expectedResult) ->
                assertEquals(
                        expectedResult,
                        dateInWords.inWords(date),
                        "retorno não esperado para a data " + date));
    }

    @Test
    @DisplayName("inWords (Month First)")
    void inWordsWithMonthFirst() {
        Map<LocalDate, String> testCases = new HashMap<>();
        testCases.put(LocalDate.of(2023, 12, 31), "December thirty-first two thousand and twenty-three");
        testCases.put(LocalDate.of(1990, 1, 1), "January first one thousand nine hundred ninety");
        testCases.put(LocalDate.of(2000, 8, 21), "August twenty-first two thousand");

        DateInWords dateInWords = new DateInEnglish.Builder()
                .withMonthFirst()
                .build();

        testCases.forEach((date, expectedResult) ->
                assertEquals(
                        expectedResult,
                        dateInWords.inWords(date),
                        "retorno não esperado para a data " + date));
    }
}