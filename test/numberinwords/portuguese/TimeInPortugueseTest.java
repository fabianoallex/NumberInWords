package numberinwords.portuguese;

import numberinwords.TimeInWords;
import org.junit.jupiter.api.Test;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TimeInPortugueseTest {
    @Test
    void inWords() {
        Map<LocalTime, String> testCases = new HashMap<>();
        testCases.put(LocalTime.of(0, 0), "zero hora");
        testCases.put(LocalTime.of(0, 1), "zero hora e um minuto");
        testCases.put(LocalTime.of(0, 2), "zero hora e dois minutos");
        testCases.put(LocalTime.of(10, 20), "dez horas e vinte minutos");
        testCases.put(LocalTime.of(10, 20, 22), "dez horas e vinte minutos");
        testCases.put(LocalTime.of(12, 0), "doze horas");
        testCases.put(LocalTime.of(13, 0), "treze horas");
        testCases.put(LocalTime.of(13, 58), "treze horas e cinquenta e oito minutos");
        testCases.put(LocalTime.of(10, 20, 33), "dez horas e vinte minutos"); //nao mostra segundos por padrão

        TimeInWords dateInWords = new TimeInPortuguese.Builder()
                .build();

        testCases.forEach((time, expectedResult) ->
                assertEquals(
                        expectedResult,
                        dateInWords.inWords(time),
                        "retorno não esperado para a data " + time));
    }

    @Test
    void inWordsWithMiddayAndMidnightPronuntiation() {
        Map<LocalTime, String> testCases = new HashMap<>();
        testCases.put(LocalTime.of(0, 0), "meia-noite");
        testCases.put(LocalTime.of(0, 1), "meia-noite e um minuto");
        testCases.put(LocalTime.of(0, 2), "meia-noite e dois minutos");
        testCases.put(LocalTime.of(10, 20), "dez horas e vinte minutos");
        testCases.put(LocalTime.of(10, 20, 22), "dez horas e vinte minutos");
        testCases.put(LocalTime.of(12, 0), "meio-dia");
        testCases.put(LocalTime.of(12, 30), "meio-dia e trinta minutos");
        testCases.put(LocalTime.of(13, 0), "treze horas");
        testCases.put(LocalTime.of(13, 58), "treze horas e cinquenta e oito minutos");
        testCases.put(LocalTime.of(10, 20, 33), "dez horas e vinte minutos"); //nao mostra segundos por padrão

        TimeInWords dateInWords = new TimeInPortuguese.Builder()
                .withMiddayAndMidnightPronuntiation()
                .build();

        testCases.forEach((time, expectedResult) ->
                assertEquals(
                        expectedResult,
                        dateInWords.inWords(time),
                        "retorno não esperado para a data " + time));
    }

    @Test
    void inWordsWithMiddayAndMidnightAndInformalPronuntiation() {
        Map<LocalTime, String> testCases = new HashMap<>();
        testCases.put(LocalTime.of(0, 0), "meia-noite");
        testCases.put(LocalTime.of(0, 1), "meia-noite e um");
        testCases.put(LocalTime.of(0, 2), "meia-noite e dois");
        testCases.put(LocalTime.of(10, 20), "dez e vinte");
        testCases.put(LocalTime.of(10, 20, 22), "dez e vinte");
        testCases.put(LocalTime.of(12, 0), "meio-dia");
        testCases.put(LocalTime.of(12, 30), "meio-dia e trinta");
        testCases.put(LocalTime.of(13, 0), "treze horas");
        testCases.put(LocalTime.of(13, 58), "treze e cinquenta e oito");
        testCases.put(LocalTime.of(10, 20, 33), "dez e vinte"); //nao mostra segundos por padrão

        TimeInWords dateInWords = new TimeInPortuguese.Builder()
                .withInformalPronuntiation()
                .withMiddayAndMidnightPronuntiation()
                .build();

        testCases.forEach((time, expectedResult) ->
                assertEquals(
                        expectedResult,
                        dateInWords.inWords(time),
                        "retorno não esperado para a data " + time));
    }

    @Test
    void inWordsForInformalPronuntiation() {
        Map<LocalTime, String> testCases = new HashMap<>();
        testCases.put(LocalTime.of(0, 0), "zero hora");
        testCases.put(LocalTime.of(0, 1), "zero hora e um minuto");
        testCases.put(LocalTime.of(0, 2), "zero hora e dois minutos");
        testCases.put(LocalTime.of(10, 20, 22), "dez e vinte");
        testCases.put(LocalTime.of(12, 0), "doze horas");
        testCases.put(LocalTime.of(13, 0), "treze horas");
        testCases.put(LocalTime.of(13, 58), "treze e cinquenta e oito");
        testCases.put(LocalTime.of(10, 20, 33), "dez e vinte");
        testCases.put(LocalTime.of(23, 59, 33), "vinte e três e cinquenta e nove");

        TimeInWords dateInWords = new TimeInPortuguese.Builder()
                .withInformalPronuntiation()
                .build();

        testCases.forEach((time, expectedResult) ->
                assertEquals(
                        expectedResult,
                        dateInWords.inWords(time),
                        "retorno não esperado para a data " + time));
    }

    @Test
    void inWordsUsingSeconds() {
        Map<LocalTime, String> testCases = new HashMap<>();
        testCases.put(LocalTime.of(1, 20), "uma hora e vinte minutos");
        testCases.put(LocalTime.of(10, 20), "dez horas e vinte minutos");
        testCases.put(LocalTime.of(10, 20, 1), "dez horas e vinte minutos e um segundo");
        testCases.put(LocalTime.of(10, 20, 33), "dez horas e vinte minutos e trinta e três segundos");
        testCases.put(LocalTime.of(0, 0), "zero hora");
        testCases.put(LocalTime.of(0, 0, 59), "zero hora e cinquenta e nove segundos");

        TimeInWords dateInWords = new TimeInPortuguese.Builder()
                .usingSeconds()
                .build();

        testCases.forEach((time, expectedResult) ->
                assertEquals(
                        expectedResult,
                        dateInWords.inWords(time),
                        "retorno não esperado para a data " + time));
    }

    @Test
    void inWordsUsingSecondsAnd12HoursFormat() {
        Map<LocalTime, String> testCases = new HashMap<>();
        testCases.put(LocalTime.of(1, 20), "uma hora e vinte minutos");
        testCases.put(LocalTime.of(13, 20), "uma hora e vinte minutos");
        testCases.put(LocalTime.of(10, 20), "dez horas e vinte minutos");
        testCases.put(LocalTime.of(22, 20), "dez horas e vinte minutos");
        testCases.put(LocalTime.of(10, 20, 1), "dez horas e vinte minutos e um segundo");
        testCases.put(LocalTime.of(10, 20, 33), "dez horas e vinte minutos e trinta e três segundos");
        testCases.put(LocalTime.of(22, 20, 33), "dez horas e vinte minutos e trinta e três segundos");
        testCases.put(LocalTime.of(0, 0), "zero hora");
        testCases.put(LocalTime.of(12, 0), "doze horas");
        testCases.put(LocalTime.of(12, 30), "doze horas e trinta minutos");
        testCases.put(LocalTime.of(0, 0, 59), "zero hora e cinquenta e nove segundos");

        TimeInWords dateInWords = new TimeInPortuguese.Builder()
                .usingSeconds()
                .with12HoursFormat()
                .build();

        testCases.forEach((time, expectedResult) ->
                assertEquals(
                        expectedResult,
                        dateInWords.inWords(time),
                        "retorno não esperado para a data " + time));
    }
}