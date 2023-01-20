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
    void inWordsWithPeriodPronuntiation() {
        //teste 01
        Map<LocalTime, String> testCases = new HashMap<>();
        testCases.put(LocalTime.of(0, 0), "zero hora da madrugada");
        testCases.put(LocalTime.of(0, 1), "zero hora e um minuto da madrugada");
        testCases.put(LocalTime.of(0, 2), "zero hora e dois minutos da madrugada");
        testCases.put(LocalTime.of(10, 20, 22), "dez horas e vinte minutos da manhã");
        testCases.put(LocalTime.of(5, 59), "cinco horas e cinquenta e nove minutos da madrugada");
        testCases.put(LocalTime.of(6, 0), "seis horas da manhã");
        testCases.put(LocalTime.of(12, 0), "doze horas da tarde");
        testCases.put(LocalTime.of(13, 0), "treze horas da tarde");
        testCases.put(LocalTime.of(13, 58), "treze horas e cinquenta e oito minutos da tarde");
        testCases.put(LocalTime.of(23, 59), "vinte e três horas e cinquenta e nove minutos da noite");
        testCases.put(LocalTime.of(22, 59), "vinte e duas horas e cinquenta e nove minutos da noite");

        TimeInWords dateInWords1 = new TimeInPortuguese.Builder()
                .withPeriodPronuntiation()
                .build();

        testCases.forEach((time, expectedResult) ->
                assertEquals(
                        expectedResult,
                        dateInWords1.inWords(time),
                        "retorno não esperado para a hora " + time));

        //teste 02
        testCases.clear();
        testCases.put(LocalTime.of(0, 0), "zero hora da madrugada");
        testCases.put(LocalTime.of(0, 1), "zero hora e um minuto da madrugada");
        testCases.put(LocalTime.of(0, 2), "zero hora e dois minutos da madrugada");
        testCases.put(LocalTime.of(10, 20, 22), "dez horas e vinte minutos da manhã");
        testCases.put(LocalTime.of(5, 59), "cinco horas e cinquenta e nove minutos da madrugada");
        testCases.put(LocalTime.of(6, 0), "seis horas da manhã");
        testCases.put(LocalTime.of(12, 0), "doze horas da tarde");
        testCases.put(LocalTime.of(13, 0), "uma hora da tarde");
        testCases.put(LocalTime.of(13, 58), "uma hora e cinquenta e oito minutos da tarde");
        testCases.put(LocalTime.of(23, 59), "onze horas e cinquenta e nove minutos da noite");
        testCases.put(LocalTime.of(22, 59), "dez horas e cinquenta e nove minutos da noite");

        TimeInWords dateInWords2 = new TimeInPortuguese.Builder()
                .withPeriodPronuntiation()
                .with12HoursFormat()
                .build();

        testCases.forEach((time, expectedResult) ->
                assertEquals(
                        expectedResult,
                        dateInWords2.inWords(time),
                        "retorno não esperado para a hora " + time));


        //teste 03
        testCases.clear();
        testCases.put(LocalTime.of(0, 0), "zero hora da madrugada");
        testCases.put(LocalTime.of(0, 1), "zero hora e um minuto da madrugada");
        testCases.put(LocalTime.of(0, 2), "zero hora e dois minutos da madrugada");
        testCases.put(LocalTime.of(10, 20, 22), "dez horas e vinte minutos da manhã");
        testCases.put(LocalTime.of(5, 59), "um minuto para às seis horas da manhã");
        testCases.put(LocalTime.of(4, 59), "um minuto para às cinco horas da madrugada");
        testCases.put(LocalTime.of(6, 0), "seis horas da manhã");
        testCases.put(LocalTime.of(12, 0), "doze horas da tarde");
        testCases.put(LocalTime.of(13, 0), "uma hora da tarde");
        testCases.put(LocalTime.of(13, 58), "dois minutos para às duas horas da tarde");
        testCases.put(LocalTime.of(23, 59), "um minuto para às doze horas da madrugada");
        testCases.put(LocalTime.of(22, 59), "um minuto para às onze horas da noite");

        TimeInWords dateInWords3 = new TimeInPortuguese.Builder()
                .withPeriodPronuntiation()
                .with12HoursFormat()
                .withMinuteToHourPronuntiation()
                .build();

        testCases.forEach((time, expectedResult) ->
                assertEquals(
                        expectedResult,
                        dateInWords3.inWords(time),
                        "retorno não esperado para a hora " + time));


        //teste 04
        testCases.clear();
        testCases.put(LocalTime.of(0, 0), "meia-noite");
        testCases.put(LocalTime.of(0, 1), "meia-noite e um minuto");
        testCases.put(LocalTime.of(0, 2), "meia-noite e dois minutos");
        testCases.put(LocalTime.of(10, 20, 22), "dez horas e vinte minutos da manhã");
        testCases.put(LocalTime.of(5, 59), "um minuto para às seis horas da manhã");
        testCases.put(LocalTime.of(4, 59), "um minuto para às cinco horas da madrugada");
        testCases.put(LocalTime.of(6, 0), "seis horas da manhã");
        testCases.put(LocalTime.of(12, 0), "meio-dia");
        testCases.put(LocalTime.of(11, 59), "um minuto para o meio-dia");
        testCases.put(LocalTime.of(13, 0), "treze horas da tarde");
        testCases.put(LocalTime.of(13, 58), "dois minutos para às quatorze horas da tarde");
        testCases.put(LocalTime.of(23, 59), "um minuto para meia-noite");
        testCases.put(LocalTime.of(22, 59), "um minuto para às vinte e três horas da noite");

        TimeInWords dateInWords4 = new TimeInPortuguese.Builder()
                .withPeriodPronuntiation()
                //.with12HoursFormat()
                .withMinuteToHourPronuntiation()
                .withMiddayAndMidnightPronuntiation()
                .build();

        testCases.forEach((time, expectedResult) ->
                assertEquals(
                        expectedResult,
                        dateInWords4.inWords(time),
                        "retorno não esperado para a hora " + time));


        //teste 05
        testCases.clear();
        testCases.put(LocalTime.of(0, 0), "meia-noite");
        testCases.put(LocalTime.of(0, 1), "meia-noite e um minuto");
        testCases.put(LocalTime.of(0, 2), "meia-noite e dois minutos");
        testCases.put(LocalTime.of(10, 20, 22), "dez horas e vinte minutos da manhã");
        testCases.put(LocalTime.of(5, 59), "um minuto para às seis horas da manhã");
        testCases.put(LocalTime.of(4, 59), "um minuto para às cinco horas da madrugada");
        testCases.put(LocalTime.of(6, 0), "seis horas da manhã");
        testCases.put(LocalTime.of(12, 0), "meio-dia");
        testCases.put(LocalTime.of(11, 59), "um minuto para o meio-dia");
        testCases.put(LocalTime.of(13, 0), "uma hora da tarde");
        testCases.put(LocalTime.of(13, 58), "dois minutos para às duas horas da tarde");
        testCases.put(LocalTime.of(23, 59), "um minuto para meia-noite");
        testCases.put(LocalTime.of(22, 59), "um minuto para às onze horas da noite");

        TimeInWords dateInWords5 = new TimeInPortuguese.Builder()
                .withPeriodPronuntiation()
                .with12HoursFormat()
                .withMinuteToHourPronuntiation()
                .withMiddayAndMidnightPronuntiation()
                .build();

        testCases.forEach((time, expectedResult) ->
                assertEquals(
                        expectedResult,
                        dateInWords5.inWords(time),
                        "retorno não esperado para a hora " + time));
    }

    @Test
    void inWordsWithMinutesToHourPronuntiation() {
        Map<LocalTime, String> testCases = new HashMap<>();
        testCases.put(LocalTime.of(0, 45), "quinze minutos para uma hora");
        testCases.put(LocalTime.of(0, 35), "zero hora e trinta e cinco minutos");
        testCases.put(LocalTime.of(0, 59), "um minuto para uma hora");
        testCases.put(LocalTime.of(10, 40), "vinte minutos para às onze horas");
        testCases.put(LocalTime.of(10, 19, 22), "dez horas e dezenove minutos");
        testCases.put(LocalTime.of(12, 45), "quinze minutos para às treze horas");
        testCases.put(LocalTime.of(13, 42), "dezoito minutos para às quatorze horas");
        testCases.put(LocalTime.of(13, 58), "dois minutos para às quatorze horas");
        testCases.put(LocalTime.of(10, 50, 33), "dez minutos para às onze horas"); //nao mostra segundos por padrão

        TimeInWords dateInWords1 = new TimeInPortuguese.Builder()
                .withMinuteToHourPronuntiation()
                .build();

        testCases.forEach((time, expectedResult) ->
                assertEquals(
                        expectedResult,
                        dateInWords1.inWords(time),
                        "retorno não esperado para a hora " + time));

        //teste 2
        testCases.clear();
        testCases.put(LocalTime.of(0, 45), "quinze minutos para uma hora");
        testCases.put(LocalTime.of(0, 35), "zero hora e trinta e cinco minutos");
        testCases.put(LocalTime.of(0, 59), "um minuto para uma hora");
        testCases.put(LocalTime.of(10, 40), "vinte minutos para às onze horas");
        testCases.put(LocalTime.of(10, 19, 22), "dez horas e dezenove minutos");
        testCases.put(LocalTime.of(12, 45), "quinze minutos para uma hora");
        testCases.put(LocalTime.of(13, 42), "dezoito minutos para às duas horas");
        testCases.put(LocalTime.of(13, 58), "dois minutos para às duas horas");
        testCases.put(LocalTime.of(10, 50, 33), "dez minutos para às onze horas"); //nao mostra segundos por padrão

        TimeInWords dateInWords2 = new TimeInPortuguese.Builder()
                .withMinuteToHourPronuntiation()
                .with12HoursFormat()
                .build();

        testCases.forEach((time, expectedResult) ->
                assertEquals(
                        expectedResult,
                        dateInWords2.inWords(time),
                        "retorno não esperado para a hora " + time));


        //teste 3
        testCases.clear();
        testCases.put(LocalTime.of(0, 45), "quinze minutos para uma hora");
        testCases.put(LocalTime.of(0, 35), "meia-noite e trinta e cinco minutos");
        testCases.put(LocalTime.of(23, 59), "um minuto para meia-noite");
        testCases.put(LocalTime.of(10, 40), "vinte minutos para às onze horas");
        testCases.put(LocalTime.of(10, 19, 22), "dez horas e dezenove minutos");
        testCases.put(LocalTime.of(12, 45), "quinze minutos para às treze horas");
        testCases.put(LocalTime.of(11, 42), "dezoito minutos para o meio-dia");
        testCases.put(LocalTime.of(11, 58), "dois minutos para o meio-dia");
        testCases.put(LocalTime.of(11, 30), "onze horas e trinta minutos");
        testCases.put(LocalTime.of(10, 50, 33), "dez minutos para às onze horas"); //nao mostra segundos por padrão

        TimeInWords dateInWords3 = new TimeInPortuguese.Builder()
                .withMinuteToHourPronuntiation()
                .withMiddayAndMidnightPronuntiation()
                .build();

        testCases.forEach((time, expectedResult) ->
                assertEquals(
                        expectedResult,
                        dateInWords3.inWords(time),
                        "retorno não esperado para a hora " + time));
    }

    @Test
    void inWordsWithHalfTo30Minutes() {
        //teste 1 - witHalfTo30Minutes()
        Map<LocalTime, String> testCases = new HashMap<>();
        testCases.put(LocalTime.of(0, 30), "zero hora e meia");
        testCases.put(LocalTime.of(1, 30), "uma hora e meia");
        testCases.put(LocalTime.of(10, 30), "dez horas e meia");
        testCases.put(LocalTime.of(12, 30), "doze horas e meia");
        testCases.put(LocalTime.of(13, 30), "treze horas e trinta minutos");

        TimeInWords dateInWords1 = new TimeInPortuguese.Builder()
                .withHalfTo30Minutes()
                .build();

        testCases.forEach((time, expectedResult) ->
                assertEquals(
                        expectedResult,
                        dateInWords1.inWords(time),
                        "retorno não esperado para a hora " + time));

        //teste 2 + .withInformalPronuntiation()
        testCases.clear();
        testCases.put(LocalTime.of(0, 30), "zero hora e meia");
        testCases.put(LocalTime.of(1, 30), "uma e meia");
        testCases.put(LocalTime.of(10, 30), "dez e meia");
        testCases.put(LocalTime.of(12, 30), "doze e meia");
        testCases.put(LocalTime.of(13, 30), "treze e trinta");

        TimeInWords dateInWords2 = new TimeInPortuguese.Builder()
                .withHalfTo30Minutes()
                .withInformalPronuntiation()
                .build();

        testCases.forEach((time, expectedResult) ->
                assertEquals(
                        expectedResult,
                        dateInWords2.inWords(time),
                        "retorno não esperado para a hora " + time));

        //teste 3 + .withMiddayAndMidnightPronuntiation()
        testCases.clear();
        testCases.put(LocalTime.of(0, 30), "meia-noite e meia");
        testCases.put(LocalTime.of(1, 30), "uma hora e meia");
        testCases.put(LocalTime.of(10, 30), "dez horas e meia");
        testCases.put(LocalTime.of(12, 30), "meio-dia e meia");
        testCases.put(LocalTime.of(13, 30), "treze horas e trinta minutos");

        TimeInWords dateInWords3 = new TimeInPortuguese.Builder()
                .withHalfTo30Minutes()
                .withMiddayAndMidnightPronuntiation()
                .build();

        testCases.forEach((time, expectedResult) ->
                assertEquals(
                        expectedResult,
                        dateInWords3.inWords(time),
                        "retorno não esperado para a hora " + time));
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
                        "retorno não esperado para a hora " + time));
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
                        "retorno não esperado para a hora " + time));
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
                        "retorno não esperado para a hora " + time));
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
                        "retorno não esperado para a hora " + time));
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
                        "retorno não esperado para a hora " + time));
    }
}