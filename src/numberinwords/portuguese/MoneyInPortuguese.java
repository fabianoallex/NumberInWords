package numberinwords.portuguese;

import numberinwords.MoneyInWords;
import numberinwords.NumberInWordsFactory;

public class MoneyInPortuguese extends MoneyInWords {
    public MoneyInPortuguese(Builder<?> builder) {
        super(builder);
        this.decimalUnitInWordsForIntegerPart = NumberInWordsFactory.createDecimalUnitInWordsBuilder()
                .forPortugueseLanguage()
                .withGender(this.genderForIntegerPart)
                .withUnitDescriptions(builder.getSingularCurrencyName(), builder.getPluralCurrencyName())
                .withCommaSeparator(builder.isUsingCommaSeparator())
                .build();

        this.decimalUnitInWordsForCentsPart = NumberInWordsFactory.createDecimalUnitInWordsBuilder()
                .forPortugueseLanguage()
                .withGender(this.genderForCentsPart)
                .withCommaSeparator(builder.isUsingCommaSeparator())
                .build();
    }
}