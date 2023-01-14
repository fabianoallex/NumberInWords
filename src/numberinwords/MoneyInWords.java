package numberinwords;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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

    protected MoneyInWords(Builder<? extends MoneyInWords> builder) {
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

    protected String getIntegerPartInWords(BigDecimal value) {
        return this.decimalUnitInWords.getIntegerPartInWords(value);
    }

    protected String getCentsInWords(BigDecimal value) {
        long centsPart = this.calcCentsPart(value);

        if (centsPart == 0)
            return "";

        long integerPart = DecimalInWords.getIntegerPart(value);
        var centsInWords = NumberInWordsFactory.createDecimalUnitInWordsBuilder()
                .forPortugueseLanguage()
                .withGender(Gender.MALE)
                .withUnitDescriptions(this.getSingularCentsName(integerPart), this.getPluralCentsName(integerPart))
                .withCommaSeparator(this.useCommaSeparator)
                .build();

        return centsInWords.inWords(BigDecimal.valueOf(centsPart));
    }

    public long calcCentsPart(BigDecimal value) {
        int differenceDecimalPlaces = subdivisionDecimalPlaces - DecimalInWords.getNumberOfDecimalPlaces(value);
        return  (long) (DecimalInWords.getDecimalPart(value) * Math.pow(10, differenceDecimalPlaces));
    }

    private String getSingularCentsName(long integerPart) {
        if (integerPart == 0)
            return this.singularCentsNameWhenLessOne;

        return this.singularCentsName;
    }

    private String getPluralCentsName(long integerPart) {
        if (integerPart == 0)
            return  this.pluralCenstNameWhenLessOne;

        return this.pluralCenstName;
    }

    protected String getConjuction(BigDecimal value) {
        if (DecimalInWords.getIntegerPart(value) > 0 && DecimalInWords.getDecimalPart(value) > 0)
            return decimalUnitInWords.getConjuction(value);

        return "";
    }

    public static class Builder<T extends MoneyInWords> {
        private final Class<T> clazz;
        private Integer subdivisionDecimalPlaces = 2;
        protected String singularCurrencyName;
        protected String pluralCurrencyName;
        protected String singularCentsName;
        protected String pluralCenstName;
        protected String singularCentsNameWhenLessOne;
        protected String pluralCenstNameWhenLessOne;
        protected boolean useCommaSeparator;
        protected Gender gender = Gender.MALE;

        public Builder(Class<T> clazz) {
            this.clazz = clazz;
        }

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

        public Builder<T> withGender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public Builder<T> withCommaSeparator() {
            this.useCommaSeparator = true;
            return this;
        }

        public Builder<T> withCommaSeparator(boolean useCommaSeparator) {
            this.useCommaSeparator = useCommaSeparator;
            return this;
        }

        public Builder<T> withSubdivisionDecimalPlaces(Integer decimalPlaces) {
            this.subdivisionDecimalPlaces = decimalPlaces;
            return this;
        }

        public Builder<T> withCurrencyName(String singularCurrencyName, String pluralCurrencyName) {
            this.singularCurrencyName = singularCurrencyName;
            this.pluralCurrencyName = pluralCurrencyName;
            return this;
        }

        public Builder<T> withCentsName(String singularCentsName, String pluralCentsName) {
            this.singularCentsName = singularCentsName;
            this.pluralCenstName = pluralCentsName;
            return this;
        }

        public Builder<T> withCentsNameWhenLessOne(String singularCentsName, String pluralCentsName) {
            this.singularCentsNameWhenLessOne = singularCentsName;
            this.pluralCenstNameWhenLessOne = pluralCentsName;
            return this;
        }

        public T build() {
            Constructor<T> constructor = null;
            try {
                constructor = this.clazz.getConstructor(Builder.class);
                return constructor.newInstance(this);
            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                     IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
