package numberinwords.portuguese;

public enum Suffix {
    NO_SUFFIX(0), THOUSAND(1), MILLION(2),
    BILLION(3), TRILLION(4), QUADRILLION(5),
    QUINTILLION(6), SEXTILLION(7), SEPTILLION(8),
    OCTILLION(9), NONILLION(10),
    DECILLION(11);

    final int suffixIndex;

    Suffix(int suffixIndex) {
        this.suffixIndex = suffixIndex;
    }

    Suffix getNext() {
        Suffix[] values = Suffix.values();
        int nextIndex = (this.ordinal() + 1) % values.length;
        return values[nextIndex];
    }
}
