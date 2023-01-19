package numberinwords.portuguese;

import numberinwords.NumberInWordsFactory;
import numberinwords.TimeInWords;
import java.time.LocalTime;

/*
01:30		uma hora e trinta minutos
                !useInformalPronuntiation
			uma e trinta
			    useInformalPronuntiation
			uma e meia
			    useInformalPronuntiation
			    useHalfFor30Minutes

01:45		uma hora e quarenta e cinco minutos
                !useInformalPronuntiation
			uma e quarenta e cinco
			    useInformalPronuntiation
			quinze para as duas
			    useInformalPronuntiation
			    useMinutesToHour
			quinze minutos para as duas horas
			    !useInformalPronuntiation
			    useMinutesToHour

01:00		uma hora


13:30		treze horas e trinta minutos
			treze e trinta
			uma hora e trinta minutos
			uma e trinta

00:00		zero hora
			meia-noite

00:30		zero horas e trinta minutos
			meia noite e trinta minutos
			meia noite e trinta

12:00		doze horas
			meio-dia


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

    public TimeInPortuguese(Builder builder) {
        this.useSeconds = builder.isUsingSeconds();
        this.use12HoursFormat = builder.isUsing12HoursFormat();
        this.use24HoursFormat = builder.isUsing24HoursFormat();
        this.useInformalPronuntiation = builder.isUsingInformalPronuntiation();
        this.useMiddayAndMidnightPronuntiation = builder.isUsingMiddayAndMidnightPronuntiation();
    }

    @Override
    public String inWords(LocalTime localTime) {
        if (this.useInformalPronuntiation)
            return inWordsForInformalPronuntiation(localTime);

        long hour = this.use12HoursFormat && localTime.getHour() > 12  ? localTime.getHour()-12 : localTime.getHour();
        long minute = localTime.getMinute();

        String hoursUnit = hour >= 2 ? "horas" : "hora";
        String minutesUnit = minute >= 2 ? "minutos" : "minuto";

        String hourInWords = this.getHourInWords(hour, hoursUnit);
        String minuteInWords = this.getMinuteInWords(minute, minutesUnit);

        if (!this.useSeconds)
            return hourInWords + minuteInWords;

        long second = localTime.getSecond();

        String secondssUnit = second >= 2 ? "segundos" : "segundo";
        String secondInWords = this.getSecondInWords(second, secondssUnit);

        return hourInWords + minuteInWords + secondInWords;
    }

    private String inWordsForInformalPronuntiation(LocalTime localTime) {
        long hour = this.use12HoursFormat && localTime.getHour() > 12  ? localTime.getHour()-12 : localTime.getHour();
        long minute = localTime.getMinute();

        String hoursUnit = hour >= 2 ? "horas" : "hora";
        String minutesUnit = minute >= 2 ? "minutos" : "minuto";

        boolean forceToUseUnit = (hour == 0 && !useMiddayAndMidnightPronuntiation) || minute == 0;

        String hourInWords = this.getHourInWords(hour, forceToUseUnit ? hoursUnit : "");
        String minuteInWords = this.getMinuteInWords(minute, forceToUseUnit ? minutesUnit : "");

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

    private String getMinuteInWords(long minute, String unit) {
        if (minute == 0)
            return "";

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
