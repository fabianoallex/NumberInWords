package numberinwords.portuguese;

import numberinwords.NumberInWords;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardinalInPortugueseWordsTest {

    @Test
    @DisplayName("inWords")
    void inWords() {
        NumberInWords cardinalNumber = new CardinalInPortugueseWords.Builder()
                .build();

        assertEquals(
                "zero",
                cardinalNumber.inWords(0L),
                "#1 retorno não esperado para o numero 0.");

        assertEquals(
                "um",
                cardinalNumber.inWords(1L),
                "#2 retorno não esperado para o numero 1.");

        assertEquals(
                "menos um",
                cardinalNumber.inWords(-1L),
                "#3 retorno não esperado para o numero -1.");

        assertEquals(
                "dez",
                cardinalNumber.inWords(10L),
                "#4 retorno não esperado para o numero 10.");

        assertEquals(
                "onze",
                cardinalNumber.inWords(11L),
                "#5 retorno não esperado para o numero 11.");

        assertEquals(
                "vinte",
                cardinalNumber.inWords(20L),
                "#6 retorno não esperado para o numero 20.");

        assertEquals(
                "cem",
                cardinalNumber.inWords(100L),
                "#7 retorno não esperado para o numero 100.");

        assertEquals(
                "duzentos e dois",
                cardinalNumber.inWords(202L),
                "#8 retorno não esperado para o numero 202.");

        assertEquals(
                "duzentos e vinte",
                cardinalNumber.inWords(220L),
                "#9 retorno não esperado para o numero 220.");

        assertEquals(
                "trezentos e quarenta e nove",
                cardinalNumber.inWords(349L),
                "#10 retorno não esperado para o numero 349.");

        assertEquals(
                "mil",
                cardinalNumber.inWords(1000L),
                "#11 retorno não esperado para o numero 1000.");

        assertEquals(
                "mil e dezessete",
                cardinalNumber.inWords(1017L),
                "#12 retorno não esperado para o numero 1017.");

        assertEquals(
                "dois mil e noventa e nove",
                cardinalNumber.inWords(2099L),
                "#13 retorno não esperado para o numero 2099.");

        assertEquals(
                "trinta mil",
                cardinalNumber.inWords(30000L),
                "#14 retorno não esperado para o numero 30.000.");

        assertEquals(
                "oitenta mil e nove",
                cardinalNumber.inWords(80009L),
                "#15 retorno não esperado para o numero 80.009.");

        assertEquals(
                "cem mil",
                cardinalNumber.inWords(100000L),
                "#16 retorno não esperado para o numero 100.000.");

        assertEquals(
                "quatrocentos e oitenta e dois mil",
                cardinalNumber.inWords(482000L),
                "#17 retorno não esperado para o numero 482.000.");

        assertEquals(
                "um milhão",
                cardinalNumber.inWords(1000000L),
                "#18 retorno não esperado para o numero 1.000.000.");


        assertEquals(
                "cinquenta milhões e duzentos",
                cardinalNumber.inWords(50000200L),
                "#19 retorno não esperado para o numero 50.000.200.");

        assertEquals(
                "sessenta milhões novecentos e trinta e três mil e trezentos",
                cardinalNumber.inWords(60933300L),
                "#20 retorno não esperado para o numero 60.933.300.");

        assertEquals(
                "um bilhão",
                cardinalNumber.inWords(1000000000L),
                "#21 retorno não esperado para o numero 1.000.000.000.");

        assertEquals(
                "dois bilhões",
                cardinalNumber.inWords(2000000000L),
                "#22 retorno não esperado para o numero 2.000.000.000.");



        assertEquals(
                "um trilhão",
                cardinalNumber.inWords(1000000000000L),
                "#23 retorno não esperado para o numero 1.000.000.000.000.");

        assertEquals(
                "dois trilhões",
                cardinalNumber.inWords(2000000000000L),
                "#24 retorno não esperado para o numero 2.000.000.000.000.");




        assertEquals(
                "um quatrilhão",
                cardinalNumber.inWords(1000000000000000L),
                "#25 retorno não esperado para o numero 1.000.000.000.000.000.");

        assertEquals(
                "dois quatrilhões",
                cardinalNumber.inWords(2000000000000000L),
                "#26 retorno não esperado para o numero 2.000.000.000.000.000.");


        assertEquals(
                "um quintilhão",
                cardinalNumber.inWords(1000000000000000000L),
                "#27 retorno não esperado para o numero 1.000.000.000.000.000.000.");

        assertEquals(
                "dois quintilhões",
                cardinalNumber.inWords(2000000000000000000L),
                "#28 retorno não esperado para o numero 2.000.000.000.000.000.000.");

        assertEquals(
                "nove quintilhões duzentos e vinte e três quatrilhões trezentos e setenta" +
                        " e dois trilhões trinta e seis bilhões oitocentos e cinquenta e quatro" +
                        " milhões setecentos e setenta e cinco mil oitocentos e sete",
                cardinalNumber.inWords(Long.MAX_VALUE),
                "#29 retorno não esperado para o numero 'Long.MAX_VALUE'.");


        //"sextilhão", "septilhão", "octilhão", "nonilhão", "decilhão"};
        //a partir destes não pode ser testado, pois o limite de long não aceita
        //assertEquals(
        //        "um sextilhão",
        //        cardinalNumber.inWords(1000000000000000000000L),
        //        "#30 retorno não esperado para o numero 1.000.000.000.000.000.000.000.");

        //assertEquals(
        //        "dois sextilhões",
        //        cardinalNumber.inWords(2000000000000000000000L),
        //        "#31 retorno não esperado para o numero 2.000.000.000.000.000.000.000.");


/*
        static final String[] singularSuffixes = new String[]{
                "", "mil", "milhão", "bilhão", "trilhão", "quatrilhão",
                "quintilhão", "sextilhão", "septilhão", "octilhão",
                "nonilhão", "decilhão"};*/
    }

    @Test
    @DisplayName("inWords (Female Gender)")
    void inWordsWithFemaleGender() {
        NumberInWords cardinalNumber = new CardinalInPortugueseWords.Builder()
                .withFemaleGender()
                .build();

        assertEquals(
                "uma",
                cardinalNumber.inWords(1L),
                "#1 retorno não esperado para o numero 1.");

        assertEquals(
                "duas",
                cardinalNumber.inWords(2L),
                "#2 retorno não esperado para o numero 2.");

        assertEquals(
                "duzentas",
                cardinalNumber.inWords(200L),
                "#3 retorno não esperado para o numero 200.");

        assertEquals(
                "trezentas",
                cardinalNumber.inWords(300L),
                "#4 retorno não esperado para o numero 300.");

        assertEquals(
                "quatrocentas",
                cardinalNumber.inWords(400L),
                "#5 retorno não esperado para o numero 400.");

        assertEquals(
                "quinhentas",
                cardinalNumber.inWords(500L),
                "#6 retorno não esperado para o numero 500.");

        assertEquals(
                "seiscentas",
                cardinalNumber.inWords(600L),
                "#7 retorno não esperado para o numero 600.");

        assertEquals(
                "setecentas",
                cardinalNumber.inWords(700L),
                "#8 retorno não esperado para o numero 700.");

        assertEquals(
                "oitocentas",
                cardinalNumber.inWords(800L),
                "#9 retorno não esperado para o numero 800.");

        assertEquals(
                "novecentas",
                cardinalNumber.inWords(900L),
                "#10 retorno não esperado para o numero 900.");

        assertEquals(
                "mil",
                cardinalNumber.inWords(1000L),
                "#11 retorno não esperado para o numero 1000.");

        assertEquals(
                "mil e uma",
                cardinalNumber.inWords(1001L),
                "#12 retorno não esperado para o numero 1001.");

        assertEquals(
                "duas mil e duas",
                cardinalNumber.inWords(2002L),
                "#13 retorno não esperado para o numero 2002.");

        assertEquals(
                "duzentos e dois milhões duas mil e duas",
                cardinalNumber.inWords(202002002L),
                "#14 retorno não esperado para o numero 202.002.002.");

        assertEquals(
                "quinhentos bilhões duzentos e dois milhões duas mil e duas",
                cardinalNumber.inWords(500202002002L),
                "#15 retorno não esperado para o numero 500.202.002.002.");
    }

    @Test
    @DisplayName("inWords (Comma Separator)")
    void inWordsWithCommaSeparator() {
        NumberInWords cardinalNumber = new CardinalInPortugueseWords.Builder()
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
        NumberInWords cardinalNumber = new CardinalInPortugueseWords.Builder()
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
        NumberInWords cardinalNumber = new CardinalInPortugueseWords.Builder()
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
        NumberInWords cardinalNumber = new CardinalInPortugueseWords.Builder()
                .withZeroDescription("nenhum")
                .build();

        assertEquals(
                "nenhum",
                cardinalNumber.inWords(0L),
                "#1 retorno não esperado para o numero 0.");

    }
}