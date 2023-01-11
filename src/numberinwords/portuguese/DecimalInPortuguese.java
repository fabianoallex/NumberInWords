package numberinwords.portuguese;

import numberinwords.DecimalInWords;
import numberinwords.NumberInWordsFactory;

import java.math.BigDecimal;

public class DecimalInPortuguese implements DecimalInWords {
    static final String[] hundredSuffix = {"", "décimo", "centésimo"};
    static final String[] thousandsSuffixes = new String[] {
            "", "milésimo", "milionésimo", "bilionésimo", "trilionésimo",
            "quatrilionésimo", "quintilionésimo", "sextilionésimo",
            "septilionésimo", "octilionésimo", "nonilionésimo", "decilionésimo"
    };

    private final boolean useCommaSeparator;

    private DecimalInPortuguese(Builder builder) {
        this.useCommaSeparator = builder.useCommaSeparator;
    }

    @Override
    public String inWords(BigDecimal number) {
        int numberOfDecimalPlaces = getNumberOfDecimalPlaces(number);
        long numberOfDecimalPlacesMultiplier = (long) Math.pow(10, numberOfDecimalPlaces);

        long integerPart = number.longValue();

        number = number.subtract(new BigDecimal(integerPart));
        number = number.multiply(BigDecimal.valueOf(numberOfDecimalPlacesMultiplier));

        long decimalPart = number.longValue();

        String integerPartDescription = this.getIntegerPartDescription(integerPart, decimalPart);
        String decimalPartDescription = this.getDecimalPartDescription(decimalPart, numberOfDecimalPlaces);
        String conjuction = "";

        if (integerPart > 0 && decimalPart > 0)
            conjuction = " e ";

        return integerPartDescription + conjuction + decimalPartDescription;
    }

    private String getIntegerPartDescription(long integerPart, long decimalPart) {
        String zeroDescription = (integerPart == 0 && decimalPart > 0) ? "" : null;

        String integerSiffix = "";

        if (integerPart > 0)
            integerSiffix = (integerPart == 1) ? " inteiro" : " inteiros";

        return NumberInWordsFactory.createCardinalInWordsBuilder()
                .forPortugueseLanguage()
                .withZeroDescription(zeroDescription)
                .withCommaSeparator(this.useCommaSeparator)
                .build()
                .inWords(integerPart)
                    + integerSiffix;
    }

    private String getDecimalPartDescription(long decimalPart, int numberOfDecimalPlaces) {
        long numberOfDecimalPlacesMultiplier = (long) Math.pow(10, numberOfDecimalPlaces);
        long validNumber = (long) (decimalPart % Math.pow(10, numberOfDecimalPlacesMultiplier));

        if (validNumber == 0)
            return "";

        Integer validNumberOfDecimalPlaces = numberOfDecimalPlaces;

        while (validNumber % 10 == 0) {
            validNumber /= 10;
            validNumberOfDecimalPlaces--;
        }

        return NumberInWordsFactory.createCardinalInWordsBuilder()
                .forPortugueseLanguage()
                .withCommaSeparator(this.useCommaSeparator)
                .build()
                .inWords(validNumber) + " " +
                    this.getSuffixDescription(validNumberOfDecimalPlaces, validNumber);
    }

    private int getNumberOfDecimalPlaces(BigDecimal bigDecimal) {
        String string = bigDecimal.stripTrailingZeros().toPlainString();
        int index = string.indexOf(".");
        return index < 0 ? 0 : string.length() - index - 1;
    }

    private String getSuffixDescription(Integer numberOfDecimalPlaces, Long number) {
        int hundred = numberOfDecimalPlaces % 3;
        int thousand = numberOfDecimalPlaces / 3;

        String hundredDescription = hundredSuffix[hundred];
        String thousandDescription = thousandsSuffixes[thousand];

        if (number > 1 && hundred > 0)
            hundredDescription += "s";
        else if (number > 1 && thousand > 0)
            thousandDescription += "s";

        String conjuction = "";

        if (hundred > 0 && thousand > 0)
            conjuction = " de ";

        return hundredDescription + conjuction + thousandDescription;
    }

    public static class Builder {
        private boolean useCommaSeparator;

        public Builder withCommaSeparator(boolean useCommaSeparator) {
            this.useCommaSeparator = useCommaSeparator;
            return this;
        }

        public DecimalInPortuguese build() {
            return new DecimalInPortuguese(this);
        }
    }
}
