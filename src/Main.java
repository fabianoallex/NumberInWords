import numberinwords.CardinalInWords;
import numberinwords.NumberInWords;
import numberinwords.OrdinalInWords;
import numberinwords.portuguese.CardinalInPortuguese;
import numberinwords.portuguese.OrdinalInPortuguese;

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
    }
}

