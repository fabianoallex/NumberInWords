package numberinwords.english;

import numberinwords.OrdinalInWords;

public class OrdinalInEnglish implements OrdinalInWords {
    private final boolean useCommaSeparator;
    private final boolean useNumberRepresentation;

    private OrdinalInEnglish(Builder builder) {
        this.useCommaSeparator = builder.useCommaSeparator;
        this.useNumberRepresentation = builder.useNumberRepresentation;
    }

    private String getNumberRepresentation(Long number) {
        String[] suffixes = new String[] { "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th" };

        return switch ((int) (number % 100)) {
            case 11, 12, 13 -> number + "th";
            default -> number + suffixes[(int) (number % 10)];
        };
    }

    @Override
    public String inWords(Long number) {
        if (number <= 0)
            return "";

        if (this.useNumberRepresentation)
            return this.getNumberRepresentation(number);

        EnglishOrdinalBlock numberBlock = new EnglishOrdinalBlock.Builder(number)
                .withOrdinalInEnglish(this)
                .build();

        StringBuilder result = new StringBuilder();

        while (numberBlock != null) {
            result.append(numberBlock.inWords());

            if (numberBlock.isLastPronounceable())
                break;

            numberBlock = numberBlock.getNextPronounceable();
        }

        return result.toString().trim();
    }

    public boolean isUsingCommaSeparator() {
        return useCommaSeparator;
    }

    public static class Builder {
        private boolean useCommaSeparator = false;
        private boolean useNumberRepresentation = false;

        public Builder withNumberRepresentation(boolean useNumberRepresentation) {
            this.useNumberRepresentation = useNumberRepresentation;
            return this;
        }

        public Builder withNumberRepresentation() {
            return this.withNumberRepresentation(true);
        }

        public Builder withCommaSeparator() {
            this.useCommaSeparator = true;
            return this;
        }

        public OrdinalInEnglish build() {
            return new OrdinalInEnglish(this);
        }
    }
}
