package numberinwords.english;

import numberinwords.Block;
import numberinwords.CardinalInWords;

public class CardinalInEnglish implements CardinalInWords {
    private final boolean useCommaSeparator;
    private final String zeroDescription;
    private final String negativeSignalDescription;
    private final String positiveSignalDescription;
    private final boolean useAndInHundred;

    protected CardinalInEnglish(Builder builder) {
        this.useCommaSeparator = builder.useCommaSeparator;
        this.zeroDescription = builder.zeroDescription;
        this.negativeSignalDescription = builder.negativeSignalDescription;
        this.positiveSignalDescription = builder.positiveSignalDescription;
        this.useAndInHundred = builder.useAndInHundred;
    }

    public boolean isUsingCommaSeparator() {
        return useCommaSeparator;
    }

    public String getZeroDescription() {
        return zeroDescription;
    }

    public String getNegativeSignalDescription() {
        return negativeSignalDescription;
    }

    public String getPositiveSignalDescription() {
        return positiveSignalDescription;
    }

    @Override
    public String inWords(Long number) {
        StringBuilder result = new StringBuilder();
        result.append(getSignalDescription(number));

        EnglishCardinalBlock block = new EnglishCardinalBlock.Builder(number)
                .withCardinalInEnglish(this)
                .withAndInHundred(this.useAndInHundred)
                .build();

        while (block != null) {
            result.append(block.inWords());

            if (block.isLastPronounceable())
                break;

            block = block.getNextPronounceable();
        }

        return result.toString().trim();
    }

    private String getSignalDescription(Long number) {
        String signalDescription = ""; //if zero

        if (number < 0)
            signalDescription = negativeSignalDescription;

        if (number > 0)
            signalDescription = positiveSignalDescription;

        return signalDescription.isEmpty() ? "" : signalDescription + " ";
    }

    public static class Builder {
        public String negativeSignalDescription = EnglishCardinalDescriptions.DEFAULT_NEGATIVE_SIGNAL_DESCRIPTION;
        public String positiveSignalDescription = EnglishCardinalDescriptions.DEFAULT_POSITIVE_SIGNAL_DESCRIPTION;
        private boolean useCommaSeparator = false;
        private boolean useAndInHundred = false;
        private String zeroDescription;

        public Builder withNegativeSignalDescription(String description) {
            this.negativeSignalDescription = description;
            return this;
        }

        public Builder withPositiveSignalDescription(String description) {
            this.positiveSignalDescription = description;
            return this;
        }

        public Builder withCommaSeparator() {
            this.useCommaSeparator = true;
            return this;
        }

        public Builder withZeroDescription(String zeroDescription) {
            this.zeroDescription = zeroDescription;
            return this;
        }

        public Builder withAndInHundred(boolean useAndInHundred) {
            this.useAndInHundred = useAndInHundred;
            return this;
        }

        public Builder withAndInHundred() {
            return this.withAndInHundred(true);
        }

        public CardinalInEnglish build() {
            return new CardinalInEnglish(this);
        }
    }
}
