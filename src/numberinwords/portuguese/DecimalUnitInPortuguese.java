package numberinwords.portuguese;

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
                .withOnlyDecimalPart()
                .withCommaSeparator(this.useCommaSeparator)
                .build();
    }

    @Override
    public String inWords(BigDecimal value) {
        String integerPartDescription = this.getIntegerPartDescription(value);
        String decimalPartDescription = this.getDecimalPartDescription(value);
        String conjuction = this.getConjuction(value);

        return integerPartDescription + conjuction + decimalPartDescription;
    }

    @Override
    protected String getIntegerPartDescription(BigDecimal value) {
        if (value.doubleValue() == 0)
            return integerPartInWords.inWords(0L) + " " + this.singularUnit;

        long integerPart = this.getIntegerPart(value);
        boolean useCurrencyNameWithPreposition = (integerPart % 1000000) == 0; //um milhão de real, um bilhão de real

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
        ;

        return currencyDescription;
    }

    @Override
    protected String getDecimalPartDescription(BigDecimal value) {
        int numberOfDecimalPlaces = getNumberOfDecimalPlaces(value);
        if (numberOfDecimalPlaces > 0)
            return decimalPartInWords.inWords(value) +
                    (this.getIntegerPart(value) == 0 ? " " + this.singularUnitWithPreposition : "");

        return "";
    }

    @Override
    protected String getConjuction(BigDecimal value) {
        if (this.getIntegerPart(value) > 0 && this.getDecimalPart(value) > 0)
            return " " + this.conjuction + " ";

        return "";
    }

    public static class Builder extends DecimalUnitInWords.Builder {
        private static final String SINGULAR_UNIT_NAME = "unidade";
        private static final String PLURAL_UNIT_NAME = "unidades";
        private static final String PREPOSITION = "de";
        private static final String CONJUCTION = "e";
        private static final String SINGULAR_UNIT_NAME_WITH_PREPOSITION = PREPOSITION + " " + SINGULAR_UNIT_NAME;
        private static final String PLURAL_UNIT_NAME_WITH_PREPOSITION = PREPOSITION + " " + PLURAL_UNIT_NAME;

        public Builder() {
            super(SINGULAR_UNIT_NAME, PLURAL_UNIT_NAME,
                    SINGULAR_UNIT_NAME_WITH_PREPOSITION, PLURAL_UNIT_NAME_WITH_PREPOSITION,
                    CONJUCTION);
            this.gender = Gender.FEMALE;
        }

        public Builder withUnitDescription(String singularUnit) {
            return this.withUnitDescriptions(singularUnit, singularUnit + "s");
        }

        public Builder withUnitDescriptions(String singularUnit, String pluralUnit) {
            this.singularUnit = singularUnit;
            this.pluralUnit = pluralUnit;
            this.singularUnitWithPreposition = "de " + singularUnit;
            this.pluralUnitWithPreposition = "de " + pluralUnit;

            return this;
        }

        @Override
        public Builder withZeroDescription(String zeroDescription) {
            return (Builder) super.withZeroDescription(zeroDescription);
        }

        @Override
        public Builder withCommaSeparator(boolean useCommaSeparator) {
            return (Builder) super.withCommaSeparator(useCommaSeparator);
        }

        @Override
        public Builder withCommaSeparator() {
            return (Builder) super.withCommaSeparator();
        }

        @Override
        public Builder withFemaleGender() {
            return (Builder) super.withFemaleGender();
        }

        @Override
        public Builder withMaleGender() {
            return (Builder) super.withMaleGender();
        }

        @Override
        public Builder withGender(Gender gender) {
            return (Builder) super.withGender(gender);
        }

        @Override
        public DecimalUnitInPortuguese build() {
            return new DecimalUnitInPortuguese(this);
        }
    }
}
