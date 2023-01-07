package numberinwords.portuguese;

public class Block {
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

    Block addNext(Long value) {
        return new Block(value, this);
    }

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

    public static class Builder {
        Long number;

        Builder withNumber(Long number) {
            this.number = Math.abs(number);
            return this;
        }

        Block build() {
            Block block = new Block(this);

            while (number != 0)
                block = block.addNext(number /= 1000);

            return block;
        }
    }
}
