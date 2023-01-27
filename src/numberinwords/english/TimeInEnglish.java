package numberinwords.english;

import numberinwords.NumberInWordsFactory;
import numberinwords.TimeInWords;
import java.time.LocalTime;

public class TimeInEnglish implements TimeInWords {
    private static final int minutesLimitForUsingPastAndTo = 30;
    private final boolean useSeconds;
    private final boolean useMiddayAndMidnightPronuntiation;
    private final boolean usePeriodPronuntiation;
    private final boolean useAmPm;
    private final boolean useQuarterAndHalf;
    private final boolean usePastAndToHours;
    private final boolean useOClock;
    private final boolean useUnits;
    private final boolean use24HoursFormat;
    private final boolean useNoon;
    private final boolean useOh;
    private final boolean useMilitaryFormat;
    private final boolean useAfterWordForPast;
    private final boolean useUntilWordForTo;

    private  TimeInEnglish(Builder builder) {
        this.useSeconds = builder.isUsingSeconds();
        this.useMiddayAndMidnightPronuntiation = builder.isUsingMiddayAndMidnightPronuntiation();
        this.usePeriodPronuntiation = builder.isUsingPeriodPronuntiation();
        this.useOh = builder.useOhForMinutes;
        this.usePastAndToHours = builder.usePastAndToHours;
        this.useQuarterAndHalf = builder.useQuarterAndHalf;
        this.useOClock = builder.useOClock;
        this.useUnits = builder.useUnits;
        this.use24HoursFormat = builder.use24HoursFormat;
        this.useNoon = builder.useNoon;
        this.useAmPm = builder.useAmPm;
        this.useMilitaryFormat = builder.useMilitaryFormat;
        this.useAfterWordForPast = builder.useAfterWordForPast;
        this.useUntilWordForTo = builder.useUntilWordForTo;
    }

    @Override
    public String inWords(LocalTime localTime) {
        if (!useMilitaryFormat && minuteOf(localTime).usingToOrPast())
            return minuteOf(localTime).inWords() +
                    hourOf(localTime).inWords() +
                    secondOf(localTime).inWords() +
                    periodOf(localTime).inWords();

        return hourOf(localTime).inWords() +
                minuteOf(localTime).inWords() +
                secondOf(localTime).inWords() +
                periodOf(localTime).inWords();
    }

    private Hour hourOf(LocalTime localTime) {
        if (useMilitaryFormat)
            return new MilitaryHour(localTime);

        return new Hour(localTime);
    }

    private Minute minuteOf(LocalTime localTime) {
        if (useMilitaryFormat)
            return new MilitaryMinute(localTime);

        return new Minute(localTime);
    }

    private Second secondOf(LocalTime localTime) {
        return new Second(localTime);
    }

    private PartOfTime periodOf(LocalTime localTime) {
        if (useAmPm)
            return new PeriodAmPm(localTime);

        return new Period(localTime);
    }

    private abstract class PartOfTime extends TimeInWords.PartOfTime {
        protected PartOfTime(LocalTime localTime) {
            super(localTime);
        }

        protected boolean usingUnit() {
//            if (useMiddayAndMidnightPronuntiation && new Hour(localTime).hourFor12or24Format() == 0)
//                return false;

//            if (new Hour(localTime).hourFor12or24Format() == 0)
//                return false;

            return useUnits;
        }
    }

    private class MilitaryHour extends Hour {
        protected MilitaryHour(LocalTime localTime) {
            super(localTime);
        }

        @Override
        public String inWords() {
            return getZero() + NumberInWordsFactory.createCardinalBuilderChooser()
                        .forEnglishLanguage()
                        .withZeroDescription(getZero().trim())
                        .build()
                        .inWords((long) localTime.getHour()) + this.getUnit();
        }

        private String getZero() {
            if (localTime.getHour() < 10)
                return useOh && !useUnits ? "oh " : "zero ";

            return "";
        }

        @Override
        protected String getUnit() {
            if (localTime.getMinute() == 0)
                return " hundred" + (useUnits ? " hours" : "");

            return "";
        }
    }

    private class Hour extends PartOfTime {
        protected Hour(LocalTime localTime) {
            super(localTime);
        }

        @Override
        public String inWords() {
            String middayAndMidnight = this.inWordsForMiddayMidnightAndNoonPronuntiation();

            if (!middayAndMidnight.isEmpty())
                return middayAndMidnight;

            return NumberInWordsFactory.createCardinalBuilderChooser()
                    .forEnglishLanguage()
                    .build()
                    .inWords(this.hourToBePronunced()) + this.getUnit();
        }

        protected String getUnit() {
            if (usingOClock())
                return " o'clock";

            if (usingUnit())
                return this.hourToBePronunced() < 2 ? " hour" : " hours";

            return "";
        }

        private boolean usingOClock() {
            if (!useOClock)
                return false;

            if (useSeconds && localTime.getSecond() > 0)
                return false;

            return localTime.getMinute() == 0;
        }

