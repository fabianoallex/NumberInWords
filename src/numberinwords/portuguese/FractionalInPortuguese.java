package numberinwords.portuguese;

import numberinwords.Fractional;
import numberinwords.FractionalInWords;
import numberinwords.Gender;
import numberinwords.NumberInWordsFactory;

import java.math.BigDecimal;
import java.util.concurrent.RecursiveTask;

public class FractionalInPortuguese implements FractionalInWords {
    private FractionalInPortuguese(Builder builder) {

    }

    @Override
    public String inWords(Fractional number) {
        String plural = Math.abs(number.getNumerator()) >= 2 ? "s" : "";

        String numeratorInWords = NumberInWordsFactory.createCardinalBuilderChooser()
                .forPortugueseLanguage()
                .build()
                .inWords(number.getNumerator());

        String denominatorInWords = PortugueseFractionalDescriptions.descriptionsMap.get(Math.abs(number.getDenominator()));

        if (denominatorInWords != null)
            return numeratorInWords + " " + denominatorInWords + plural;

        return numeratorInWords + " " +
                NumberInWordsFactory.createDecimalUnitBuilderChooser()
                        .forPortugueseLanguage()
                        .withGender(Gender.MALE)
                        .withUnitDescriptions("avos", "avos")
                        .build()
                        .inWords(new BigDecimal(number.getDenominator()));
    }

    public static class Builder {
        public FractionalInPortuguese build() {
            return new FractionalInPortuguese(this);
        }
    }
}
