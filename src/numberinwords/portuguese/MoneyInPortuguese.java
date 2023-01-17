package numberinwords.portuguese;

import numberinwords.MoneyInWords;
import numberinwords.NumberInWordsFactory;

public class MoneyInPortuguese extends MoneyInWords {
    public MoneyInPortuguese(Builder<?> builder) {
        super(builder);
        this.decimalUnitInWordsForIntegerPart = NumberInWordsFactory.createDecimalUnitBuilderChooser()
                .forPortugueseLanguage()
                .withGender(this.genderForIntegerPart)
                .withUnitDescriptions(builder.getSingularCurrencyName(), builder.getPluralCurrencyName())
                .withCommaSeparator(builder.isUsingCommaSeparator())
                .build();

        this.decimalUnitInWordsForCentsPart = NumberInWordsFactory.createDecimalUnitBuilderChooser()
                .forPortugueseLanguage()
                .withGender(this.genderForCentsPart)
                .withCommaSeparator(builder.isUsingCommaSeparator())
                .build();
    }
}