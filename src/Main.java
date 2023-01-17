import numberinwords.*;
import numberinwords.english.CardinalInEnglish;
import numberinwords.portuguese.CardinalInPortuguese;
import numberinwords.portuguese.OrdinalInPortuguese;
import numberinwords.roman.NumberInRoman;

import java.math.BigDecimal;
import java.time.LocalDate;

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

        CardinalInWords cardinalInEnglish2 = NumberInWordsFactory.createCardinalInWordsBuilderChooser()
                .forEnglishLanguage()
                .build();

        System.out.println(cardinalInEnglish2.inWords(123456789L));

        CardinalInWords cardinalInPortugues = NumberInWordsFactory.createCardinalInWordsBuilderChooser()
                .forPortugueseLanguage()
                .withCommaSeparator()
                .build();

        System.out.println(cardinalInPortugues.inWords(123456789L));

        var s = NumberInWordsFactory
                .createCardinalInWordsBuilderChooser()
                .forEnglishLanguage()
                .withCommaSeparator()
                .withZeroDescription("Nenhuma")
                .withPositiveSignalDescription("+")
                .withNegativeSignalDescription("-")
                .build()
                .inWords(-2555L);

        MoneyInWords realInPortuguese = NumberInWordsFactory.createMoneyInWordsBuilderChoosers()
                .forRealInPortuguese()
                .build();

        System.out.println(realInPortuguese.inWords(new BigDecimal("2.125")));


        var decimalInPortuguese = NumberInWordsFactory.createDecimalInWordsBuilderChooser()
                .forPortugueseLanguage()
                .build();

        System.out.println(decimalInPortuguese.inWords(new BigDecimal("32.100001")));


        var maleDecimalUnitInPortuguese = NumberInWordsFactory.createDecimalUnitInWordsBuilderChooser()
                .forPortugueseLanguage()
                .withMaleGender()
                .withCommaSeparator()
                .withUnitDescriptions("Carro", "Carros")
                .build();

        System.out.println(maleDecimalUnitInPortuguese.inWords(new BigDecimal("2000")));
        System.out.println(maleDecimalUnitInPortuguese.inWords(new BigDecimal("2000000")));
        System.out.println(maleDecimalUnitInPortuguese.inWords(new BigDecimal("2.001")));
        System.out.println(maleDecimalUnitInPortuguese.inWords(new BigDecimal("0.1")));


        var femaleDecimalUnitInPortuguese = NumberInWordsFactory.createDecimalUnitInWordsBuilderChooser()
                .forPortugueseLanguage()
                .withCommaSeparator()
                .withUnitDescriptions("Casa", "Casas")
                .build();

        System.out.println(femaleDecimalUnitInPortuguese.inWords(new BigDecimal("2000")));
        System.out.println(femaleDecimalUnitInPortuguese.inWords(new BigDecimal("2223456")));
        System.out.println(femaleDecimalUnitInPortuguese.inWords(new BigDecimal("2.001")));
        System.out.println(femaleDecimalUnitInPortuguese.inWords(new BigDecimal("0.1")));

        var realInPortuguese2 = NumberInWordsFactory.createMoneyInWordsBuilderChoosers()
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

        var dolarInPortuguese = NumberInWordsFactory.createMoneyInWordsBuilderChoosers()
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

        var poundsInPortuguese = NumberInWordsFactory.createMoneyInWordsBuilderChoosers()
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

        var bitcoisInPortuguese = NumberInWordsFactory.createMoneyInWordsBuilderChoosers()
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


        var lira = NumberInWordsFactory.createMoneyInWordsBuilderChoosers()
                .forPortugueseLanguage()
                .withCurrencyName("lira turca", "liras turca")
                .withCentsName("kuruş", "kuruş")
                .withSubdivisionDecimalPlaces(2)
                .withGenderForIntegerPart(Gender.FEMALE)
                .withGenderForCentsPart(Gender.MALE)
                .build();

        System.out.println(lira.inWords(new BigDecimal("1.00")));
        System.out.println(lira.inWords(new BigDecimal("2.00")));
        System.out.println(lira.inWords(new BigDecimal("1.25")));
        System.out.println(lira.inWords(new BigDecimal("0.01")));

        var date = NumberInWordsFactory.createDateInWordsBuilderChooser()
                .forPortugueseLanguage()
                .usingMonthAndYear()
                .build();


        System.out.println(date.inWords(LocalDate.of(2023, 12, 31)));

        //primeiro de janeiro de dois mil e vinte e três
        System.out.println(date.inWords(LocalDate.of(2023, 1, 1)));

        var ordinalInEnglish = NumberInWordsFactory.createOrdinalInWordsBuilderChooser()
                .forEnglishLanguage()
                .build();


        //first
        System.out.println(ordinalInEnglish.inWords(1L));

        //tenth
        System.out.println(ordinalInEnglish.inWords(10L));

        //twenty-fifth
        System.out.println(ordinalInEnglish.inWords(25L));

        //eighty-eighth
        System.out.println(ordinalInEnglish.inWords(88L));

        //one hundredth
        System.out.println(ordinalInEnglish.inWords(100L));

        //one hundred first
        System.out.println(ordinalInEnglish.inWords(101L));

        //one thousandth
        System.out.println(ordinalInEnglish.inWords(1000L));

        //one thousand and second
        System.out.println(ordinalInEnglish.inWords(1002L));
    }
}