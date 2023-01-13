package numberinwords;

import java.math.BigDecimal;

public abstract class DecimalUnitInWords implements DecimalInWords {
    protected CardinalInWords integerPartInWords;
    protected DecimalInWords decimalPartInWords;
    protected final String zeroDescription;
    protected final boolean useCommaSeparator;
    protected final String singularUnit;
    protected final String pluralUnit;
    protected final String singularUnitWithPreposition;
    protected final String pluralUnitWithPreposition;
    protected final String conjuction;
    protected final Gender gender;

    protected DecimalUnitInWords(Builder builder) {
        this.gender = builder.gender;
        this.useCommaSeparator = builder.useCommaSeparator;
        this.singularUnit = builder.singularUnit;
        this.pluralUnit = builder.pluralUnit;
        this.singularUnitWithPreposition = builder.singularUnitWithPreposition;
        this.pluralUnitWithPreposition = builder.pluralUnitWithPreposition;
        this.conjuction = builder.conjuction;
        this.zeroDescription = builder.zeroDescription;
    }

    @Override
    public abstract String inWords(BigDecimal value);
    public abstract String getIntegerPartInWords(BigDecimal value);
    public abstract String getDecimalPartInWords(BigDecimal value);
    public abstract String getConjuction(BigDecimal value);

    public static abstract class Builder {
        protected boolean useCommaSeparator = false;
        protected Gender gender;
        protected String zeroDescription;
        protected String singularUnit;
        protected String pluralUnit;
        protected String singularUnitWithPreposition;
        protected String pluralUnitWithPreposition;
        protected String conjuction;

        public Builder(String singularUnit, String pluralUnit,
                       String singularUnitWithPreposition, String pluralUnitWithPreposition,
                       String conjuction) {
            this.singularUnit = singularUnit;
            this.pluralUnit = pluralUnit;
            this.singularUnitWithPreposition = singularUnitWithPreposition;
            this.pluralUnitWithPreposition = pluralUnitWithPreposition;
            this.conjuction = conjuction;
        }

        public Builder withZeroDescription(String zeroDescription) {
            this.zeroDescription = zeroDescription;
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

        public Builder withFemaleGender() {
            this.gender = Gender.FEMALE;
            return this;
        }

        public Builder withMaleGender() {
            this.gender = Gender.MALE;
            return this;
        }

        public Builder withGender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public abstract DecimalInWords build();
    }
}
