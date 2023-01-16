package numberinwords.portuguese;

import numberinwords.DateInWords;
import numberinwords.NumberInWordsFactory;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

public class DateInPortuguese implements DateInWords {
    private final boolean useDay;
    private final boolean useYear;

    private DateInPortuguese(Builder builder) {
        this.useDay = builder.isUsingDay();
        this.useYear = builder.isUsingYear();
    }

    @Override
    public String inWords(LocalDate date) {
       return getDayInWords(date.getDayOfMonth()) +
               getMonthInWords(date.getMonthValue()) +
               getYearInWords(date.getYear());
    }

    private String getDayInWords(int day) {
        if (!this.useDay)
            return "";

        if (day > 1)
            return NumberInWordsFactory.createCardinalInWordsBuilderChooser()
                    .forPortugueseLanguage()
                    .build().inWords((long) day) + " de ";

        return NumberInWordsFactory.createOrdinalInWordsBuilderChooser()
                    .forPortugueseLanguage()
                    .build()
                    .inWords((long) day) + " de ";
    }

    private String getMonthInWords(int month) {
        return Month.of(month).getDisplayName(TextStyle.FULL, Locale.forLanguageTag("pt-BR"));
    }

    private String getYearInWords(int year) {
        if (!this.useYear)
            return "";

        return " de " + NumberInWordsFactory.createCardinalInWordsBuilderChooser()
                .forPortugueseLanguage()
                .build()
                .inWords((long) year);
    }

    public static class Builder extends DateInWords.Builder<Builder> {
        @Override
        protected Builder getThis() {
            return this;
        }

        @Override
        public DateInPortuguese build() {
            return new DateInPortuguese(this);
        }
    }
}
