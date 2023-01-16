package numberinwords.portuguese;

import numberinwords.DateInWords;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DateInPortugueseTest {
    @Test
    void inWords() {
        Map<LocalDate, String> testCases = new HashMap<>();
        testCases.put(LocalDate.of(2023, 12, 31), "trinta e um de dezembro de dois mil e vinte e três");
        testCases.put(LocalDate.of(1990, 1, 1), "primeiro de janeiro de mil novecentos e noventa");
        testCases.put(LocalDate.of(2000, 8, 21), "vinte e um de agosto de dois mil");

        DateInWords dateInWords = new DateInPortuguese.Builder()
                .build();

        testCases.forEach((date, expectedResult) ->
                assertEquals(
                        expectedResult,
                        dateInWords.inWords(date),
                        "retorno não esperado para a data " + date));
    }

    @Test
    @DisplayName("inWords (day and month)")
    void inWordsDayAndMonth() {
        Map<LocalDate, String> testCases = new HashMap<>();
        testCases.put(LocalDate.of(2023, 12, 31), "trinta e um de dezembro");
        testCases.put(LocalDate.of(1990, 1, 1), "primeiro de janeiro");
        testCases.put(LocalDate.of(2000, 8, 21), "vinte e um de agosto");

        DateInWords dateInWords = new DateInPortuguese.Builder()
                .usingDayAndMonth()
                .build();

        testCases.forEach((date, expectedResult) ->
                assertEquals(
                        expectedResult,
                        dateInWords.inWords(date),
                        "retorno não esperado para a data " + date));
    }

    @Test
    @DisplayName("month and year")
    void inWordsMonthAndYear() {
        Map<LocalDate, String> testCases = new HashMap<>();
        testCases.put(LocalDate.of(2023, 12, 31), "dezembro de dois mil e vinte e três");
        testCases.put(LocalDate.of(1990, 1, 1), "janeiro de mil novecentos e noventa");
        testCases.put(LocalDate.of(2000, 8, 21), "agosto de dois mil");

        DateInWords dateInWords = new DateInPortuguese.Builder()
                .usingMonthAndYear()
                .build();

        testCases.forEach((date, expectedResult) ->
                assertEquals(
                        expectedResult,
                        dateInWords.inWords(date),
                        "retorno não esperado para a data " + date));
    }
}