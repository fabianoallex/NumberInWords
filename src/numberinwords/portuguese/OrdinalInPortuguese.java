package numberinwords.portuguese;

import numberinwords.Gender;
import numberinwords.OrdinalInWords;

public class OrdinalInPortuguese implements OrdinalInWords {
    private final Gender gender;
    private final boolean useCommaSeparator;

    protected OrdinalInPortuguese(Builder builder) {
        this.gender = builder.gender;
        this.useCommaSeparator = builder.useCommaSeparator;
    }

    @Override
    public String inWords(Long number) {
        StringBuilder result = new StringBuilder();

        if (number <= 0)
            return "";

        OrdinalBlock numberBlock = new OrdinalBlock.Builder(number)
                .withGender(this.gender)
                .withCommaSeparator(this.useCommaSeparator)
                .build();

        while (numberBlock != null) {
            result.append(numberBlock.inWords());

            if (numberBlock.isLastPronounceable())
                break;

            numberBlock = numberBlock.getNextPronounceable();
        }

        return result.toString().trim();
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

        public OrdinalInPortuguese build() {
            return new OrdinalInPortuguese(this);
        }
    }
}