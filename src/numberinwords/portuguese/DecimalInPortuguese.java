package numberinwords.portuguese;

import numberinwords.DecimalInWords;
import numberinwords.NumberInWordsFactory;

public class DecimalInPortuguese implements DecimalInWords {
    static final String[] hundredSuffix = {"", "décimo", "centésimo"};
    static final String[] thousandsSuffixes = new String[] {
            "", "milésimo", "milionésimo", "bilionésimo", "trilionésimo",
            "quatrilionésimo", "quintilionésimo", "sextilionésimo",
            "septilionésimo", "octilionésimo", "nonilionésimo", "decilionésimo"
    };

    private final Integer numberOfDecimalPlaces;
    private final boolean useCommaSeparator;

    private DecimalInPortuguese(Builder builder) {
        this.numberOfDecimalPlaces = builder.numberOfDecimalPlaces;
        this.useCommaSeparator = builder.useCommaSeparator;
    }

    @Override
    public String inWords(Long number) {
        long validNumber = (long) (number % Math.pow(10, this.numberOfDecimalPlaces));

        if (validNumber == 0)
            return "";

        Integer validNumberOfDecimalPlaces = this.numberOfDecimalPlaces;

        while (validNumber % 10 == 0) {
            validNumber /= 10;
            validNumberOfDecimalPlaces--;
        }

        return NumberInWordsFactory.createCardinalInWords()
                .forPortugueseLanguage()
                .withCommaSeparator(this.useCommaSeparator)
                .build()
                .inWords(validNumber) + " " +
                    this.getSuffixDescription(validNumberOfDecimalPlaces, validNumber);
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
        private final Integer numberOfDecimalPlaces;
        private boolean useCommaSeparator;

        public Builder(Integer numberOfDecimalPlaces) {
            this.numberOfDecimalPlaces = numberOfDecimalPlaces <= 0 ? 1 : numberOfDecimalPlaces;
        }

        public Builder withCommaSeparator(boolean useCommaSeparator) {
            this.useCommaSeparator = useCommaSeparator;
            return this;
        }

        public DecimalInPortuguese build() {
            return new DecimalInPortuguese(this);
        }
    }
}
