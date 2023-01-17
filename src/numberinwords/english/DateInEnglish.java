package numberinwords.english;

import numberinwords.DateInWords;
import numberinwords.NumberInWordsFactory;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

public class DateInEnglish  implements DateInWords {
    private final boolean useDay;
    private final boolean useYear;
    private final boolean useOfAfterDay;
    private final boolean useTheBeforeDay;
    private final boolean useMonthFirst;
    private final boolean useCardinalForDay;

    private DateInEnglish(Builder builder) {
        this.useDay = builder.isUsingDay();
        this.useYear = builder.isUsingYear();
        this.useOfAfterDay  = builder.useOfAfterDay;
        this.useTheBeforeDay = builder.useTheBeforeDay;
        this.useMonthFirst = builder.useMonthFirst;
        this.useCardinalForDay = builder.useCardinalForDay;
    }

    @Override
    public String inWords(LocalDate date) {
        if (this.useMonthFirst)
            return getMonthInWords(date.getMonthValue()) +
                    getDayInWords(date.getDayOfMonth()) +
                    getYearInWords(date.getYear());

        return getDayInWords(date.getDayOfMonth()) +
                getMonthInWords(date.getMonthValue()) +
                getYearInWords(date.getYear());
    }

    private String getDayInWords(int day) {
        if (!this.useDay)
            return "";

        String the = useTheBeforeDay ? "the " : "";
        String of = useOfAfterDay && !useMonthFirst ? " of" : "";
        String initialSpace = useMonthFirst ? " " : "";
        String finalSpace = useMonthFirst ? "" : " ";

        if (this.useCardinalForDay)
            return initialSpace + the + NumberInWordsFactory.createCardinalBuilderChooser()
                    .forEnglishLanguage()
                    .build()
                    .inWords((long) day) + of + finalSpace;

        return initialSpace + the + NumberInWordsFactory.createOrdinalBuilderChooser()
                .forEnglishLanguage()
                .build()
                .inWords((long) day) + of + finalSpace;
    }

    private String getMonthInWords(int month) {
        return Month.of(month).getDisplayName(TextStyle.FULL, Locale.forLanguageTag("en-US"));
    }

    private String getYearInWords(int year) {
        if (!this.useYear)
            return "";

        return " " + NumberInWordsFactory.createCardinalBuilderChooser()
                .forEnglishLanguage()
                .build()
                .inWords((long) year);
    }

    public static class Builder extends DateInWords.Builder<Builder> {
        private boolean useOfAfterDay = false;
        private boolean useTheBeforeDay = false;
        private boolean useMonthFirst = false;
        private boolean useCardinalForDay = false;

        public Builder withCardinalForDay(boolean useCardinalForDay) {
            this.useCardinalForDay = useCardinalForDay;
            return getThis();
        }

        public Builder withCardinalForDay() {
            return this.withCardinalForDay(true);
        }

        public Builder withOfAfterDay(boolean useOfAfterDay) {
            this.useOfAfterDay = useOfAfterDay;
            return getThis();
        }

        public Builder withOfAfterDay() {
            return this.withOfAfterDay(true);
        }

        public Builder withMonthFirst(boolean useMonthFirst) {
            this.useMonthFirst = useMonthFirst;
            return getThis();
        }

        public Builder withMonthFirst() {
            return this.withMonthFirst(true);
        }

        public Builder withTheBeforeDay(boolean useTheBeforeDay) {
            this.useTheBeforeDay = useTheBeforeDay;
            return getThis();
        }

        public Builder withTheBeforeDay() {
            return this.withTheBeforeDay(true);
        }

        @Override
        protected Builder getThis() {
            return this;
        }

        @Override
        public DateInEnglish build() {
            return new DateInEnglish(this);
        }
    }
}
