package numberinwords.portuguese;

import numberinwords.MoneyInWords;
import numberinwords.NumberInWordsFactory;

public class MoneyInPortuguese extends MoneyInWords {
    public MoneyInPortuguese(Builder<?> builder) {
        super(builder);
        this.decimalUnitInWords = NumberInWordsFactory.createDecimalUnitInWordsBuilder()
                .forPortugueseLanguage()
                .withGender(this.gender)
                .withUnitDescriptions(builder.getSingularCurrencyName(), builder.getPluralCurrencyName())
                .withCommaSeparator(builder.isUsingCommaSeparator())
                .build();
    }
}
