package numberinwords.portuguese;

public abstract class Block {
    final Long value;
    final Suffix suffix;
    final Block next;

    Block(Builder builder) {
        this.next = null;
        this.value = builder.number % 1000;
        this.suffix = Suffix.NO_SUFFIX; //first block don't have suffix
    }

    Block(Long value, Block next) {
        this.next = next;
        this.value = value % 1000;
        this.suffix = next.suffix.getNext(); //get next suffix
    }

    public String inWords() {
        return this.getNumberDescription() +
                this.getSuffixDescription() +
                this.getConjuction();
    }
    public abstract Block addNext(Long value);

    protected abstract String getSuffixDescription();

    protected abstract String getConjuction();

    protected abstract String getNumberDescription();

    Long getValue() {
        return value;
    }

    Block getNextBlock() {
        return this.next;
    }

    Block getNextPronounceableBlock() {
        Block next = this.getNextBlock();

        while (next != null && next.getValue() == 0)
            next = next.getNextBlock();

        return next;
    }

    boolean isLastPronounceableBlock() {
        return getNextPronounceableBlock() == null;
    }

    public abstract static class Builder {
        Long number;

        abstract Builder withNumber(Long number);
        abstract Block build();
    }
}
