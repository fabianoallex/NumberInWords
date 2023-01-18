package numberinwords.portuguese;

import numberinwords.Fractional;
import numberinwords.FractionalInWords;
import numberinwords.Gender;
import numberinwords.NumberInWordsFactory;

import java.math.BigDecimal;
import java.util.Map;

public class FractionalInPortuguese implements FractionalInWords {
    private final Gender gender;

    private FractionalInPortuguese(Builder builder) {
        this.gender = builder.gender;
    }

    @Override
    public String inWords(Fractional number) {
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
            return (numeratorInWords + " " + denominatorInWords + plural +
                    (gender.equals(Gender.FEMALE) ? " " + suffix : "")).trim();

        return numeratorInWords + " " +
                NumberInWordsFactory.createDecimalUnitBuilderChooser()
                        .forPortugueseLanguage()
                        .withGender(this.gender)
                        .withUnitDescriptions(suffix, suffix)
                        .build()
                        .inWords(new BigDecimal(number.getDenominator()));
    }

    private Map<Long, String> getDescriptionMap() {
        if (gender.equals(Gender.FEMALE))
            return PortugueseFractionalDescriptions.femaleDescriptionsMap;

        return PortugueseFractionalDescriptions.maleDescriptionsMap;
    }

    public static class Builder {
        private Gender gender = Gender.MALE;

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
