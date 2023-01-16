package numberinwords;

import numberinwords.portuguese.DateInPortuguese;

import java.time.LocalDate;

public interface DateInWords extends NumberInWords<LocalDate> {
    abstract class Builder<T extends Builder<T>> {
        public boolean isUsingDay() {
            return useDay;
        }

        public boolean isUsingMonth() {
            return useMonth;
        }

        public boolean isUsingYear() {
            return useYear;
        }

        protected boolean useDay = true;
        protected boolean useMonth = true;
        protected boolean useYear = true;

        protected abstract T getThis();
        public abstract DateInWords build();

        public T usingDayAndMonth() {
            this.useDay = true;
            this.useMonth = true;
            this.useYear = false;
            return getThis();
        }

        public T usingMonthAndYear() {
            this.useDay = false;
            this.useMonth = true;
            this.useYear = true;
            return getThis();
        }
    }
}
