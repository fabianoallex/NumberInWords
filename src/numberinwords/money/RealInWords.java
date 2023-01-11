package numberinwords.money;

import numberinwords.CardinalInWords;
import numberinwords.NumberInWordsFactory;
import java.math.BigDecimal;

public class RealInWords implements MoneyInWords {
    private final String singularCurrencyName;
    private final String pluralCurrencyName;
    private final CardinalInWords cardinalInWords;
    private final String singularCentsDescription;
    private final String pluralCentsDescritpion;
    private final String singularCentsDescriptionWhenLessOne;
    private final String pluralCentsDescritpionWhenLessOne;
    private final String conjuction;
    private final boolean useCommaSeparator;

    private RealInWords(Builder builder) {
        this.singularCurrencyName = builder.singularCurrencyName;
        this.pluralCurrencyName = builder.pluralCurrencyName;
        this.cardinalInWords = builder.cardinalInWords;
        this.singularCentsDescription = builder.singularCentsDescription;
        this.pluralCentsDescritpion = builder.pluralCentsDescritpion;
        this.singularCentsDescriptionWhenLessOne = builder.singularCentsDescriptionWhenLessOne;
        this.pluralCentsDescritpionWhenLessOne = builder.pluralCentsDescritpionWhenLessOne;
        this.conjuction = builder.conjuction;
        this.useCommaSeparator = builder.useCommaSeparator;
    }

    @Override
    public String inWords(BigDecimal value) {
        String currencyDescription = this.getCurrencyDescription(value);
        String centsDescription = this.getCentsDescription(value);
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

    private String getCentsDescription(BigDecimal value) {
        String centsDescription = "";

        int numberOfDecimalPlaces = getNumberOfDecimalPlaces(value);
        long integerPart = this.getIntegerPart(value);
        long centsPart = this.getCentsPart(value);

        //mais de duas casas decimais
        if (numberOfDecimalPlaces > 2)
            return NumberInWordsFactory.createDecimalInWordsBuilder()
                    .forPortugueseLanguage()
                    .withOnlyDecimalPart()
                    .withCommaSeparator(this.useCommaSeparator)
                    .build()
                    .inWords(value) +
                        (integerPart == 0 ? " de " + this.singularCurrencyName : "");

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

    private String getCurrencyDescription(BigDecimal value) {
        if (value.doubleValue() == 0)
            return cardinalInWords.inWords(0L) + " " + this.singularCurrencyName;

        long integerPart = this.getIntegerPart(value);

        String currencyDescription = "";

        if (integerPart > 0)
            currencyDescription = cardinalInWords.inWords(integerPart);

        if (integerPart == 1)
            currencyDescription += " " + this.singularCurrencyName;

        if (integerPart > 1)
            currencyDescription += " " + this.pluralCurrencyName;

        return currencyDescription;
    }

    public static class Builder {
        private CardinalInWords cardinalInWords;
        private String singularCurrencyName = "real";
        private String pluralCurrencyName = "reais";
        private String singularCentsDescription = "centavo";
        private String pluralCentsDescritpion = "centavos";
        private String singularCentsDescriptionWhenLessOne = "centavo de real";
        private String pluralCentsDescritpionWhenLessOne = "centavos de real";
        private String conjuction = "e";
        private boolean useCommaSeparator = false;

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

        public RealInWords build() {
            if (cardinalInWords == null)
                this.cardinalInWords = NumberInWordsFactory.createCardinalInWordsBuilder()
                        .forPortugueseLanguage()
                        .withCommaSeparator(this.useCommaSeparator)
                        .build();

            return new RealInWords(this);
        }
    }
}
