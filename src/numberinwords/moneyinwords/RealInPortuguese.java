package numberinwords.moneyinwords;

import numberinwords.CardinalInWords;
import numberinwords.DecimalInWords;
import numberinwords.NumberInWordsFactory;
import java.math.BigDecimal;

public class RealInPortuguese implements MoneyInWords {
    private final CardinalInWords cardinalInWords;
    private final DecimalInWords decimalInWords;
    private final String singularCurrencyName;
    private final String pluralCurrencyName;
    private final String singularCurrencyNameWithPreposition;
    private final String pluralCurrencyNameWithPreposition;
    private final String singularCentsDescription;
    private final String pluralCentsDescritpion;
    private final String singularCentsDescriptionWhenLessOne;
    private final String pluralCentsDescritpionWhenLessOne;
    private final String conjuction;

    private RealInPortuguese(Builder builder) {
        this.cardinalInWords = builder.cardinalInWords;
        this.decimalInWords = builder.decimalInWords;
        this.singularCurrencyName = builder.singularCurrencyName;
        this.pluralCurrencyName = builder.pluralCurrencyName;
        this.singularCurrencyNameWithPreposition = builder.singularCurrencyNameWithPreposition;
        this.pluralCurrencyNameWithPreposition = builder.pluralCurrencyNameWithPreposition;
        this.singularCentsDescription = builder.singularCentsDescription;
        this.pluralCentsDescritpion = builder.pluralCentsDescritpion;
        this.singularCentsDescriptionWhenLessOne = builder.singularCentsDescriptionWhenLessOne;
        this.pluralCentsDescritpionWhenLessOne = builder.pluralCentsDescritpionWhenLessOne;
        this.conjuction = builder.conjuction;
    }

    @Override
    public String inWords(BigDecimal value) {
        String currencyDescription = this.getIntegerPartDescription(value);
        String centsDescription = this.getCentsPartDescription(value);
        String conjuction = this.getConjuction(value);

        return currencyDescription + conjuction + centsDescription;
    }

    private long getIntegerPart(BigDecimal value) {
        int numberOfDecimalPlaces = getNumberOfDecimalPlaces(value);
        long placesMultiplier = (long) Math.pow(10, numberOfDecimalPlaces);
        long centsValue = value
                .multiply(BigDecimal.valueOf(placesMultiplier))
                .longValue();
        return centsValue / placesMultiplier;
    }

    private long getCentsPart(BigDecimal value) {
        int numberOfDecimalPlaces = getNumberOfDecimalPlaces(value);
        long placesMultiplier = (long) Math.pow(10, numberOfDecimalPlaces);
        long centsValue = value
                .multiply(BigDecimal.valueOf(placesMultiplier))
                .longValue();
        return centsValue % placesMultiplier;
    }

    private String getCentsPartDescription(BigDecimal value) {
        String centsDescription = "";

        int numberOfDecimalPlaces = getNumberOfDecimalPlaces(value);
        long integerPart = this.getIntegerPart(value);
        long centsPart = this.getCentsPart(value);

        //mais de duas casas decimais
        if (numberOfDecimalPlaces > 2)
            return decimalInWords.inWords(value) + (integerPart == 0 ? " " + this.singularCurrencyNameWithPreposition : "");

        if (numberOfDecimalPlaces == 1)
            centsPart = centsPart * 10;

        //menos de duas casas decimais
        if (centsPart > 0)
            centsDescription = cardinalInWords.inWords(centsPart);

        if (integerPart == 0 && centsPart == 1)
            centsDescription += " " + this.singularCentsDescriptionWhenLessOne;

        if (integerPart == 0 && centsPart > 1)
            centsDescription += " " + this.pluralCentsDescritpionWhenLessOne;

        if (integerPart > 0 && centsPart == 1)
            centsDescription += " " + this.singularCentsDescription;

        if (integerPart > 0 && centsPart > 1)
            centsDescription += " " + this.pluralCentsDescritpion;

        return centsDescription;
    }

    private int getNumberOfDecimalPlaces(BigDecimal bigDecimal) {
        String string = bigDecimal.stripTrailingZeros().toPlainString();
        int index = string.indexOf(".");
        return index < 0 ? 0 : string.length() - index - 1;
    }

