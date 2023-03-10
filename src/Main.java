import numberinwords.*;
import numberinwords.english.CardinalInEnglish;
import numberinwords.portuguese.CardinalInPortuguese;
import numberinwords.portuguese.OrdinalInPortuguese;
import numberinwords.roman.NumberInRoman;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.function.Consumer;

import static java.lang.String.join;

public class Main {
    public static void main(String[] args) {
        CardinalInWords cardinalNumber = new CardinalInPortuguese.Builder()
                .withCommaSeparator()
                .withFemaleGender()
                .withZeroDescription("Nenhuma")
                .withNegativeSignalDescription("(NEGATIVO)")
                //.withPositiveSignalDescription("(POSITIVO)")
                .build();

        System.out.println(cardinalNumber.inWords(0L));
        System.out.println(cardinalNumber.inWords(30L));
        System.out.println(cardinalNumber.inWords(230L));
        System.out.println(cardinalNumber.inWords(1030L));
        System.out.println(cardinalNumber.inWords(1000030L));
        System.out.println(cardinalNumber.inWords(31L));
        System.out.println(cardinalNumber.inWords(40L));
        System.out.println(cardinalNumber.inWords(90L));

        System.out.println(cardinalNumber.inWords(100L));
        System.out.println(cardinalNumber.inWords(101L));
        System.out.println(cardinalNumber.inWords(110L));
        System.out.println(cardinalNumber.inWords(111L));
        System.out.println(cardinalNumber.inWords(120L));
        System.out.println(cardinalNumber.inWords(121L));

        System.out.println(cardinalNumber.inWords(200L));
        System.out.println(cardinalNumber.inWords(202L));
        System.out.println(cardinalNumber.inWords(222L));

        System.out.println(cardinalNumber.inWords(2000L));
        System.out.println(cardinalNumber.inWords(2002L));
        System.out.println(cardinalNumber.inWords(2022L));
        System.out.println(cardinalNumber.inWords(2099L));

        System.out.println(cardinalNumber.inWords(2100L));
        System.out.println(cardinalNumber.inWords(2101L));
        System.out.println(cardinalNumber.inWords(2111L));
        System.out.println(cardinalNumber.inWords(2200L));
        System.out.println(cardinalNumber.inWords(2202L));

        System.out.println(cardinalNumber.inWords(5000100L));
        System.out.println(cardinalNumber.inWords(5000101L));
        System.out.println(cardinalNumber.inWords(5100000L));
        System.out.println(cardinalNumber.inWords(5100001L));

        System.out.println(cardinalNumber.inWords(5002100L));
        System.out.println(cardinalNumber.inWords(5002101L));
        System.out.println(cardinalNumber.inWords(5002111L));

        System.out.println(cardinalNumber.inWords(1002100L));
        System.out.println(cardinalNumber.inWords(1002101L));
        System.out.println(cardinalNumber.inWords(1002111L));

        System.out.println(cardinalNumber.inWords(1005002111L));
        System.out.println(cardinalNumber.inWords(2001252111L));

        System.out.println(cardinalNumber.inWords(2000000111L));

        System.out.println(cardinalNumber.inWords(1000L));
        System.out.println(cardinalNumber.inWords(1001L));
        System.out.println(cardinalNumber.inWords(10000L));
        System.out.println(cardinalNumber.inWords(100000L));

        System.out.println(cardinalNumber.inWords((long) Integer.MAX_VALUE));
        System.out.println(cardinalNumber.inWords(Long.MAX_VALUE));
        System.out.println(cardinalNumber.inWords(-Long.MAX_VALUE));

        OrdinalInWords ordinalNumber = new OrdinalInPortuguese.Builder()
                .withMaleGender()
                //.withCommaSeparator()
                .build();

        System.out.println(ordinalNumber.inWords(125221129L));
        System.out.println(ordinalNumber.inWords(11L));
        System.out.println(ordinalNumber.inWords(12L));
        System.out.println(ordinalNumber.inWords(16L));

        NumberInRoman numberInRoman = new NumberInRoman.Builder()
                .withAdditiveMethod()
                .build();

        for (int i = 0; i <= 10; i++) {
            System.out.println(numberInRoman.inRoman(i));
        }

        System.out.println(numberInRoman.inRoman(3888));

        CardinalInWords cardinalInEnglish = new CardinalInEnglish.Builder()
                .withCommaSeparator()
                .build();

        System.out.println(cardinalInEnglish.inWords(0L));
        System.out.println(cardinalInEnglish.inWords(30L));
        System.out.println(cardinalInEnglish.inWords(230L));
        System.out.println(cardinalInEnglish.inWords(1030L));
        System.out.println(cardinalInEnglish.inWords(1000030L));
        System.out.println(cardinalInEnglish.inWords(31L));
        System.out.println(cardinalInEnglish.inWords(40L));
        System.out.println(cardinalInEnglish.inWords(90L));

        System.out.println(cardinalInEnglish.inWords(5002100L));
        System.out.println(cardinalInEnglish.inWords(5002101L));
        System.out.println(cardinalInEnglish.inWords(5002111L));

        System.out.println(cardinalInEnglish.inWords(1002100L));
        System.out.println(cardinalInEnglish.inWords(1002101L));
        System.out.println(cardinalInEnglish.inWords(1002111L));

        System.out.println(cardinalInEnglish.inWords(1005002111L));
        System.out.println(cardinalInEnglish.inWords(2001252111L));

        System.out.println(cardinalInEnglish.inWords(2000000111L));

        System.out.println(cardinalInEnglish.inWords(1000L));
        System.out.println(cardinalInEnglish.inWords(1001L));
        System.out.println(cardinalInEnglish.inWords(10000L));
        System.out.println(cardinalInEnglish.inWords(100000L));

        System.out.println(cardinalInEnglish.inWords((long) Integer.MAX_VALUE));
        System.out.println(cardinalInEnglish.inWords(Long.MAX_VALUE));
        System.out.println(cardinalInEnglish.inWords(-Long.MAX_VALUE));

        CardinalInWords cardinalInEnglish2 = NumberInWordsFactory.createCardinalBuilderChooser()
                .forEnglishLanguage()
                .build();

        System.out.println(cardinalInEnglish2.inWords(123456789L));

        CardinalInWords cardinalInPortugues = NumberInWordsFactory.createCardinalBuilderChooser()
                .forPortugueseLanguage()
                .withCommaSeparator()
                .build();

        System.out.println(cardinalInPortugues.inWords(123456789L));

        var s = NumberInWordsFactory
                .createCardinalBuilderChooser()
                .forEnglishLanguage()
                .withCommaSeparator()
                .withZeroDescription("Nenhuma")
                .withPositiveSignalDescription("+")
                .withNegativeSignalDescription("-")
                .build()
                .inWords(-2555L);

        MoneyInWords realInPortuguese = NumberInWordsFactory.createMoneyBuilderChoosers()
                .forRealInPortuguese()
                .build();

        System.out.println(realInPortuguese.inWords(new BigDecimal("2.125")));


        var decimalInPortuguese = NumberInWordsFactory.createDecimalBuilderChooser()
                .forPortugueseLanguage()
                .build();

        System.out.println(decimalInPortuguese.inWords(new BigDecimal("32.100001")));


        var maleDecimalUnitInPortuguese = NumberInWordsFactory.createDecimalUnitBuilderChooser()
                .forPortugueseLanguage()
                .withMaleGender()
                .withCommaSeparator()
                .withUnitDescriptions("Carro", "Carros")
                .build();

        System.out.println(maleDecimalUnitInPortuguese.inWords(new BigDecimal("2000")));
        System.out.println(maleDecimalUnitInPortuguese.inWords(new BigDecimal("2000000")));
        System.out.println(maleDecimalUnitInPortuguese.inWords(new BigDecimal("2.001")));
        System.out.println(maleDecimalUnitInPortuguese.inWords(new BigDecimal("0.1")));


        var femaleDecimalUnitInPortuguese = NumberInWordsFactory.createDecimalUnitBuilderChooser()
                .forPortugueseLanguage()
                .withCommaSeparator()
                .withUnitDescriptions("Casa", "Casas")
                .build();

        System.out.println(femaleDecimalUnitInPortuguese.inWords(new BigDecimal("2000")));
        System.out.println(femaleDecimalUnitInPortuguese.inWords(new BigDecimal("2223456")));
        System.out.println(femaleDecimalUnitInPortuguese.inWords(new BigDecimal("2.001")));
        System.out.println(femaleDecimalUnitInPortuguese.inWords(new BigDecimal("0.1")));

        var realInPortuguese2 = NumberInWordsFactory.createMoneyBuilderChoosers()
                .forRealInPortuguese()
                .withCommaSeparator()
                .build();

        System.out.println(realInPortuguese2.inWords(new BigDecimal("2000")));
        System.out.println(realInPortuguese2.inWords(new BigDecimal("2223456")));
        System.out.println(realInPortuguese2.inWords(new BigDecimal("2.001")));
        System.out.println(realInPortuguese2.inWords(new BigDecimal("0.1")));
        System.out.println(realInPortuguese2.inWords(new BigDecimal("1000000")));
        System.out.println(realInPortuguese2.inWords(new BigDecimal("1.25")));
        System.out.println(realInPortuguese2.inWords(new BigDecimal("1.255")));

        var dolarInPortuguese = NumberInWordsFactory.createMoneyBuilderChoosers()
                .forDollarInPortuguese()
                .withCommaSeparator()
                .build();

        System.out.println(dolarInPortuguese.inWords(new BigDecimal("2000")));
        System.out.println(dolarInPortuguese.inWords(new BigDecimal("2223456.33")));
        System.out.println(dolarInPortuguese.inWords(new BigDecimal("2.001")));
        System.out.println(dolarInPortuguese.inWords(new BigDecimal("0.1")));
        System.out.println(dolarInPortuguese.inWords(new BigDecimal("1000000")));
        System.out.println(dolarInPortuguese.inWords(new BigDecimal("1.25")));
        System.out.println(dolarInPortuguese.inWords(new BigDecimal("1.255")));

        var poundsInPortuguese = NumberInWordsFactory.createMoneyBuilderChoosers()
                .forPoundInPortuguese()
                .withCommaSeparator()
                .build();

        System.out.println(poundsInPortuguese.inWords(new BigDecimal("2000")));
        System.out.println(poundsInPortuguese.inWords(new BigDecimal("0.01")));
        System.out.println(poundsInPortuguese.inWords(new BigDecimal("2223456.33")));
        System.out.println(poundsInPortuguese.inWords(new BigDecimal("2.001")));
        System.out.println(poundsInPortuguese.inWords(new BigDecimal("0.1")));
        System.out.println(poundsInPortuguese.inWords(new BigDecimal("2.02")));
        System.out.println(poundsInPortuguese.inWords(new BigDecimal("1000000")));
        System.out.println(poundsInPortuguese.inWords(new BigDecimal("1.25")));
        System.out.println(poundsInPortuguese.inWords(new BigDecimal("1.255")));

        var bitcoisInPortuguese = NumberInWordsFactory.createMoneyBuilderChoosers()
                .forBitcoinInPortuguese()
                .build();

        System.out.println(bitcoisInPortuguese.inWords(new BigDecimal("2000")));
        System.out.println(bitcoisInPortuguese.inWords(new BigDecimal("0.01")));
        System.out.println(bitcoisInPortuguese.inWords(new BigDecimal("2223456.32")));
        System.out.println(bitcoisInPortuguese.inWords(new BigDecimal("2.001")));
        System.out.println(bitcoisInPortuguese.inWords(new BigDecimal("0.00000001")));
        System.out.println(bitcoisInPortuguese.inWords(new BigDecimal("0.01")));
        System.out.println(bitcoisInPortuguese.inWords(new BigDecimal("1000000.0")));
        System.out.println(bitcoisInPortuguese.inWords(new BigDecimal("1.25")));
        System.out.println(bitcoisInPortuguese.inWords(new BigDecimal("1.255")));
        System.out.println(bitcoisInPortuguese.inWords(new BigDecimal("1.00000001")));


        var lira = NumberInWordsFactory.createMoneyBuilderChoosers()
                .forPortugueseLanguage()
                .withCurrencyName("lira turca", "liras turca")
                .withCentsName("kuru??", "kuru??")
                .withSubdivisionDecimalPlaces(2)
                .withGenderForIntegerPart(Gender.FEMALE)
                .withGenderForCentsPart(Gender.MALE)
                .build();

        System.out.println(lira.inWords(new BigDecimal("1.00")));
        System.out.println(lira.inWords(new BigDecimal("2.00")));
        System.out.println(lira.inWords(new BigDecimal("1.25")));
        System.out.println(lira.inWords(new BigDecimal("0.01")));

        var date = NumberInWordsFactory.createDateBuilderChooser()
                .forPortugueseLanguage()
                .usingMonthAndYear()
                .build();


        System.out.println(date.inWords(LocalDate.of(2023, 12, 31)));

        //primeiro de janeiro de dois mil e vinte e tr??s
        System.out.println(date.inWords(LocalDate.of(2023, 1, 1)));

        var ordinalInEnglish = NumberInWordsFactory.createOrdinalBuilderChooser()
                .forEnglishLanguage()
                .withNumberRepresentation()
                .build();


        //1st
        System.out.println(ordinalInEnglish.inWords(1L));

        //2st
        System.out.println(ordinalInEnglish.inWords(2L));

        //3rd
        System.out.println(ordinalInEnglish.inWords(3L));

        //10th
        System.out.println(ordinalInEnglish.inWords(10L));

        //25th
        System.out.println(ordinalInEnglish.inWords(25L));

        //1000th
        System.out.println(ordinalInEnglish.inWords(1000L));

        //1002nd
        System.out.println(ordinalInEnglish.inWords(1002L));

        var dateInEnglish = NumberInWordsFactory.createDateBuilderChooser()
                .forEnglishLanguage()
                .withCardinalForDay()
                .build();

        System.out.println(dateInEnglish.inWords(LocalDate.of(2023, 12, 31)));
        System.out.println(dateInEnglish.inWords(LocalDate.of(2023, 1, 1)));

        var fractional = NumberInWordsFactory.createFractionalBuilderChooser()
                .forPortugueseLanguage()
                .withOverPronuntiation()
                .build();

        //um sobre dois (1/2)
        System.out.println(fractional.inWords(Fractional.of(2)));

        //vinte e nove e dois sobre tr??s (29 * 2/3)
        System.out.println(fractional.inWords(Fractional.of(29,2, 3)));

        //um sobre cinquenta (1/50)
        System.out.println(fractional.inWords(Fractional.of(50)));

        //trinta e tr??s sobre cem (33/100)
        System.out.println(fractional.inWords(Fractional.of(33,100)));


        var percentInPortugues = NumberInWordsFactory.createPercentBuildeChooser()
                .forPortugueseLanguage()
                .build();

        System.out.println(percentInPortugues.inWords(new BigDecimal("20.3")));

        var timeInPortuguese = NumberInWordsFactory.createTimeBuilderChooser()
                .forPortugueseLanguage()
                .withHalfFor30Minutes()
                .withMiddayAndMidnightPronuntiation()
                .withMinuteToHourPronuntiation()
                .with12HoursFormat()
                .withInformalPronuntiation()
                .withPeriodPronuntiation()
                .build();

        //vinte para meia-noite
        System.out.println(timeInPortuguese.inWords(LocalTime.of(23, 40)));
        //nove e meia da manh??
        System.out.println(timeInPortuguese.inWords(LocalTime.of(9, 30)));
        //dez e meia da noite
        System.out.println(timeInPortuguese.inWords(LocalTime.of(22, 30)));
        //dez para o meio-dia
        System.out.println(timeInPortuguese.inWords(LocalTime.of(11, 50)));
        //dois para ??s dez da noite
        System.out.println(timeInPortuguese.inWords(LocalTime.of(21, 58)));


        CardinalInWords digit = new CardinalInEnglish.Builder()
                .withDigitPronuntiation()
                .build();

        //zero
        System.out.println(digit.inWords(0L));
        //seven eight
        System.out.println(digit.inWords(78L));
        //nine five five
        System.out.println(digit.inWords(955L));
        //five zero two two
        System.out.println(digit.inWords(5022L));



        LocalTime localTime = LocalTime.of(0, 0);
        Map<LocalTime, Set<String>> map = new LinkedHashMap<>();

        do {
            LocalTime finalLocalTime = localTime;

            Set<String> values = new HashSet<>();

            permutation(13, (boolean[] options) -> {
                var timeInEnglish = NumberInWordsFactory.createTimeBuilderChooser()
                        .forEnglishLanguage()
                        .withUntilWordForTo(options[0])
                        .withAfterWordForPast(options[1])
                        .withOh(options[2])
                        .withPastAndToHours(options[3])
                        .withMilitaryFormat(options[4])
                        .withAmPm(options[5])
                        .withNoon(options[6])
                        .with24HoursFormat(options[7])
                        .withUnits(options[8])
                        .withOClock(options[9])
                        .withQuarterAndHalf(options[10])
                        .withPeriodPronuntiation(options[11])
                        .withMiddayAndMidnightPronuntiation(options[12])
                        .build();
                    values.add(timeInEnglish.inWords(finalLocalTime));
            });

            map.put(finalLocalTime, values);

            if (localTime.getHour() == 23 && localTime.getMinute() == 59)
                break;

            localTime = localTime.plus(Duration.ofMinutes(1));

        } while (true) ;


        try {
            FileWriter writer = new FileWriter("c:\\trabalho\\time.txt");

            map.forEach((localTime1, strings) -> {
                try {
                    writer.write(localTime1.toString() + "\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                List<String> list = new ArrayList<>(strings);
                Collections.sort(list);

                list.forEach(s1 -> {
                    try {
                        writer.write("\t\t" + s1 + "\n");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            });

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void permutation(int size, Consumer<boolean[]> callback) {
        boolean[] array = new boolean[size];
        permutationHelper(array, 0, callback);
    }

    private static void permutationHelper(boolean[] array, int index, Consumer<boolean[]> callback) {
        if (index == array.length) {
            callback.accept(array);
            return;
        }
        array[index] = false;
        permutationHelper(array, index + 1, callback);
        array[index] = true;
        permutationHelper(array, index + 1, callback);
    }
}