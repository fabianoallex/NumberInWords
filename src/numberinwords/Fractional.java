package numberinwords;

public class Fractional {
    private final long wholePart;
    private final long numerator;
    private final long denominator;

    public static Fractional of(long wholePart, long numerator, long denominator) {
        return new Fractional(wholePart, numerator, denominator);
    }

    public static Fractional of(long numerator, long denominator) {
        return new Fractional(0, numerator, denominator);
    }

    public static Fractional of(long denominator) {
        return Fractional.of(0, 1, denominator);
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
        return  this.wholePart + (double) numerator / denominator;
    }

    public Fractional toImproper() {
        if (this.isMixed())
            return Fractional.of(this.wholePart * this.denominator + this.numerator, this.denominator);

        return this;
    }

    public Fractional toMixed() {
        if (isProper())
            return this;

        long whole = this.numerator / this.denominator + this.wholePart;
        long numerator = this.numerator % this.denominator;

        return Fractional.of(whole, numerator, this.denominator);
    }

    public boolean isMixed() {
        return this.wholePart != 0;
    }

    public boolean isProper() {
        return this.denominator > this.numerator;
    }

    public boolean isUnproper() {
        return !this.isProper();
    }


    @Override
    public String toString() {
        return "Fractional(%d, %d, %d)".formatted(wholePart, numerator, denominator);
    }
}
