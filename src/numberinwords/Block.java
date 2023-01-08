package numberinwords;

public abstract class Block {
    private final Long value;

    private final Suffix suffix;

    private final Block next;
    public Block(Builder builder) {
        this.next = null;
        this.value = builder.number % 1000;
        this.suffix = Suffix.NO_SUFFIX; //first block don't have suffix
    }

    public Block(Long value, Block next) {
        this.next = next;
        this.value = value % 1000;
        this.suffix = next.suffix.getNext(); //get next suffix
    }

    public Suffix getSuffix() {
        return suffix;
    }

    public String inWords() {
        return this.getNumberDescription() +
                this.getSuffixDescription() +
                this.getConjuction();
    }
    public abstract Block addNext(Long value);

    public abstract String getSuffixDescription();

    public abstract String getConjuction();

    public abstract String getNumberDescription();

    public Long getValue() {
        return value;
    }

    public Block getNext() {
        return this.next;
    }

    public Block getNextPronounceable() {
        Block next = this.getNext();

        while (next != null && next.getValue() == 0)
            next = next.getNext();

        return next;
    }

    public boolean isLastPronounceable() {
        return getNextPronounceable() == null;
    }

    public abstract static class Builder {
        Long number;

        abstract Builder withNumber(Long number);
        abstract Block build();
    }
}
