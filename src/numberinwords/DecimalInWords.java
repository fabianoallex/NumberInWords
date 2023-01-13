package numberinwords;

import java.math.BigDecimal;

public interface DecimalInWords extends NumberInWords<BigDecimal> {


    static int getNumberOfDecimalPlaces(BigDecimal bigDecimal) {
        String string = bigDecimal.stripTrailingZeros().toPlainString();
        int index = string.indexOf(".");
        return index < 0 ? 0 : string.length() - index - 1;
    }

    static long getIntegerPart(BigDecimal value) {
        int numberOfDecimalPlaces = getNumberOfDecimalPlaces(value);
        long placesMultiplier = (long) Math.pow(10, numberOfDecimalPlaces);
        long centsValue = value
                .multiply(BigDecimal.valueOf(placesMultiplier))
                .longValue();
        return centsValue / placesMultiplier;
    }

    static long getDecimalPart(BigDecimal value) {
        int numberOfDecimalPlaces = getNumberOfDecimalPlaces(value);
        long placesMultiplier = (long) Math.pow(10, numberOfDecimalPlaces);
        long centsValue = value
                .multiply(BigDecimal.valueOf(placesMultiplier))
                .longValue();
        return centsValue % placesMultiplier;
    }
}