        private String inWordsForMiddayMidnightAndNoonPronuntiation() {
            var minute = (Minute) minuteOf(localTime);
            if (useMiddayAndMidnightPronuntiation) {
                if (minute.usingMinutesTo()) {
                    if (localTime.getHour()+1 == 24) return "midnight";
                    if (localTime.getHour()+1 == 12) return "midday";
                } else {
                    if (localTime.getHour() == 0) return "midnight";
                    if (localTime.getHour() == 12) return "midday";
                }
            }

            if (useNoon) {
                if (minute.usingMinutesTo()) {
                    if (localTime.getHour()+1 == 12) return "noon";
                } else {
                    if (localTime.getHour() == 12) return "noon";
                }
            }

            return "";
        }

        public long hourToBePronunced() {
            long hour = hourFor12or24Format();

            if (new Minute(localTime).usingTo())
                hour++;

            long maxHour = use24HoursFormat ? 23 : 12;

            if (hour > maxHour)
                hour = hour == 13 ? 1 : 0;

            return hour;
        }

        private long hourFor12or24Format() {
            return !use24HoursFormat && localTime.getHour() > 12  ? localTime.getHour()-12 : localTime.getHour();
        }
    }

    private class MilitaryMinute extends Minute {
        protected MilitaryMinute(LocalTime localTime) {
            super(localTime);
        }

        @Override
        public String inWords() {
            if (localTime.getMinute() == 0)
                return "";

            String zero = "";

            if (localTime.getMinute() < 10)
                zero = useOh && !useUnits ? "oh " : "zero ";

            return " " + zero + NumberInWordsFactory.createCardinalBuilderChooser()
                    .forEnglishLanguage()
                    .build()
                    .inWords((long) localTime.getMinute()) + getUnit();
        }

        protected String getUnit() {
            if (useUnits && localTime.getMinute() != 0)
                return " hours";

            return "";
        }
    }

    private class Minute extends PartOfTime {
        protected Minute(LocalTime localTime) {
            super(localTime);
        }

        @Override
        public String inWords() {
            if (localTime.getMinute() == 0)
                return "";

            if (usingTo())
                return inWordsForTo();

            if (usingPast())
                return inWordsForPast();

            return " " + getOh() + NumberInWordsFactory.createCardinalBuilderChooser()
                    .forEnglishLanguage()
                    .build()
                    .inWords(new Minute(localTime).minuteToBePronunced()) + this.getUnit();
        }

        private String getOh() {
            if (useOh && !useUnits && localTime.getMinute() < 10)
                return "oh ";

            return "";
        }

        private String inWordsForPast() {
            String result = "";

            if (usingQuarter() && localTime.getMinute() == 15)
                result = "a quarter";
            else if (usingHalf() && localTime.getMinute() == 30)
                result = "half";
            else
                result = NumberInWordsFactory.createCardinalBuilderChooser()
                        .forEnglishLanguage()
                        .build()
                        .inWords(minuteToBePronunced()) + this.getUnit();

            return result + (useAfterWordForPast ? " after " : " past ");
        }

        private String inWordsForTo() {
            String result = "";

            if (usingQuarter() && localTime.getMinute() == 45)
                result = "a quarter";
            else
                result = NumberInWordsFactory.createCardinalBuilderChooser()
                        .forEnglishLanguage()
                        .build()
                        .inWords(minuteToBePronunced()) + this.getUnit();

            return result + (useUntilWordForTo ? " until " : " to ");
        }

        private String getUnit() {
            if (usingUnit())
                return this.minuteToBePronunced() < 2 ? " minute" : " minutes";

            return "";
        }

        public long minuteToBePronunced() {
            if (usingTo())
                return 60 - localTime.getMinute();

            return localTime.getMinute();
        }

        private boolean usingPast() {
            if (localTime.getMinute() == 0)
                return false;

            if (usePastAndToHours && localTime.getMinute() <= minutesLimitForUsingPastAndTo)
                return true;

            return useQuarterAndHalf && localTime.getMinute() % 15 == 0;
        }

        private boolean usingTo() {
            if (localTime.getMinute() == 0)
                return false;

            if (usePastAndToHours && localTime.getMinute() >= 60 - Math.min(minutesLimitForUsingPastAndTo, 29))
                return true;

            return useQuarterAndHalf && localTime.getMinute() == 45;
        }

        private boolean usingToOrPast() {
            return usingTo() || usingPast();
        }

        private boolean usingHalf() {
            return useQuarterAndHalf && localTime.getMinute() == 30;
        }

        private boolean usingQuarter() {
            if (useQuarterAndHalf && localTime.getMinute() == 15)
                return true;

            return useQuarterAndHalf && localTime.getMinute() == 45;
        }

        public boolean usingMinutesTo() {
            return usingTo() && !usingHalf() && !usingQuarter();
        }
    }

    private class Second extends PartOfTime {
        protected Second(LocalTime localTime) {
            super(localTime);
        }

