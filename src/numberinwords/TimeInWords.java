package numberinwords;

import java.time.LocalTime;

public interface TimeInWords extends NumberInWords<LocalTime>{
    abstract class PartOfTime {
        protected final LocalTime localTime;

        public abstract String inWords();

        protected PartOfTime(LocalTime localTime) {
            this.localTime = localTime;
        }
    }

    abstract class Builder<T extends Builder<T>> {
        protected boolean useSeconds = false;
        protected boolean usePeriodPronuntiation = false;
        protected boolean useMiddayAndMidnightPronuntiation = false;

        protected abstract T getThis();
        public abstract TimeInWords build();

        public T withPeriodPronuntiation() {
            return this.withPeriodPronuntiation(true);
        }

        public T withPeriodPronuntiation(boolean usePeriodPronuntiation) {
            this.usePeriodPronuntiation = usePeriodPronuntiation;
            return getThis();
        }

        public T withMiddayAndMidnightPronuntiation(boolean useMiddayAndMidnightPronuntiation) {
            this.useMiddayAndMidnightPronuntiation = useMiddayAndMidnightPronuntiation;
            return getThis();
        }

        public T withMiddayAndMidnightPronuntiation() {
            return this.withMiddayAndMidnightPronuntiation(true);
        }

        public T usingSeconds() {
            this.useSeconds = true;
            return getThis();
        }

        public boolean isUsingSeconds() {
            return useSeconds;
        }

        public boolean isUsingMiddayAndMidnightPronuntiation() {
            return this.useMiddayAndMidnightPronuntiation;
        }

        public boolean isUsingPeriodPronuntiation() {
            return usePeriodPronuntiation;
        }
    }
}
