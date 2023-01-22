package numberinwords.portuguese;

import numberinwords.NumberInWordsFactory;
import numberinwords.TimeInWords;
import java.time.LocalTime;

public class TimeInPortuguese implements TimeInWords {
    private final boolean useSeconds;
    private final boolean use12HoursFormat;
    private final boolean useInformalPronuntiation;
    private final boolean useMiddayAndMidnightPronuntiation;
    private final boolean useHalfFor30Minutes;
    private final boolean useMinutesToHourPronuntiation;
    private final boolean usePeriodPronuntiation;

    public TimeInPortuguese(Builder builder) {
        this.useSeconds = builder.isUsingSeconds();
        this.useMiddayAndMidnightPronuntiation = builder.isUsingMiddayAndMidnightPronuntiation();
        this.usePeriodPronuntiation = builder.isUsingPeriodPronuntiation();
        this.use12HoursFormat = builder.use12HoursFormat;
        this.useInformalPronuntiation = builder.useInformalPronuntiation;
        this.useHalfFor30Minutes = builder.useHalfFor30Minutes;
        this.useMinutesToHourPronuntiation = builder.useMinutesToHourPronuntiation;
    }

    @Override
    public String inWords(LocalTime localTime) {
        if (this.canUseMinutesToHourPronunciation(localTime))
            return minuteOf(localTime).inWords() +
                    hourOf(localTime).inWords() +
                    periodOf(localTime).inWords();

        return hourOf(localTime).inWords() +
                minuteOf(localTime).inWords() +
                secondOf(localTime).inWords() +
                periodOf(localTime).inWords();
    }

