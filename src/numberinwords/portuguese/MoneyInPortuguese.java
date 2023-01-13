package numberinwords.portuguese;

import numberinwords.DecimalInWords;
import numberinwords.MoneyInWords;
import numberinwords.NumberInWordsFactory;
import java.math.BigDecimal;

public class MoneyInPortuguese extends MoneyInWords {
    protected MoneyInPortuguese(Builder builder) {
        super(builder);
        this.decimalUnitInWords = NumberInWordsFactory.createDecimalUnitInWordsBuilder()
                .forPortugueseLanguage()
                .withMaleGender()
                .withUnitDescriptions(builder.getSingularCurrencyName(), builder.getPluralCurrencyName())
                .withCommaSeparator(builder.isUsingCommaSeparator())
                .build();
    }

    @Override
    protected String getIntegerPartDescription(BigDecimal value) {
        return this.decimalUnitInWords.getIntegerPartInWords(value);
    }

    @Override
    protected String getConjuction(BigDecimal value) {
        if (DecimalInWords.getIntegerPart(value) > 0 && DecimalInWords.getDecimalPart(value) > 0)
            return decimalUnitInWords.getConjuction(value);

        return "";
    }

    @Override
    protected String getCentsDescription(BigDecimal value) {
        String centsDescription = "";

        long integerPart = DecimalInWords.getIntegerPart(value);
        long centsPart = DecimalInWords.getDecimalPart(value);
        int numberOfDecimalPlaces = DecimalInWords.getNumberOfDecimalPlaces(value);

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

    public static class Builder extends MoneyInWords.Builder {
        @Override
        public Builder withCommaSeparator() {
            return (Builder) super.withCommaSeparator();
        }

        @Override
        public Builder withCommaSeparator(boolean useCommaSeparator) {
            return (Builder) super.withCommaSeparator(useCommaSeparator);
        }

        @Override
        public Builder withSubdivisionDecimalPlaces(Integer decimalPlaces) {
            return (Builder) super.withSubdivisionDecimalPlaces(decimalPlaces);
        }

        @Override
        public Builder withCurrencyName(String singularCurrencyName, String pluralCurrencyName) {
            return (Builder) super.withCurrencyName(singularCurrencyName, pluralCurrencyName);
        }

        @Override
        public Builder withCentsName(String singularCentsName, String pluralCentsName) {
            return (Builder) super.withCentsName(singularCentsName, pluralCentsName);
        }

        @Override
        public Builder withCentsNameWhenLessOne(String singularCentsName, String pluralCentsName) {
            return (Builder) super.withCentsNameWhenLessOne(singularCentsName, pluralCentsName);
        }

        @Override
        public MoneyInPortuguese build() {
            return new MoneyInPortuguese(this);
        }
    }
}
