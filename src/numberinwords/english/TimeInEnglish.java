package numberinwords.english;

import numberinwords.NumberInWordsFactory;
import numberinwords.TimeInWords;
import java.time.LocalTime;

public class TimeInEnglish implements TimeInWords {
    private final boolean useSeconds;
    private final boolean useMiddayAndMidnightPronuntiation;
    private final boolean usePeriodPronuntiation;
    private final boolean useQuarterAndHalf;
    private final boolean usePastAndToHours;
    private static final int minutesLimitForUsingPastAndTo = 30;
    private final boolean useOClock;
    private final boolean useUnits;
    private final boolean use24HoursFormat;
    private final boolean useNoon;
    private final boolean useAmPm;
    private final boolean useMilitarFormat;

    private  TimeInEnglish(Builder builder) {
        this.useSeconds = builder.isUsingSeconds();
        this.useMiddayAndMidnightPronuntiation = builder.isUsingMiddayAndMidnightPronuntiation();
        this.usePeriodPronuntiation = builder.isUsingPeriodPronuntiation();
        this.usePastAndToHours = builder.usePastAndToHours;
        this.useQuarterAndHalf = builder.useQuarterAndHalf;
        this.useOClock = builder.useOClock;
        this.useUnits = builder.useUnits;
        this.use24HoursFormat = builder.use24HoursFormat;
        this.useNoon = builder.useNoon;
        this.useAmPm = builder.useAmPm;
        this.useMilitarFormat = builder.useMilitarFormat;
    }

    @Override
    public String inWords(LocalTime localTime) {
        if (minuteOf(localTime).canUseHalfMinutesToOrPast())
            return inWordsForMinutesToAndPastHour(localTime);

        return hourOf(localTime).inWords() +
                minuteOf(localTime).inWords() +
                secondOf(localTime).inWords() +
                periodOf(localTime).inWords();
    }

    private String inWordsForMinutesToAndPastHour(LocalTime localTime) {
        return minuteOf(localTime).inWords() +
                hourOf(localTime).inWords() +
                secondOf(localTime).inWords() +
                periodOf(localTime).inWords();
    }

    private Hour hourOf(LocalTime localTime) {
        return new Hour(localTime);
    }

    private Minute minuteOf(LocalTime localTime) {
        return new Minute(localTime);
    }

    private Second secondOf(LocalTime localTime) {
        return new Second(localTime);
    }

    private Period periodOf(LocalTime localTime) {
        return new Period(localTime);
    }

    private abstract class PartOfTime extends TimeInWords.PartOfTime {
        protected PartOfTime(LocalTime localTime) {
            super(localTime);
        }

        protected boolean checkUnitNeedToBeUsed(LocalTime localTime) {
            if (useMiddayAndMidnightPronuntiation && hourOf(localTime).hourFor12or24Format() == 0)
                return false;

            return useUnits;
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
                    .inWords(this.hourToPronuntiate()) + this.getUnit();
        }

        private String getUnit() {
            if (canUseOClock())
                return " o'clock";

            if (checkUnitNeedToBeUsed(localTime))
                return this.hourToPronuntiate() < 2 ? " hour" : " hours";

            return "";
        }

        private boolean canUseOClock() {
            if (!useOClock)
                return false;

            if (useSeconds && localTime.getSecond() > 0)
                return false;

            return localTime.getMinute() == 0;
        }

        private String inWordsForMiddayMidnightAndNoonPronuntiation() {
            if (useMiddayAndMidnightPronuntiation) {
                if (minuteOf(localTime).canUseMinutesToPronunciation()) {
                    if (localTime.getHour()+1 == 24) return "midnight";
                    if (localTime.getHour()+1 == 12) return "midday";
                } else {
                    if (localTime.getHour() == 0) return "midnight";
                    if (localTime.getHour() == 12) return "midday";
                }
            }

            if (useNoon) {
                if (minuteOf(localTime).canUseMinutesToPronunciation()) {
                    if (localTime.getHour()+1 == 12) return "noon";
                } else {
                    if (localTime.getHour() == 12) return "noon";
                }
            }

            return "";
        }

        public long hourToPronuntiate() {
            long hour = hourFor12or24Format();

            if (minuteOf(localTime).usingTo())
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

            return this.calcBeforMinute() + NumberInWordsFactory.createCardinalBuilderChooser()
                    .forEnglishLanguage()
                    .build()
                    .inWords(minuteOf(localTime).minuteToPronuntiate()) + this.getUnit();
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
                        .inWords(minuteToPronuntiate());

            return result + " past ";
        }

