package numberinwords.english;

import numberinwords.OrdinalInWords;

public class OrdinalInEnglish implements OrdinalInWords {
    private final boolean useCommaSeparator;

    private OrdinalInEnglish(Builder builder) {
        this.useCommaSeparator = builder.useCommaSeparator;
    }

    @Override
    public String inWords(Long number) {
        StringBuilder result = new StringBuilder();

        if (number <= 0)
            return "";

        EnglishOrdinalBlock numberBlock = new EnglishOrdinalBlock.Builder(number)
                .withOrdinalInEnglish(this)
                .build();

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

        public Builder withCommaSeparator() {
            this.useCommaSeparator = true;
            return this;
        }

        public OrdinalInEnglish build() {
            return new OrdinalInEnglish(this);
        }
    }
}
