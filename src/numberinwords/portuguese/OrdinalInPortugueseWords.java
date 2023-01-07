package numberinwords.portuguese;

import numberinwords.NumberInWords;

public class OrdinalInPortugueseWords implements NumberInWords {
    private final Gender gender;
    private final boolean useCommaSeparator;

    protected OrdinalInPortugueseWords(Builder builder) {
        this.gender = builder.gender;
        this.useCommaSeparator = builder.useCommaSeparator;
    }

    @Override
    public String inWords(Long number) {
        StringBuilder result = new StringBuilder();

        OrdinalBlock numberBlock = new OrdinalBlock.Builder()
                .withNumber(number)
                .build();

        while (numberBlock != null) {
            result.append(numberBlock.inWords(this.useCommaSeparator, this.gender));

            if (numberBlock.isLastPronounceableBlock())
                break;

            numberBlock = numberBlock.getNextPronounceableBlock();
        }

        return result.toString();
    }

    public static class Builder {
        private Gender gender = Gender.MALE;
        private boolean useCommaSeparator = false;

        public Builder withMaleGender() {
            this.gender = Gender.MALE;
            return this;
        }

        public Builder withCommaSeparator() {
            this.useCommaSeparator = true;
            return this;
        }

        public Builder withFemaleGender() {
            this.gender = Gender.FEMALE;
            return this;
        }

        public OrdinalInPortugueseWords build() {
            return new OrdinalInPortugueseWords(this);
        }
    }
}