        private String inWordsForTo() {
            String result = "";

            if (usingQuarter() && localTime.getMinute() == 45)
                result = "a quarter";
            else
                result = NumberInWordsFactory.createCardinalBuilderChooser()
                        .forEnglishLanguage()
                        .build()
                        .inWords(minuteToPronuntiate());

            return result + " to ";
        }

        private String calcBeforMinute() {
            return " ";
        }

        private String getUnit() {
            if (checkUnitNeedToBeUsed(localTime))
                return this.minuteToPronuntiate() < 2 ? " minute" : " minutes";

            return "";
        }

        public long minuteToPronuntiate() {
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

        public boolean usingMinutesPast() {
            return usingPast() && !usingHalf() && !usingQuarter();
        }

        private boolean canUseMinutesToPronunciation() {
            return (usePastAndToHours) && localTime.getMinute() >= 60 - Math.min(minutesLimitForUsingPastAndTo, 29);
        }

        private boolean canUseMinutesPastPronunciation() {
            return usePastAndToHours && localTime.getMinute() <= minutesLimitForUsingPastAndTo;
        }

        private boolean canUseQuarterOrHalf() {
            if (useQuarterAndHalf && localTime.getMinute() > 0)
                return localTime.getMinute() % 15 == 0;

            return false;
        }

        private boolean canUseHalfMinutesToOrPast() {
            return canUseQuarterOrHalf() || canUseMinutesPastPronunciation() || canUseMinutesToPronunciation();
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

            return " and " +
                    NumberInWordsFactory.createCardinalBuilderChooser()
                            .forEnglishLanguage()
                            .build()
                            .inWords((long) localTime.getSecond()) + this.getUnit();
        }

        private String getUnit() {
            return localTime.getSecond() < 2 ? " second" : " seconds";
        }
    }

    private class Period extends PartOfTime {
        protected Period(LocalTime localTime) {
            super(localTime);
        }

        @Override
        public String inWords() {
            if (useAmPm)
                return getAmPm();

            if (!usePeriodPronuntiation)
                return "";

            int hour = (localTime.getHour() + (minuteOf(localTime).canUseMinutesToPronunciation() ? 1 : 0)) % 24;

            if (useMiddayAndMidnightPronuntiation && (hour == 0 || hour == 12))
                return "";

            if (hour < 12) return " in the morning";
            if (hour < 18) return " in the afternoon";
            if (hour < 21) return " in the evening";
            return " at night";
        }

        private String getAmPm() {
            if (!useAmPm)
                return "";

            if (!hourOf(localTime).inWordsForMiddayMidnightAndNoonPronuntiation().isEmpty())
                return "";

            if (localTime.getHour() <= 11)
                return " AM";

            return " PM";
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
        private boolean useMilitarFormat = false;

        protected Builder withPastAndToHours() {
            return this.withPastAndToHours(true);
        }

        protected Builder withPastAndToHours(boolean usePastAndToHours) {
            this.usePastAndToHours = usePastAndToHours;
            return getThis();
        }

        protected Builder withMilitarFormat(boolean useMilitarFormat) {
            this.useMilitarFormat = useMilitarFormat;
            return getThis();
        }

        protected Builder withMilitarFormat() {
            return this.withMilitarFormat(true);
        }

        protected Builder withAmPm(boolean useAmPm) {
            this.useAmPm = useAmPm;
            return getThis();
        }

        protected Builder withAmPm() {
            return withAmPm(true);
        }

        protected Builder withNoon(boolean useNoon) {
            this.useNoon = useNoon;
            return getThis();
        }

        protected Builder withNoon() {
            return this.withNoon(true);
        }

        protected Builder with24HoursFormat(boolean use24HoursFormat) {
            this.use24HoursFormat = use24HoursFormat;
            return getThis();
        }

        protected Builder with24HoursFormat() {
            return this.with24HoursFormat(true);
        }

        protected Builder withUnits(boolean useUnits) {
            this.useUnits = useUnits;
            return getThis();
        }

        protected Builder withUnits() {
            return this.withUnits(true);
        }

        protected Builder withOClock(boolean useOClock) {
            this.useOClock = useOClock;
            return getThis();
        }

        protected Builder withOClock() {
            return this.withOClock(true);
        }

        protected Builder withQuarterAndHalf(boolean useQuarterAndHalf) {
            this.useQuarterAndHalf = useQuarterAndHalf;
            return getThis();
        }

        protected Builder withQuarterAndHalf() {
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