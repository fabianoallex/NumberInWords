package numberinwords.spanish;

import numberinwords.CardinalInWords;
import numberinwords.Gender;

public class CardinalInSpanish implements CardinalInWords {
    private final boolean useApocope;
    private final boolean useCommaSeparator;
    private final Gender gender;
    private final String zeroDescription;
    private final String negativeSignalDescription;
    private final String positiveSignalDescription;

    public boolean isUsingApocope() {
        return useApocope;
    }

    public boolean isUsingCommaSeparator() {
        return useCommaSeparator;
    }

    public Gender getGender() {
        return gender;
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

    private CardinalInSpanish(Builder builder) {
        this.useApocope = builder.useApocope;
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

        SpanishCardinalBlock block = new SpanishCardinalBlock.Builder(number)
                .withCardinalInSpanish(this)
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
        public String negativeSignalDescription = SpanishCardinalDescriptions.DEFAULT_NEGATIVE_SIGNAL_DESCRIPTION;
        public String positiveSignalDescription = SpanishCardinalDescriptions.DEFAULT_POSITIVE_SIGNAL_DESCRIPTION;
        private boolean useApocope = false;
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

        public Builder withGender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public Builder withZeroDescription(String zeroDescription) {
            this.zeroDescription = zeroDescription;
            return this;
        }

        public Builder withCommaSeparator(boolean useCommaSeparator) {
            this.useCommaSeparator = useCommaSeparator;
            return this;
        }

        public Builder withApocope() {
            this.useApocope = true;
            return this;
        }

        public CardinalInSpanish build() {
            return new CardinalInSpanish(this);
        }
    }
}
