package numberinwords.portuguese;

import numberinwords.Fractional;
import numberinwords.FractionalInWords;
import numberinwords.Gender;
import numberinwords.NumberInWordsFactory;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class FractionalInPortuguese implements FractionalInWords {
    private final Gender gender;
    private final boolean useDecimalPronuntiation;
    private final int maxDecimalPlacesForDecimalPronuntiation;
    private final boolean forceToImproper;
    private final boolean forceToMixed;

    private FractionalInPortuguese(Builder builder) {
        this.gender = builder.gender;
        this.useDecimalPronuntiation = builder.useDecimalPronuntiation;
        this.maxDecimalPlacesForDecimalPronuntiation = builder.maxDecimalPlacesForDecimalPronuntiation;
        this.forceToImproper = builder.forceToImproper;
        this.forceToMixed = builder.forceToMixed;
    }

    @Override
    public String inWords(Fractional number) {
        if (this.useDecimalPronuntiation)
            return inWordsForDecimalPronuntiation(number);

        if (this.forceToMixed)
            number = number.toMixed();

        if (this.forceToImproper)
            number = number.toImproper();

        String wholePartInWords =
                Math.abs(number.getWholePart()) <= 1 ? "" :
                    NumberInWordsFactory.createCardinalBuilderChooser()
                        .forPortugueseLanguage()
                        .withGender(this.gender)
                        .build()
                        .inWords(number.getWholePart()) + " e ";

        String plural = Math.abs(number.getNumerator()) >= 2 ? "s" : "";
        String singularSuffix = gender.equals(Gender.FEMALE) ? "parte" : "avos";
        String pluralSuffix = gender.equals(Gender.FEMALE) ? "partes" : "avos";
        String suffix = Math.abs(number.getNumerator()) >= 2 ? pluralSuffix : singularSuffix;

        //previne 1/2 --> 'uma metade parte'. correto 'uma metade'
        if (Math.abs(number.getDenominator()) <= 2)
            suffix = "";

        String numeratorInWords = NumberInWordsFactory.createCardinalBuilderChooser()
                .forPortugueseLanguage()
                .withGender(this.gender)
                .build()
                .inWords(number.getNumerator());

        String denominatorInWords = getDescriptionMap().get(Math.abs(number.getDenominator()));

        if (denominatorInWords != null)
            return wholePartInWords + (
                        numeratorInWords + " " + denominatorInWords + plural +
                        (gender.equals(Gender.FEMALE) ? " " + suffix : "")
                    ).trim();

        return wholePartInWords + numeratorInWords + " " +
                NumberInWordsFactory.createDecimalUnitBuilderChooser()
                        .forPortugueseLanguage()
                        .withGender(this.gender)
                        .withUnitDescriptions(suffix, suffix)
                        .build()
                        .inWords(new BigDecimal(number.getDenominator()));
    }

    private String inWordsForDecimalPronuntiation(Fractional number) {
        return NumberInWordsFactory.createDecimalBuilderChooser()
                .forPortugueseLanguage()
                .withGender(this.gender)
                .build()
                .inWords(BigDecimal.valueOf(number.toDouble())
                        .setScale(this.maxDecimalPlacesForDecimalPronuntiation, RoundingMode.HALF_UP));
    }

    private Map<Long, String> getDescriptionMap() {
        if (gender.equals(Gender.FEMALE))
            return PortugueseFractionalDescriptions.femaleDescriptionsMap;

        return PortugueseFractionalDescriptions.maleDescriptionsMap;
    }

    public static class Builder {
        private Gender gender = Gender.MALE;
        private boolean useDecimalPronuntiation = false;
        private int maxDecimalPlacesForDecimalPronuntiation = 2;
        private boolean forceToImproper = false;
        private boolean forceToMixed = false;

        public Builder withForcingToImproper(boolean forceToImproper) {
            this.forceToImproper = forceToImproper;
            if (this.forceToImproper)
                this.forceToMixed = false;

            return this;
        }

        public Builder withForcingToMixed(boolean forceToMixed) {
            this.forceToMixed = forceToMixed;
            if (this.forceToMixed)
                this.forceToImproper = false;

            return this;
        }

        public Builder withDecimalPronuntiation(boolean useDecimalPronuntiation, int maxDecimalPlacesForDecimalPronuntiation) {
            this.useDecimalPronuntiation = useDecimalPronuntiation;
            this.maxDecimalPlacesForDecimalPronuntiation = maxDecimalPlacesForDecimalPronuntiation;
            return this;
        }

        public Builder withDecimalPronuntiation(int maxDecimalPlacesForDecimalPronuntiation) {
            return this.withDecimalPronuntiation(true, maxDecimalPlacesForDecimalPronuntiation);
        }

        public Builder withDecimalPronuntiation() {
            return this.withDecimalPronuntiation(true, this.maxDecimalPlacesForDecimalPronuntiation);
        }

        public Builder withGender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public Builder withFemaleGender() {
            return this.withGender(Gender.FEMALE);
        }

        public FractionalInPortuguese build() {
            return new FractionalInPortuguese(this);
        }
    }
}
