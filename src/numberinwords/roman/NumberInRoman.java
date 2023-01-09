package numberinwords.roman;

import static java.lang.String.join;
import static java.util.Collections.nCopies;

public class NumberInRoman {
    private static final int[] SUB_DECIMALS = {
            1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static final String[] SUB_ROMANS = {
            "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    private static final int[] ADD_DECIMALS = {1000, 500, 100, 50, 10, 5, 1};
    private static final String[] ADD_ROMANS = {"M", "D", "C", "L", "X", "V", "I"};

    private final boolean useAdditiveMethod;

    private NumberInRoman(Builder builder) {
        this.useAdditiveMethod = builder.useAdditiveMethod;
    }

    public String inRoman(Integer number) {
        if (number <= 0)
            return "";

        int[] decimals = useAdditiveMethod ? ADD_DECIMALS : SUB_DECIMALS;
        String[] romans = useAdditiveMethod ? ADD_ROMANS : SUB_ROMANS;

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < decimals.length; i++) {
            int integerPart = number / decimals[i];
            number -= decimals[i] * integerPart;
            result.append(join("", nCopies(integerPart, romans[i])));
        }
        return result.toString();
    }

    public static class Builder {
        private boolean useAdditiveMethod = false;

        public Builder withAdditiveMethod() {
            this.useAdditiveMethod = true;
            return this;
        }

        public NumberInRoman build() {
            return new NumberInRoman(this);
        }
    }
}
