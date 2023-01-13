package numberinwords;

import java.math.BigDecimal;

public abstract class MoneyInWords implements NumberInWords<BigDecimal> {
    protected DecimalUnitInWords decimalUnitInWords;
    protected final Integer subdivisionDecimalPlaces;
    protected final String singularCurrencyName;
    protected final String pluralCurrencyName;
    protected final String singularCentsName;
    protected final String pluralCenstName;
    protected final String singularCentsNameWhenLessOne;
    protected final String pluralCenstNameWhenLessOne;
    protected final boolean useCommaSeparator;
    protected final Gender gender;

    protected MoneyInWords(Builder builder) {
        this.subdivisionDecimalPlaces = builder.subdivisionDecimalPlaces;
        this.singularCurrencyName = builder.singularCurrencyName;
        this.pluralCurrencyName = builder.pluralCurrencyName;
        this.singularCentsName = builder.singularCentsName;
        this.pluralCenstName = builder.pluralCenstName;
        this.singularCentsNameWhenLessOne = builder.singularCentsNameWhenLessOne;
        this.pluralCenstNameWhenLessOne = builder.pluralCenstNameWhenLessOne;
        this.useCommaSeparator = builder.useCommaSeparator;
        this.gender = builder.gender;
    }

    @Override
    public String inWords(BigDecimal value) {
        if (DecimalInWords.getNumberOfDecimalPlaces(value) > this.subdivisionDecimalPlaces)
            return this.decimalUnitInWords.inWords(value);

        String integerPartInWords = this.getIntegerPartInWords(value);
        String centsInWords = this.getCentsInWords(value);
        String conjuction = this.getConjuction(value);

        return integerPartInWords + conjuction + centsInWords;
    }

    protected abstract String getIntegerPartInWords(BigDecimal value);

    protected abstract String getCentsInWords(BigDecimal value);

    protected abstract String getConjuction(BigDecimal value);

    public abstract static class Builder {
        private Integer subdivisionDecimalPlaces = 2;
        protected String singularCurrencyName;
        protected String pluralCurrencyName;
        protected String singularCentsName;
        protected String pluralCenstName;
        protected String singularCentsNameWhenLessOne;
        protected String pluralCenstNameWhenLessOne;
        protected boolean useCommaSeparator;
        protected Gender gender = Gender.MALE;

        public Integer getSubdivisionDecimalPlaces() {
            return subdivisionDecimalPlaces;
        }

        public String getSingularCurrencyName() {
            return singularCurrencyName;
        }

        public String getPluralCurrencyName() {
            return pluralCurrencyName;
        }

        public String getSingularCentsName() {
            return singularCentsName;
        }

        public String getPluralCenstName() {
            return pluralCenstName;
        }

        public String getSingularCentsNameWhenLessOne() {
            return singularCentsNameWhenLessOne;
        }

        public String getPluralCenstNameWhenLessOne() {
            return pluralCenstNameWhenLessOne;
        }

        public boolean isUsingCommaSeparator() {
            return useCommaSeparator;
        }

        public Builder withGender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public Builder withCommaSeparator() {
            this.useCommaSeparator = true;
            return this;
        }

        public Builder withCommaSeparator(boolean useCommaSeparator) {
            this.useCommaSeparator = useCommaSeparator;
            return this;
        }

        public Builder withSubdivisionDecimalPlaces(Integer decimalPlaces) {
            this.subdivisionDecimalPlaces = decimalPlaces;
            return this;
        }

        public Builder withCurrencyName(String singularCurrencyName, String pluralCurrencyName) {
            this.singularCurrencyName = singularCurrencyName;
            this.pluralCurrencyName = pluralCurrencyName;
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

        public abstract MoneyInWords build();
    }
}
