package numberinwords.portuguese;

import numberinwords.NumberInWords;

public class OrdinalInPortugueseWords implements NumberInWords {
    private final Gender gender;

    protected OrdinalInPortugueseWords(Builder builder) {
        this.gender = builder.gender;
    }

    @Override
    public String inWords(Long number) {
        StringBuilder result = new StringBuilder();

        OrdinalBlock numberBlock = new OrdinalBlock.Builder()
                .withNumber(number)
                .build();

        while (numberBlock != null) {
            result.append(numberBlock.inWords(this.gender));

            if (numberBlock.isLastPronounceableBlock())
                break;

            numberBlock = numberBlock.getNextPronounceableBlock();
        }

        return result.toString();
    }

    public static class Builder {
        private Gender gender = Gender.MALE;

        public Builder withMaleGender() {
            this.gender = Gender.MALE;
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