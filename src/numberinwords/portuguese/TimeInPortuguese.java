package numberinwords.portuguese;

import numberinwords.NumberInWordsFactory;
import numberinwords.TimeInWords;
import java.time.LocalTime;

/*
01:30		uma hora e trinta minutos
			uma e trinta
			uma e meia

01:45		uma hora e quarenta e cinco minutos
			uma e quarenta e cinco
			quinze para as duas
			quinze minutos para as duas horas

01:00		uma hora


13:30		treze horas e trinta minutos
			treze e trinta
			uma hora e trinta minutos
			uma e trinta

00:00		zero hora
			meia-noite

00:30		zero horas e trinta minutos
			meia-noite e trinta minutos
			meia-noite e trinta
			meia-noite e meia

12:00		doze horas
			meio-dia

12:30		doze horas e trinta minutos
            doze e trinta
            meio-dia e trinta minutos
            meio-dia e trina


11:45		onze horas e quarenta e cinco minutos
			onze e quarenta e cinco
			quinze para as doze
			quinze para o meio-dia
*
*
* */


public class TimeInPortuguese implements TimeInWords {
    private final boolean useSeconds;
    private final boolean use24HoursFormat;
    private final boolean use12HoursFormat;
    private final boolean useInformalPronuntiation;
    private final boolean useMiddayAndMidnightPronuntiation;
    private final boolean useHalfTo30Minutes;
    private final boolean useMinutesToHourPronuntiation;

    public TimeInPortuguese(Builder builder) {
        this.useSeconds = builder.isUsingSeconds();
        this.use12HoursFormat = builder.isUsing12HoursFormat();
        this.use24HoursFormat = builder.isUsing24HoursFormat();
        this.useInformalPronuntiation = builder.isUsingInformalPronuntiation();
        this.useMiddayAndMidnightPronuntiation = builder.isUsingMiddayAndMidnightPronuntiation();
        this.useHalfTo30Minutes = builder.useHalfTo30Minutes;
        this.useMinutesToHourPronuntiation = builder.isUsingMinutesToHourPronuntiation();
    }

    @Override
    public String inWords(LocalTime localTime) {
        long hour = this.use12HoursFormat && localTime.getHour() > 12  ? localTime.getHour()-12 : localTime.getHour();
        long minute = localTime.getMinute();

        if (this.useMinutesToHourPronuntiation && minute >= 40)
            return inWordsForMinutesToHourPronuntiation(localTime);

        if (this.useInformalPronuntiation)
            return inWordsForInformalPronuntiation(localTime);

        String hoursUnit = hour >= 2 ? "horas" : "hora";
        String minutesUnit = minute >= 2 ? "minutos" : "minuto";

        String hourInWords = this.getHourInWords(hour, hoursUnit);
        String minuteInWords = this.getMinuteInWords(minute, hour, minutesUnit);

        if (!this.useSeconds)
            return hourInWords + minuteInWords;

        long second = localTime.getSecond();

        String secondssUnit = second >= 2 ? "segundos" : "segundo";
        String secondInWords = this.getSecondInWords(second, secondssUnit);

        return hourInWords + minuteInWords + secondInWords;
    }

    private String inWordsForMinutesToHourPronuntiation(LocalTime localTime) {
        long maxHour = this.use12HoursFormat ? 12 : 23;

        long hour = (this.use12HoursFormat && localTime.getHour() > 12  ? localTime.getHour()-12 : localTime.getHour()) + 1;

        if (hour > maxHour)
            hour = hour == 13 ? 1 : 0; //se passou das 12 ou 23 horas, vai pra 1 ou pra 0

        long minute = 60 - localTime.getMinute();

        String hoursUnit = hour >= 2 ? "horas" : "hora";
        String minutesUnit = minute >= 2 ? "minutos" : "minuto";

        boolean forceToUseUnit = !this.useInformalPronuntiation
                || (hour == 0 && !useMiddayAndMidnightPronuntiation);

        String hourInWords = this.getHourInWords(hour, forceToUseUnit ? hoursUnit : "");
        String minuteInWords = NumberInWordsFactory.createCardinalBuilderChooser()
                .forPortugueseLanguage()
                .withMaleGender()
                .build()
                .inWords(minute)
                    + (forceToUseUnit ? " " + minutesUnit : "");

        String preposition = "às ";

        if (hourInWords.equals("meia-noite"))
            preposition = "";
        else if (hourInWords.equals("meio-dia"))
            preposition = "o ";
        else if (hour < 2)
            preposition = "";

        return minuteInWords + " para " + preposition + hourInWords;
    }

    private String inWordsForInformalPronuntiation(LocalTime localTime) {
        long hour = this.use12HoursFormat && localTime.getHour() > 12  ? localTime.getHour()-12 : localTime.getHour();
        long minute = localTime.getMinute();

        String hoursUnit = hour >= 2 ? "horas" : "hora";
        String minutesUnit = minute >= 2 ? "minutos" : "minuto";

        boolean forceToUseUnit = (hour == 0 && !useMiddayAndMidnightPronuntiation) || minute == 0;

        String hourInWords = this.getHourInWords(hour, forceToUseUnit ? hoursUnit : "");
        String minuteInWords = this.getMinuteInWords(minute, hour, forceToUseUnit ? minutesUnit : "");

        return hourInWords + minuteInWords;
    }

    private String getSecondInWords(long second, String unit) {
        if (second <= 0)
            return "";

        return " e " + NumberInWordsFactory.createCardinalBuilderChooser()
                .forPortugueseLanguage()
                .withMaleGender()
                .build()
                .inWords(second)
                    + (unit.isEmpty() ? "" : " " + unit);
    }

    private String getMinuteInWords(long minute, long hour, String unit) {
        if (minute == 0)
            return "";

        if (minute == 30 && this.useHalfTo30Minutes && hour <= 12)
            return " e meia";

        return " e " + NumberInWordsFactory.createCardinalBuilderChooser()
                .forPortugueseLanguage()
                .withMaleGender()
                .build()
                .inWords(minute)
                    + (unit.isEmpty() ? "" : " " + unit);
    }

    private String getHourInWords(long hour, String unit) {
        if (this.useMiddayAndMidnightPronuntiation && hour == 0)
            return "meia-noite";

        if (this.useMiddayAndMidnightPronuntiation && hour == 12)
            return "meio-dia";

        return NumberInWordsFactory.createCardinalBuilderChooser()
                .forPortugueseLanguage()
                .withFemaleGender()
                .build()
                .inWords(hour)
                    + (unit.isEmpty() ? "" : " " + unit);
    }

    public static class Builder extends TimeInWords.Builder<Builder> {
        private boolean useHalfTo30Minutes = false;

        public Builder witHalfTo30Minutes() {
            return this.witHalfTo30Minutes(true);
        }

        public Builder witHalfTo30Minutes(boolean useHalfTo30Minutes) {
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