    private String getConjuction(BigDecimal value) {
        if (this.getIntegerPart(value) > 0 && this.getCentsPart(value) > 0)
            return  " " + this.conjuction + " ";

        return "";
    }

    private String getIntegerPartDescription(BigDecimal value) {
        if (value.doubleValue() == 0)
            return cardinalInWords.inWords(0L) + " " + this.singularCurrencyName;

        long integerPart = this.getIntegerPart(value);
        boolean useCurrencyNameWithPreposition = (integerPart % 1000000) == 0; //um milhão de real, um bilhão de real

        String currencyDescription = "";

        if (integerPart > 0)
            currencyDescription = cardinalInWords.inWords(integerPart);

        if (integerPart == 1)
            currencyDescription += " " +
                    (useCurrencyNameWithPreposition ?
                            this.singularCurrencyNameWithPreposition : this.singularCurrencyName);

        if (integerPart > 1)
            currencyDescription += " " +
                    (useCurrencyNameWithPreposition ?
                            this.pluralCurrencyNameWithPreposition : this.pluralCurrencyName);;

        return currencyDescription;
    }

    public static class Builder {
        private CardinalInWords cardinalInWords;
        private DecimalInWords decimalInWords;
        private String singularCurrencyName = "real";
        private String pluralCurrencyName = "reais";
        private String singularCurrencyNameWithPreposition = "de real";
        private String pluralCurrencyNameWithPreposition = "de reais";
        private String singularCentsDescription = "centavo";
        private String pluralCentsDescritpion = "centavos";
        private String singularCentsDescriptionWhenLessOne = "centavo de real";
        private String pluralCentsDescritpionWhenLessOne = "centavos de real";
        private String conjuction = "e";
        private boolean useCommaSeparator = false;

        public Builder withSingularCurrencyNameWithPreposition(String description) {
            this.singularCurrencyNameWithPreposition = description;
            return this;
        }

        public Builder withPluralCurrencyNameWithPreposition(String description) {
            this.pluralCurrencyNameWithPreposition = description;
            return this;
        }

        public Builder withCommaSeparator(boolean useCommaSeparator) {
            this.useCommaSeparator = useCommaSeparator;
            return this;
        }

        public Builder withCommaSeparator() {
            this.useCommaSeparator = true;
            return this;
        }

        public Builder withConjuction(String conjuction) {
            this.conjuction = conjuction;
            return this;
        }

        public Builder withCurrencyName(String singularCurrencyName, String pluralCurrencyName) {
            this.singularCurrencyName = singularCurrencyName;
            this.pluralCurrencyName = pluralCurrencyName;
            return this;
        }

        public Builder withSingularCentsDescription(String singularCentsDescription) {
            this.singularCentsDescription = singularCentsDescription;
            return this;
        }

        public Builder withPluralCentsDescription(String pluralCentsDescription) {
            this.pluralCentsDescritpion = pluralCentsDescription;
            return this;
        }

        public Builder withSingularCentsDescriptionWhenLessOne(String singularCentsDescriptionWhenLessOne) {
            this.singularCentsDescriptionWhenLessOne = singularCentsDescriptionWhenLessOne;
            return this;
        }

        public Builder withPluralCentsDescriptionWhenLessOne(String pluralCentsDescriptionWhenLessOne) {
            this.pluralCentsDescritpionWhenLessOne = pluralCentsDescriptionWhenLessOne;
            return this;
        }

        public RealInPortuguese build() {
            if (cardinalInWords == null)
                this.cardinalInWords = NumberInWordsFactory.createCardinalInWordsBuilder()
                        .forPortugueseLanguage()
                        .withCommaSeparator(this.useCommaSeparator)
                        .build();

            if (decimalInWords == null)
                this.decimalInWords = NumberInWordsFactory.createDecimalInWordsBuilder()
                        .forPortugueseLanguage()
                        .withOnlyDecimalPart()
                        .withCommaSeparator(this.useCommaSeparator)
                        .build();

            return new RealInPortuguese(this);
        }
    }
}
