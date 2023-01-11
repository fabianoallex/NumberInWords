package numberinwords.money;

import numberinwords.CardinalInWords;
import numberinwords.NumberInWordsFactory;
import java.math.BigDecimal;

public class RealInWords implements MoneyInWords {
    private final Integer numberOfDecimalPlacesToCents;
    private final String singularCurrencyName;
    private final String pluralCurrencyName;
    private final CardinalInWords cardinalInWords;
    private final String singularCentsDescription;
    private final String pluralCentsDescritpion;
    private final String singularCentsDescriptionWhenLessOne;
    private final String pluralCentsDescritpionWhenLessOne;
    private final String conjuction;

    private RealInWords(Builder builder) {
        this.numberOfDecimalPlacesToCents = builder.numberOfDecimalPlacesToCents;
        this.singularCurrencyName = builder.singularCurrencyName;
        this.pluralCurrencyName = builder.pluralCurrencyName;
        this.cardinalInWords = builder.cardinalInWords;
        this.singularCentsDescription = builder.singularCentsDescription;
        this.pluralCentsDescritpion = builder.pluralCentsDescritpion;
        this.singularCentsDescriptionWhenLessOne = builder.singularCentsDescriptionWhenLessOne;
        this.pluralCentsDescritpionWhenLessOne = builder.pluralCentsDescritpionWhenLessOne;
        this.conjuction = builder.conjuction;
    }

    @Override
    public String inWords(BigDecimal value) {
        long placesMultiplier = (long) Math.pow(10, this.numberOfDecimalPlacesToCents);
        long centsValue = value
                .multiply(BigDecimal.valueOf(placesMultiplier))  // multiplica por 100 para ter a quantidade de centavos
                .intValue();

        long integerPart = centsValue / placesMultiplier;
        long centsPart = centsValue % placesMultiplier;

        String currencyDescription = this.getCurrencyDescription(integerPart, centsPart);
        String centsDescription = this.getCentsDescription(integerPart, centsPart);
        String conjuction = this.getConjuction(integerPart, centsPart);

        return currencyDescription + conjuction + centsDescription;
    }

    private String getConjuction(long integerPart, long centsPart) {
        String conjuction = "";

        if (integerPart > 0 && centsPart > 0)
            conjuction = " " + this.conjuction + " ";

        return conjuction;
    }

    private String getCurrencyDescription(long integerPart, long centsPart) {
        String currencyDescription = "";

        if (integerPart > 0)
            currencyDescription = cardinalInWords.inWords(integerPart);

        if (integerPart == 1)
            currencyDescription += " " + this.singularCurrencyName;

        if (integerPart > 1)
            currencyDescription += " " + this.pluralCurrencyName;

        return currencyDescription;
    }

    private String getCentsDescription(long integerPart, long centsPart) {
        String centsDescription = "";

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

    public static class Builder {
        private CardinalInWords cardinalInWords;
        private Integer numberOfDecimalPlacesToCents = 2;
        private String singularCurrencyName = "real";
        private String pluralCurrencyName = "reais";
        private String singularCentsDescription = "centavo";
        private String pluralCentsDescritpion = "centavos";
        private String singularCentsDescriptionWhenLessOne = "centavo de real";
        private String pluralCentsDescritpionWhenLessOne = "centavos de real";
        private String conjuction = "e";

        public Builder withConjuction(String conjuction) {
            this.conjuction = conjuction;
            return this;
        }

        public Builder withNumberOfDecimalPlacesToCents(Integer numberOfDecimalPlacesToCents) {
            this.numberOfDecimalPlacesToCents = numberOfDecimalPlacesToCents;
            return this;
        }

        public Builder withCurrencyName(String singularCurrencyName, String pluralCurrencyName) {
            this.singularCurrencyName = singularCurrencyName;
            this.pluralCurrencyName = pluralCurrencyName;
            return this;
        }

        public Builder withCardinalInWords(CardinalInWords cardinalInWords) {
            this.cardinalInWords = cardinalInWords;
            return this;
        };

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
                this.cardinalInWords = NumberInWordsFactory.createCardinalInWords()
                        .forPortugueseLanguage()
                        .withCommaSeparator()
                        .build();

            return new RealInWords(this);
        }
    }
}
