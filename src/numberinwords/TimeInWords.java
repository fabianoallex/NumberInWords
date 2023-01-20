package numberinwords;

import java.time.LocalTime;

public interface TimeInWords extends NumberInWords<LocalTime>{

    abstract class Builder<T extends Builder<T>> {
        protected boolean useSeconds = false;
        protected boolean use24HoursFormat = true;
        protected boolean use12HoursFormat = false;
        protected boolean useInformalPronuntiation = false;
        protected boolean useMiddayAndMidnightPronuntiation = false;
        protected boolean useMinutesToHourPronuntiation = false;

        protected abstract T getThis();
        public abstract TimeInWords build();


        public T withMinuteToHourPronuntiation() {
            return this.withMinuteToHourPronuntiation(true);
        }

        public T withMinuteToHourPronuntiation(boolean useMinutesToHourPronuntiation) {
            this.useMinutesToHourPronuntiation = useMinutesToHourPronuntiation;
            return getThis();
        }

        public T withMiddayAndMidnightPronuntiation(boolean useMiddayAndMidnightPronuntiation) {
            this.useMiddayAndMidnightPronuntiation = useMiddayAndMidnightPronuntiation;
            return getThis();
        }

        public T withMiddayAndMidnightPronuntiation() {
            return this.withMiddayAndMidnightPronuntiation(true);
        }

        public T withInformalPronuntiation(boolean useInformalPronuntiation) {
            this.useInformalPronuntiation = useInformalPronuntiation;
            return getThis();
        }

        public T withInformalPronuntiation() {
            return this.withInformalPronuntiation(true);
        }

        public T with24HoursFormat(boolean use24HoursFormat) {
            this.use24HoursFormat = use24HoursFormat;
            this.use12HoursFormat = false;
            return getThis();
        }

        public T with24HoursFormat() {
            return this.with24HoursFormat(true);
        }

        public T with12HoursFormat(boolean use12HoursFormat) {
            this.use12HoursFormat = use12HoursFormat;
            this.use24HoursFormat = false;
            return getThis();
        }

        public T with12HoursFormat() {
            return this.with12HoursFormat(true);
        }

        public T usingSeconds() {
            this.useSeconds = true;
            return getThis();
        }

        public boolean isUsing12HoursFormat() {
            return use12HoursFormat;
        }

        public boolean isUsing24HoursFormat() {
            return use24HoursFormat;
        }

        public boolean isUsingSeconds() {
            return useSeconds;
        }

        public boolean isUsingInformalPronuntiation() {
            return useInformalPronuntiation;
        }

        public boolean isUsingMiddayAndMidnightPronuntiation() {
            return this.useMiddayAndMidnightPronuntiation;
        }

        public boolean isUsingMinutesToHourPronuntiation() {
            return useMinutesToHourPronuntiation;
        }
    }
}
