package numberinwords.portuguese;

import numberinwords.NumberInWords;

public class CardinalInPortuguese implements NumberInWords {
    private final boolean useCommaSeparator;
    private final Gender gender;
    private final String zeroDescription;
    private final String negativeSignalDescription;
    private final String positiveSignalDescription;

    protected CardinalInPortuguese(Builder builder) {
        this.useCommaSeparator = builder.useCommaSeparator;
        this.gender = builder.gender;
        this.zeroDescription = builder.zeroDescription;
        this.negativeSignalDescription = builder.negativeSignalDescription;
        this.positiveSignalDescription = builder.positiveSignalDescription;
    }

    @Override
    public String inWords(Long number) {
        StringBuilder result = new StringBuilder();
        result.append(getSignalDescription(number));

        CardinalBlock block = new CardinalBlock.Builder()
                .withCommaSeparator(useCommaSeparator)
                .withZeroDescription(this.zeroDescription)
                .withGender(this.gender)
                .withNumber(number)
                .build();

        while (block != null) {
            result.append(block.inWords());

            if (block.isLastPronounceableBlock())
                break;

            block = block.getNextPronounceableBlock();
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
        public String negativeSignalDescription = CardinalDescriptions.DEFAULT_NEGATIVE_SIGNAL_DESCRIPTION;
        public String positiveSignalDescription = CardinalDescriptions.DEFAULT_POSITIVE_SIGNAL_DESCRIPTION;
        private boolean useCommaSeparator = false;
        private Gender gender = Gender.MALE;
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

        public Builder withMaleGender() {
            this.gender = Gender.MALE;
            return this;
        }

        public Builder withFemaleGender() {
            this.gender = Gender.FEMALE;
            return this;
        }

        public Builder withZeroDescription(String zeroDescription) {
            this.zeroDescription = zeroDescription;
            return this;
        }

        public CardinalInPortuguese build() {
            return new CardinalInPortuguese(this);
        }
    }
}