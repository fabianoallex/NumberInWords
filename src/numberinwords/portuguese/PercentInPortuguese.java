package numberinwords.portuguese;

import numberinwords.DecimalInWords;
import numberinwords.NumberInWordsFactory;
import java.math.BigDecimal;

public class PercentInPortuguese implements DecimalInWords {
    @Override
    public String inWords(BigDecimal number) {
        return NumberInWordsFactory.createDecimalBuilderChooser().forPortugueseLanguage()
                .withFloatPointPronuntiation()
                .build()
                .inWords(number) + " por cento";
    }
}