    private boolean canUseMinutesToHourPronunciation(LocalTime localTime) {
        return useMinutesToHourPronuntiation && localTime.getMinute() >= 40;
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

    private abstract class PartOfTime {
        protected final LocalTime localTime;

        public abstract String inWords();

        protected PartOfTime(LocalTime localTime) {
            this.localTime = localTime;
        }

        protected boolean checkUnitNeedToBeUsed(LocalTime localTime) {
            return (localTime.getMinute() == 0 ||
                    !useInformalPronuntiation ||
                    (!useMiddayAndMidnightPronuntiation && hourOf(localTime).hourFor12or24Format() == 0));

        }
    }

    private class Hour extends PartOfTime {
        protected Hour(LocalTime localTime) {
            super(localTime);
        }

        @Override
        public String inWords() {
            String middayAndMidnight = this.inWordsForMiddayAndMidnightPronuntiation();

            if (!middayAndMidnight.isEmpty())
                return middayAndMidnight;

            return NumberInWordsFactory.createCardinalBuilderChooser()
                        .forPortugueseLanguage()
                        .withFemaleGender()
                        .build()
                        .inWords(this.hourToPronuntiate())
                            + this.getUnit();
        }

        private String getUnit() {
            if (checkUnitNeedToBeUsed(localTime))
                return this.hourToPronuntiate() < 2 ? " hora" : " horas";

            return "";
        }

        private String inWordsForMiddayAndMidnightPronuntiation() {
            if (useMiddayAndMidnightPronuntiation) {
                if ( canUseMinutesToHourPronunciation(localTime)) {
                    if (localTime.getHour()+1 == 24) return "meia-noite";
                    if (localTime.getHour()+1 == 12) return "meio-dia";
                } else {
                    if (localTime.getHour() == 0) return "meia-noite";
                    if (localTime.getHour() == 12) return "meio-dia";
                }
            }

            return "";
        }

        public long hourToPronuntiate() {
            long hour = hourFor12or24Format();

            if (canUseMinutesToHourPronunciation(localTime))
                hour++;

            long maxHour = use12HoursFormat ? 12 : 23;

            if (hour > maxHour)
                hour = hour == 13 ? 1 : 0;

            return hour;
        }

        private long hourFor12or24Format() {
            return use12HoursFormat && localTime.getHour() > 12  ? localTime.getHour()-12 : localTime.getHour();
        }
    }

    private class Minute extends PartOfTime {
        private Minute(LocalTime localTime) {
            super(localTime);
        }

        @Override
        public String inWords() {
            if (canUseMinutesToHourPronunciation(localTime))
                return inWordsForMinutesToHourPronuntiation();

            long minute = this.minuteToPronuntiate();

            if (minute == 0)
                return "";

            long hour = hourOf(localTime).hourFor12or24Format();

            if (minute == 30 && useHalfFor30Minutes && hour <= 12)
                return " e meia";

            return " e " + NumberInWordsFactory.createCardinalBuilderChooser()
                    .forPortugueseLanguage()
                    .withMaleGender()
                    .build()
                    .inWords(minute) + this.getUnit();
        }

        private String inWordsForMinutesToHourPronuntiation() {
            return NumberInWordsFactory.createCardinalBuilderChooser()
                        .forPortugueseLanguage()
                        .withMaleGender()
                        .build()
                        .inWords(minuteToPronuntiate()) + this.getUnit() + getPreposition();
        }

        public long minuteToPronuntiate() {
            if (canUseMinutesToHourPronunciation(localTime))
                return 60 - localTime.getMinute();

            return localTime.getMinute();
        }

        private String getUnit() {
            if (checkUnitNeedToBeUsed(localTime))
                return this.minuteToPronuntiate() < 2 ? " minuto" : " minutos";

            return "";
        }

        private String getPreposition() {
            Hour hour = hourOf(localTime);

            String preposition = "às ";

            if (hour.inWords().equals("meia-noite"))
                preposition = "";
            else if (hour.inWords().equals("meio-dia"))
                preposition = "o ";
            else if (hour.hourToPronuntiate() < 2)
                preposition = "";

            return " para " + preposition;
        }
    }

    public class Second extends PartOfTime {
        protected Second(LocalTime localTime) {
            super(localTime);
        }

        @Override
        public String inWords() {
            if (!useSeconds || localTime.getSecond() == 0)
                return "";

            return " e " +
                    NumberInWordsFactory.createCardinalBuilderChooser()
                            .forPortugueseLanguage()
                            .withMaleGender()
                            .build()
                            .inWords((long) localTime.getSecond()) + this.getUnit();
        }

        private String getUnit() {
            return localTime.getSecond() < 2 ? " segundo" : " segundos";
        }
    }

    public class Period extends PartOfTime {
        protected Period(LocalTime localTime) {
            super(localTime);
        }

        @Override
        public String inWords() {
            if (!usePeriodPronuntiation)
                return "";

            int hour = (localTime.getHour() + (canUseMinutesToHourPronunciation(localTime) ? 1 : 0)) % 24;

            if (useMiddayAndMidnightPronuntiation && (hour == 0 || hour == 12))
                return "";

            return switch (hour / 6) {
                case 0 -> " da madrugada";
                case 1 -> " da manhã";
                case 2 -> " da tarde";
                default -> " da noite";
            };
        }
    }

    public static class Builder extends TimeInWords.Builder<Builder> {
        private boolean useHalfFor30Minutes = false;
        protected boolean use12HoursFormat = false;
        protected boolean useInformalPronuntiation = false;
        protected boolean useMinutesToHourPronuntiation = false;

        public Builder withHalfFor30Minutes() {
            return this.withHalfFor30Minutes(true);
        }

        public Builder withHalfFor30Minutes(boolean useHalfFor30Minutes) {
            this.useHalfFor30Minutes = useHalfFor30Minutes;
            return getThis();
        }

        public Builder withInformalPronuntiation(boolean useInformalPronuntiation) {
            this.useInformalPronuntiation = useInformalPronuntiation;
            return getThis();
        }

        public Builder withInformalPronuntiation() {
            return this.withInformalPronuntiation(true);
        }

        public Builder with12HoursFormat(boolean use12HoursFormat) {
            this.use12HoursFormat = use12HoursFormat;
            return getThis();
        }

        public Builder with12HoursFormat() {
            return this.with12HoursFormat(true);
        }

        public Builder withMinuteToHourPronuntiation() {
            return this.withMinuteToHourPronuntiation(true);
        }

        public Builder withMinuteToHourPronuntiation(boolean useMinutesToHourPronuntiation) {
            this.useMinutesToHourPronuntiation = useMinutesToHourPronuntiation;
            return getThis();
        }

        @Override
        protected Builder getThis() {
            return this;
        }

        @Override
        public TimeInPortuguese build() {
            return new TimeInPortuguese(this);
        }
    }
}