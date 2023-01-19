package numberinwords.portuguese;

import numberinwords.DecimalInWords;
import numberinwords.NumberInWordsFactory;
import java.math.BigDecimal;

public class PercentInPortuguese implements DecimalInWords {
    private PercentInPortuguese(Builder builder) {}

    @Override
    public String inWords(BigDecimal number) {
        return NumberInWordsFactory.createDecimalBuilderChooser()
                .forPortugueseLanguage()
                .withFloatPointPronuntiation()
                .build()
                .inWords(number) + " por cento";
    }

    public static class Builder {
        public PercentInPortuguese build() {
            return new PercentInPortuguese(this);
        }
    }
}
