package numberinwords;

public class Fractional {
    private final long wholePart;
    private final long numerator;
    private final long denominator;

    public static Fractional of(long wholePart, long numerator, long denominator) {
        return new Fractional(wholePart, numerator, denominator);
    }

    public static Fractional of(long numerator, long denominator) {
        return new Fractional(1, numerator, denominator);
    }

    public static Fractional of(long denominator) {
        return Fractional.of(1, 1, denominator);
    }

    private Fractional(long wholePart, long numerator, long denominator) {
        this.wholePart = wholePart;
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public long getNumerator() {
        return numerator;
    }

    public long getDenominator() {
        return denominator;
    }

    public long getWholePart() {
        return wholePart;
    }

    public double toDouble() {
        return (double) this.wholePart * numerator / denominator;
    }


    @Override
    public String toString() {
        return "Fractional(%d, %d, %d)".formatted(wholePart, numerator, denominator);
    }
}
