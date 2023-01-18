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
    private final boolean useDecimalResult;
    private final int maxDecimalPlacesForDecimalResult;

    private FractionalInPortuguese(Builder builder) {
        this.gender = builder.gender;
        this.useDecimalResult = builder.useDecimalResult;
        this.maxDecimalPlacesForDecimalResult = builder.maxDecimalPlacesForDecimalResult;
    }

    @Override
    public String inWords(Fractional number) {
        if (this.useDecimalResult)
            return inWordsForDecimalResult(number);

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

    private String inWordsForDecimalResult(Fractional number) {
        return NumberInWordsFactory.createDecimalBuilderChooser()
                .forPortugueseLanguage()
                .withGender(this.gender)
                .build()
                .inWords(BigDecimal.valueOf(number.toDouble())
                        .setScale(this.maxDecimalPlacesForDecimalResult, RoundingMode.HALF_UP));
    }

    private Map<Long, String> getDescriptionMap() {
        if (gender.equals(Gender.FEMALE))
            return PortugueseFractionalDescriptions.femaleDescriptionsMap;

        return PortugueseFractionalDescriptions.maleDescriptionsMap;
    }

    public static class Builder {
        private Gender gender = Gender.MALE;
        private boolean useDecimalResult = false;
        private int maxDecimalPlacesForDecimalResult = 2;

        public Builder withDecimalResult(boolean useDecimalResult, int maxDecimalPlacesForDecimalResult) {
            this.useDecimalResult = useDecimalResult;
            this.maxDecimalPlacesForDecimalResult = maxDecimalPlacesForDecimalResult;
            return this;
        }

        public Builder withDecimalResult(int maxDecimalPlacesForDecimalResult) {
            return this.withDecimalResult(true, maxDecimalPlacesForDecimalResult);
        }

        public Builder withDecimalResult() {
            return this.withDecimalResult(true, this.maxDecimalPlacesForDecimalResult);
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
