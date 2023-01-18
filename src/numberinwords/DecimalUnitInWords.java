package numberinwords;

import java.math.BigDecimal;

public abstract class DecimalUnitInWords implements DecimalInWords {
    protected CardinalInWords integerPartInWords;
    protected DecimalInWords decimalPartInWords;
    protected String singularUnit;
    protected String pluralUnit;
    protected String singularUnitWithPreposition;
    protected String pluralUnitWithPreposition;
    protected final String zeroDescription;
    protected final boolean useCommaSeparator;
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
    public abstract void setUnitWithPreposition(String singularUnit, String pluralUnit);

    public void setUnit(String singularUnit, String pluralUnit) {
        this.singularUnit = singularUnit;
        this.pluralUnit = pluralUnit;

        this.setUnitWithPreposition(singularUnit, pluralUnit);
    }

    public static abstract class Builder<T extends Builder<T>> {
        protected boolean useCommaSeparator = false;
        protected Gender gender;
        protected String zeroDescription;
        protected String singularUnit;
        protected String pluralUnit;
        protected String singularUnitWithPreposition;
        protected String pluralUnitWithPreposition;
        protected String conjuction;

        protected abstract T getThis();
        public abstract DecimalInWords build();

        public Builder(String singularUnit,
                       String pluralUnit,
                       String singularUnitWithPreposition,
                       String pluralUnitWithPreposition,
                       String conjuction) {
            this.singularUnit = singularUnit;
            this.pluralUnit = pluralUnit;
            this.singularUnitWithPreposition = singularUnitWithPreposition;
            this.pluralUnitWithPreposition = pluralUnitWithPreposition;
            this.conjuction = conjuction;
        }

        public T withZeroDescription(String zeroDescription) {
            this.zeroDescription = zeroDescription;
            return getThis();
        }

        public T withCommaSeparator(boolean useCommaSeparator) {
            this.useCommaSeparator = useCommaSeparator;
            return getThis();
        }

        public T withCommaSeparator() {
            this.useCommaSeparator = true;
            return getThis();
        }

        public T withFemaleGender() {
            this.gender = Gender.FEMALE;
            return getThis();
        }

        public T withMaleGender() {
            this.gender = Gender.MALE;
            return getThis();
        }

        public T withGender(Gender gender) {
            this.gender = gender;
            return getThis();
        }
    }
}
