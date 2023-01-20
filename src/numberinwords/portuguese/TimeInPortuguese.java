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
        if (this.acceptMinutesToHourPronunciation(localTime))
            return inWordsForMinutesToHourPronuntiation(localTime);

        return this.getHourInWords(localTime) +
                this.getMinuteInWords(localTime) +
                this.getSecondInWords(localTime) +
                this.getPeriodInWords(localTime);
    }

    private String inWordsForMinutesToHourPronuntiation(LocalTime localTime) {
        long hour = calculateHourToPronuntiate(localTime);
        long minute = 60 - localTime.getMinute();

        String hourInWords = this.getHourInWords(localTime);
        String minutesUnit = minute >= 2 ? "minutos" : "minuto";
        String minuteInWords = NumberInWordsFactory.createCardinalBuilderChooser()
                .forPortugueseLanguage()
                .withMaleGender()
                .build()
                .inWords(minute)
                    + (this.checkIfUseUnit(localTime) ? " " + minutesUnit : "");

        return minuteInWords +
                this.calculatePreposition(hourInWords, hour) +
                hourInWords +
                this.getPeriodInWords(localTime);
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

    private String getHourInWords(LocalTime localTime){
        String hourForMiddayAndMidnight = getHourInWordsForMiddayAndMidnightPronuntiation(localTime);

        if (!hourForMiddayAndMidnight.isEmpty())
            return hourForMiddayAndMidnight;

        String hoursUnit = calculateHourToPronuntiate(localTime) < 2 ?
                " hora" : " horas";

        return NumberInWordsFactory.createCardinalBuilderChooser()
                .forPortugueseLanguage()
                .withFemaleGender()
                .build()
                .inWords(calculateHourToPronuntiate(localTime))
                    + (checkIfUseUnit(localTime) ? hoursUnit : "");
    }

    private String getSecondInWords(LocalTime localTime) {
        if (!this.useSeconds)
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

    private String getMinuteInWords(LocalTime localTime) {
        long hour = calculateHourFor12or24Format(localTime);
        long minute = localTime.getMinute();

        if (minute == 0)
            return "";

        if (minute == 30 && this.useHalfTo30Minutes && hour <= 12)
            return " e meia";

        String minutesUnit = minute >= 2 ? " minutos" : " minuto";

        return " e " + NumberInWordsFactory.createCardinalBuilderChooser()
                .forPortugueseLanguage()
                .withMaleGender()
                .build()
                .inWords(minute)
                    + (checkIfUseUnit(localTime) ? minutesUnit : "");
    }

    private boolean checkIfUseUnit(LocalTime localTime) {
        return localTime.getMinute() == 0 ||
                !this.useInformalPronuntiation ||
                (!useMiddayAndMidnightPronuntiation &&
                        calculateHourFor12or24Format(localTime) == 0);
    }

    private long calculateHourFor12or24Format(LocalTime localTime) {
        return this.use12HoursFormat && localTime.getHour() > 12  ?
                localTime.getHour()-12 : localTime.getHour();
    }

    private String getPeriodInWords(LocalTime localTime) {
        if (!this.usePeriodPronuntiation)
            return "";

        int hour = (localTime.getHour() + (this.acceptMinutesToHourPronunciation(localTime) ? 1 : 0)) % 24;

        if (this.useMiddayAndMidnightPronuntiation && (hour == 0 || hour == 12))
            return "";

        return switch (hour / 6) {
            case 0 -> " da madrugada";
            case 1 -> " da manhã";
            case 2 -> " da tarde";
            default -> " da noite";
        };
    }

    private boolean acceptMinutesToHourPronunciation(LocalTime localTime) {
        return this.useMinutesToHourPronuntiation && localTime.getMinute() >= 40;
    }

    private long calculateHourToPronuntiate(LocalTime localTime) {
        long hour = (this.use12HoursFormat && localTime.getHour() > 12  ? localTime.getHour()-12 : localTime.getHour());

        if (this.acceptMinutesToHourPronunciation(localTime))
            hour++;

        long maxHour = this.use12HoursFormat ? 12 : 23;

        if (hour > maxHour)
            hour = hour == 13 ? 1 : 0; //se passou das 12 ou 23 horas, vai pra 1 ou pra 0

        return hour;
    }

    private String getHourInWordsForMiddayAndMidnightPronuntiation(LocalTime localTime) {
        if (this.useMiddayAndMidnightPronuntiation) {
            if (this.acceptMinutesToHourPronunciation(localTime)) {
                if (localTime.getHour()+1 == 24) return "meia-noite";
                if (localTime.getHour()+1 == 12) return "meio-dia";
            } else {
                if (localTime.getHour() == 0) return "meia-noite";
                if (localTime.getHour() == 12) return "meio-dia";
            }
        }

        return "";
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