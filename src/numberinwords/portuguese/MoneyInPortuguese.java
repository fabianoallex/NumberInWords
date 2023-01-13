package numberinwords.portuguese;

import numberinwords.Gender;
import numberinwords.NumberInWordsFactory;
import java.math.BigDecimal;

public class MoneyInPortuguese extends DecimalUnitInPortuguese {
    private final String singularCentsName;
    private final String pluralCenstName;
    private final String singularCentsNameWhenLessOne;
    private final String pluralCenstNameWhenLessOne;

    public MoneyInPortuguese(Builder builder) {
        super(builder.superBuilder);
        this.singularCentsName = builder.singularCentsName;
        this.pluralCenstName = builder.pluralCenstName;
        this.singularCentsNameWhenLessOne = builder.singularCentsNameWhenLessOne;
        this.pluralCenstNameWhenLessOne = builder.pluralCenstNameWhenLessOne;
    }

    @Override
    protected String getDecimalPartDescription(BigDecimal value) {
        if (this.getNumberOfDecimalPlaces(value) > 2)
            return super.getDecimalPartDescription(value);

        return this.getCentsDescription(value);
    }

    private String getCentsDescription(BigDecimal value) {
        String centsDescription = "";

        long integerPart = this.getIntegerPart(value);
        long centsPart = this.getDecimalPart(value);
        int numberOfDecimalPlaces = getNumberOfDecimalPlaces(value);

        if (numberOfDecimalPlaces == 1)
            centsPart = centsPart * 10;

        var cardinalInWords = NumberInWordsFactory.createCardinalInWordsBuilder()
                .forPortugueseLanguage()
                .withCommaSeparator(this.useCommaSeparator)
                .build();

        if (centsPart > 0)
            centsDescription = cardinalInWords.inWords(centsPart);

        if (integerPart == 0 && centsPart == 1)
            centsDescription += " " + this.singularCentsNameWhenLessOne;

        if (integerPart == 0 && centsPart > 1)
            centsDescription += " " + this.pluralCenstNameWhenLessOne;

        if (integerPart > 0 && centsPart == 1)
            centsDescription += " " + this.singularCentsName;

        if (integerPart > 0 && centsPart > 1)
            centsDescription += " " + this.pluralCenstName;

        return centsDescription;
    }

    public static class Builder {
        private final DecimalUnitInPortuguese.Builder superBuilder;
        private String singularCentsName;
        private String pluralCenstName;
        private String singularCentsNameWhenLessOne;
        private String pluralCenstNameWhenLessOne;

        public Builder() {
            superBuilder = new DecimalUnitInPortuguese.Builder();
            superBuilder.withMaleGender();
        }

        public Builder withCurrencyName(String singularCurrencyName, String pluralCurrencyName) {
            superBuilder.withUnitDescriptions(singularCurrencyName, pluralCurrencyName);
            return this;
        }

        public Builder withCentsName(String singularCentsName, String pluralCentsName) {
            this.singularCentsName = singularCentsName;
            this.pluralCenstName = pluralCentsName;
            return this;
        }

        public Builder withCentsNameWhenLessOne(String singularCentsName, String pluralCentsName) {
            this.singularCentsNameWhenLessOne = singularCentsName;
            this.pluralCenstNameWhenLessOne = pluralCentsName;
            return this;
        }

        public Builder withCommaSeparator() {
            this.superBuilder.withCommaSeparator();
            return this;
        }

        public Builder withGender(Gender gender) {
            this.superBuilder.withGender(gender);
            return this;
        }

        public MoneyInPortuguese build() {
            return new MoneyInPortuguese(this);
        }
    }
}
