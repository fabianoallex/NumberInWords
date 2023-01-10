package numberinwords.portuguese;

import numberinwords.CardinalInWords;
import numberinwords.DecimalInWords;
import numberinwords.Gender;

public class CardinalInPortuguese implements CardinalInWords {
    private final boolean useCommaSeparator;
    private final Gender gender;
    private final String zeroDescription;
    private final String negativeSignalDescription;
    private final String positiveSignalDescription;

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

        PortugueseCardinalBlock block = new PortugueseCardinalBlock.Builder(number)
                .withCardinalInPortuguese(this)
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
        public String negativeSignalDescription = PortugueseCardinalDescriptions.DEFAULT_NEGATIVE_SIGNAL_DESCRIPTION;
        public String positiveSignalDescription = PortugueseCardinalDescriptions.DEFAULT_POSITIVE_SIGNAL_DESCRIPTION;
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

        public Builder withCommaSeparator(boolean useCommaSeparator) {
            this.useCommaSeparator = useCommaSeparator;
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
