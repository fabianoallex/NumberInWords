package numberinwords.portuguese;

import numberinwords.NumberInWordsFactory;
import numberinwords.TimeInWords;
import java.time.LocalTime;

public class TimeInPortuguese implements TimeInWords {
    private final boolean useSeconds;
    private final boolean use12HoursFormat;
    private final boolean useInformalPronuntiation;
    private final boolean useMiddayAndMidnightPronuntiation;
    private final boolean useHalfTo30Minutes;
    private final boolean useMinutesToHourPronuntiation;
    private final boolean usePeriodPronuntiation;

    public TimeInPortuguese(Builder builder) {
        this.useSeconds = builder.isUsingSeconds();
        this.use12HoursFormat = builder.isUsing12HoursFormat();
        this.useInformalPronuntiation = builder.isUsingInformalPronuntiation();
        this.useMiddayAndMidnightPronuntiation = builder.isUsingMiddayAndMidnightPronuntiation();
        this.useHalfTo30Minutes = builder.useHalfTo30Minutes;
        this.useMinutesToHourPronuntiation = builder.isUsingMinutesToHourPronuntiation();
        this.usePeriodPronuntiation = builder.isUsingPeriodPronuntiation();
    }

    @Override
    public String inWords(LocalTime localTime) {
        Hour hour = new Hour(localTime);
        Minute minute = new Minute(localTime);
        Second second = new Second(localTime);
        Period period = new Period(localTime);

        if (this.canUseMinutesToHourPronunciation(localTime))
            return minute.inWords() +
                    hour.inWords() +
                    period.inWords();

        return hour.inWords() +
                minute.inWords() +
                second.inWords() +
                period.inWords();
    }

    private boolean checkIfUseUnit(LocalTime localTime) {
        return localTime.getMinute() == 0 ||
                !this.useInformalPronuntiation ||
                (!useMiddayAndMidnightPronuntiation && new Hour(localTime).hourFor12or24Format() == 0);
    }

    private boolean canUseMinutesToHourPronunciation(LocalTime localTime) {
        return this.useMinutesToHourPronuntiation && localTime.getMinute() >= 40;
    }

    private abstract static class PartOfTime {
        protected final LocalTime localTime;

        protected PartOfTime(LocalTime localTime) {
            this.localTime = localTime;
        }

        public abstract String inWords();
    }

    private class Hour extends PartOfTime {
        protected Hour(LocalTime localTime) {
            super(localTime);
        }

        @Override
        public String inWords() {
            String hourForMiddayAndMidnight = this.inWordsForMiddayAndMidnightPronuntiation();

            if (!hourForMiddayAndMidnight.isEmpty())
                return hourForMiddayAndMidnight;

            String hoursUnit = this.hourToPronuntiate() < 2 ? " hora" : " horas";

            return NumberInWordsFactory.createCardinalBuilderChooser()
                        .forPortugueseLanguage()
                        .withFemaleGender()
                        .build()
                        .inWords(this.hourToPronuntiate())
                            + (checkIfUseUnit(localTime) ? hoursUnit : "");
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

            long hour = new Hour(localTime).hourFor12or24Format();
            long minute = localTime.getMinute();

            if (minute == 0)
                return "";

            if (minute == 30 && useHalfTo30Minutes && hour <= 12)
                return " e meia";

            String minutesUnit = minute >= 2 ? " minutos" : " minuto";

            return " e " + NumberInWordsFactory.createCardinalBuilderChooser()
                    .forPortugueseLanguage()
                    .withMaleGender()
                    .build()
                    .inWords(minute)
                    + (checkIfUseUnit(localTime) ? minutesUnit : "");
        }

        private String inWordsForMinutesToHourPronuntiation() {
            Hour hour = new Hour(localTime);

            long minute = 60 - localTime.getMinute();

            String minutesUnit = minute >= 2 ? "minutos" : "minuto";
            String minuteInWords = NumberInWordsFactory.createCardinalBuilderChooser()
                    .forPortugueseLanguage()
                    .withMaleGender()
                    .build()
                    .inWords(minute)
                    + (checkIfUseUnit(localTime) ? " " + minutesUnit : "");

            return minuteInWords +
                    calculatePreposition(hour.inWords(), hour.hourToPronuntiate());
        }

        private String calculatePreposition(String hourInWords, long hour) {
            String preposition = "às ";

            if (hourInWords.equals("meia-noite"))
                preposition = "";
            else if (hourInWords.equals("meio-dia"))
                preposition = "o ";
            else if (hour < 2)
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
            if (!useSeconds)
                return "";

            if (localTime.getSecond() == 0)
                return "";

            return " e " + NumberInWordsFactory.createCardinalBuilderChooser()
                    .forPortugueseLanguage()
                    .withMaleGender()
                    .build()
                    .inWords((long) localTime.getSecond())
                    + (localTime.getSecond() < 2 ? " segundo" : " segundos");
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
        private boolean useHalfTo30Minutes = false;

        public Builder withHalfTo30Minutes() {
            return this.withHalfTo30Minutes(true);
        }

        public Builder withHalfTo30Minutes(boolean useHalfTo30Minutes) {
            this.useHalfTo30Minutes = useHalfTo30Minutes;
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