        @Override
        public String inWords() {
            if (!useSeconds || localTime.getSecond() == 0)
                return "";

            String oh = getOh();

            return (oh.isEmpty() ? " and " : " " + oh) +
                    NumberInWordsFactory.createCardinalBuilderChooser()
                            .forEnglishLanguage()
                            .build()
                            .inWords((long) localTime.getSecond()) + this.getUnit();
        }

        private String getUnit() {
            String unit = localTime.getSecond() < 2 ? " second" : " seconds";

            if (useUnits)
                return unit;

            return (getOh().isEmpty()) ? unit : "";
        }

        private String getOh() {
            if (useOh && !useUnits && localTime.getSecond() < 10)
                return "oh ";

            return "";
        }
    }

    private class PeriodAmPm extends PartOfTime {
        protected PeriodAmPm(LocalTime localTime) {
            super(localTime);
        }

        @Override
        public String inWords() {
            var hour = new Hour(localTime);

            if (!useAmPm || useMilitaryFormat || localTime.getHour() == 0 || hour.hourToBePronunced() > 12)
                return "";

            if (!hour.inWordsForMiddayMidnightAndNoonPronuntiation().isEmpty())
                return "";

            if (localTime.getHour() <= 11)
                return " AM";

            return " PM";
        }
    }

    private class Period extends PartOfTime {
        protected Period(LocalTime localTime) {
            super(localTime);
        }

        @Override
        public String inWords() {
            if (!usePeriodPronuntiation || useMilitaryFormat)
                return "";

            int hour = (localTime.getHour() + (new Minute(localTime).usingMinutesTo() ? 1 : 0)) % 24;

            if (useMiddayAndMidnightPronuntiation && (hour == 0 || hour == 12))
                return "";

            if (hour < 12) return " in the morning";
            if (hour < 18) return " in the afternoon";
            if (hour < 21) return " in the evening";
            return " at night";
        }
    }

    public static class Builder extends TimeInWords.Builder<Builder> {
        public boolean usePastAndToHours = false;
        private boolean useQuarterAndHalf = false;
        private boolean useOClock = false;
        private boolean useUnits = false;
        private boolean use24HoursFormat = false;
        private boolean useNoon = false;
        private boolean useAmPm = false;
        private boolean useMilitaryFormat = false;
        private boolean useOhForMinutes = false;
        private boolean useAfterWordForPast = false;
        private boolean useUntilWordForTo = false;

        public Builder withUntilWordForTo(boolean useUntilWordForTo) {
            this.useUntilWordForTo = useUntilWordForTo;
            return getThis();
        }

        public Builder withUntilWordForTo() {
            return this.withUntilWordForTo(true);
        }

        public Builder withAfterWordForPast(boolean useAfterWordForPast) {
            this.useAfterWordForPast = useAfterWordForPast;
            return getThis();
        }

        public Builder withAfterWordForPast() {
            return this.withAfterWordForPast(true);
        }

        public Builder withOh() {
            this.withUnits(false); //oh cant use unit
            return this.withOh(true);
        }

        public Builder withOh(boolean useOhForMinutes) {
            this.useOhForMinutes = useOhForMinutes;
            return getThis();
        }

        public Builder withPastAndToHours() {
            return this.withPastAndToHours(true);
        }

        public Builder withPastAndToHours(boolean usePastAndToHours) {
            this.usePastAndToHours = usePastAndToHours;
            return getThis();
        }

        public Builder withMilitaryFormat(boolean useMilitaryFormat) {
            this.useMilitaryFormat = useMilitaryFormat;
            return getThis();
        }

        public Builder withMilitaryFormat() {
            this.withUnits();
            return this.withMilitaryFormat(true);
        }

        public Builder withAmPm(boolean useAmPm) {
            this.useAmPm = useAmPm;
            return getThis();
        }

        public Builder withAmPm() {
            return withAmPm(true);
        }

        public Builder withNoon(boolean useNoon) {
            this.useNoon = useNoon;
            return getThis();
        }

        public Builder withNoon() {
            return this.withNoon(true);
        }

        public Builder with24HoursFormat(boolean use24HoursFormat) {
            this.use24HoursFormat = use24HoursFormat;
            return getThis();
        }

        public Builder with24HoursFormat() {
            return this.with24HoursFormat(true);
        }

        public Builder withUnits(boolean useUnits) {
            this.useUnits = useUnits;
            return getThis();
        }

        public Builder withUnits() {
            return this.withUnits(true);
        }

        public Builder withOClock(boolean useOClock) {
            this.useOClock = useOClock;
            return getThis();
        }

        public Builder withOClock() {
            return this.withOClock(true);
        }

        public Builder withQuarterAndHalf(boolean useQuarterAndHalf) {
            this.useQuarterAndHalf = useQuarterAndHalf;
            return getThis();
        }

        public Builder withQuarterAndHalf() {
            return withQuarterAndHalf(true);
        }

        @Override
        protected Builder getThis() {
            return this;
        }

        @Override
        public TimeInEnglish build() {
            return new TimeInEnglish(this);
        }
    }
}