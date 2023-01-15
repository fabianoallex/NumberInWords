package numberinwords.portuguese;

import numberinwords.DecimalInWords;
import numberinwords.DecimalUnitInWords;
import numberinwords.Gender;
import numberinwords.NumberInWordsFactory;
import java.math.BigDecimal;

public class DecimalUnitInPortuguese extends DecimalUnitInWords {
    public DecimalUnitInPortuguese(Builder builder) {
        super(builder);

        this.integerPartInWords = NumberInWordsFactory.createCardinalInWordsBuilder()
                .forPortugueseLanguage()
                .withGender(this.gender)
                .withZeroDescription(this.zeroDescription)
                .withCommaSeparator(this.useCommaSeparator)
                .build();

        this.decimalPartInWords = NumberInWordsFactory.createDecimalInWordsBuilder()
                .forPortugueseLanguage()
                .withGender(Gender.FEMALE)
                .withOnlyDecimalPart()
                .withCommaSeparator(this.useCommaSeparator)
                .build();
    }

    @Override
    public String inWords(BigDecimal value) {
        String integerPartDescription = this.getIntegerPartInWords(value);
        String decimalPartDescription = this.getDecimalPartInWords(value);
        String conjuction = this.getConjuction(value);

        return integerPartDescription + conjuction + decimalPartDescription;
    }

    @Override
    public String getIntegerPartInWords(BigDecimal value) {
        if (value.doubleValue() == 0)
            return integerPartInWords.inWords(0L) + " " + this.singularUnit;

        long integerPart = DecimalInWords.getIntegerPart(value);
        boolean useCurrencyNameWithPreposition = (integerPart % 1000000) == 0;

        String currencyDescription = "";

        if (integerPart > 0)
            currencyDescription = integerPartInWords.inWords(integerPart);

        if (integerPart == 1)
            currencyDescription += " " +
                    (useCurrencyNameWithPreposition ?
                            this.singularUnitWithPreposition : this.singularUnit);

        if (integerPart > 1)
            currencyDescription += " " +
                    (useCurrencyNameWithPreposition ?
                            this.pluralUnitWithPreposition : this.pluralUnit);

        return currencyDescription;
    }

    @Override
    public String getDecimalPartInWords(BigDecimal value) {
        long numberOfDecimalPlaces = DecimalInWords.getNumberOfDecimalPlaces(value);
        if (numberOfDecimalPlaces > 0)
            return decimalPartInWords.inWords(value) +
                    (DecimalInWords.getIntegerPart(value) == 0 ? " " + this.singularUnitWithPreposition : "");

        return "";
    }

    @Override
    public String getConjuction(BigDecimal value) {
        if (DecimalInWords.getIntegerPart(value) > 0 && DecimalInWords.getDecimalPart(value) > 0)
            return " " + this.conjuction + " ";

        return "";
    }

    @Override
    public void setUnitWithPreposition(String singularUnit, String pluralUnit) {
        this.singularUnitWithPreposition = Builder.PREPOSITION + " " + singularUnit;
        this.pluralUnitWithPreposition = Builder.PREPOSITION + " " + pluralUnit;
    }

    public static class Builder extends DecimalUnitInWords.Builder<Builder> {
        private static final String SINGULAR_UNIT_NAME = "unidade";
        private static final String PLURAL_UNIT_NAME = "unidades";
        private static final String PREPOSITION = "de";
        private static final String CONJUCTION = "e";
        private static final String SINGULAR_UNIT_NAME_WITH_PREPOSITION = PREPOSITION + " " + SINGULAR_UNIT_NAME;
        private static final String PLURAL_UNIT_NAME_WITH_PREPOSITION = PREPOSITION + " " + PLURAL_UNIT_NAME;

        public Builder() {
            super(SINGULAR_UNIT_NAME,
                    PLURAL_UNIT_NAME,
                    SINGULAR_UNIT_NAME_WITH_PREPOSITION,
                    PLURAL_UNIT_NAME_WITH_PREPOSITION,
                    CONJUCTION);
            this.gender = Gender.FEMALE;
        }

        public Builder withUnitDescription(String singularUnit) {
            return this.withUnitDescriptions(singularUnit, singularUnit + "s");
        }

        public Builder withUnitDescriptions(String singularUnit, String pluralUnit) {
            this.singularUnit = singularUnit;
            this.pluralUnit = pluralUnit;
            this.singularUnitWithPreposition = PREPOSITION + " " + singularUnit;
            this.pluralUnitWithPreposition = PREPOSITION + " " + pluralUnit;

            return getThis();
        }

        @Override
        public Builder getThis() {
            return this;
        }

        @Override
        public DecimalUnitInPortuguese build() {
            return new DecimalUnitInPortuguese(this);
        }
    }
}
