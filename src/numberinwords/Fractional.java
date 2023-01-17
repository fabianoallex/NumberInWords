package numberinwords;

public class Fractional {
    private final long numerator;
    private final long denominator;

    public static Fractional of(long numerator, long denominator) {
        return new Fractional(numerator, denominator);
    }

    public static Fractional of(long denominator) {
        return Fractional.of(1, denominator);
    }

    private Fractional(long numerator, long denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public long getNumerator() {
        return numerator;
    }

    public long getDenominator() {
        return denominator;
    }

    @Override
    public String toString() {
        return "Fractional(%d, %d)".formatted(numerator, denominator);
    }
}
