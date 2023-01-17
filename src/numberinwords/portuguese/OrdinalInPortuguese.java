package numberinwords.portuguese;

import numberinwords.Gender;
import numberinwords.OrdinalInWords;

public class OrdinalInPortuguese implements OrdinalInWords {
    private final Gender gender;
    private final boolean useCommaSeparator;
    private final boolean useNumberRepresentation;

    private OrdinalInPortuguese(Builder builder) {
        this.gender = builder.gender;
        this.useCommaSeparator = builder.useCommaSeparator;
        this.useNumberRepresentation = builder.useNumberRepresentation;
    }

    public Gender getGender() {
        return gender;
    }

    public boolean isUsingCommaSeparator() {
        return useCommaSeparator;
    }

    private String getNumberRepresentation(Long number) {
        if (gender.equals(Gender.MALE))
            return number.toString() + "º";

        return number.toString() + "ª";
    }

    @Override
    public String inWords(Long number) {
        if (number <= 0)
            return "";

        if (this.useNumberRepresentation)
            return this.getNumberRepresentation(number);

        StringBuilder result = new StringBuilder();

        PortugueseOrdinalBlock numberBlock = new PortugueseOrdinalBlock.Builder(number)
                .withOrdinalInPortuguese(this)
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
        private boolean useNumberRepresentation = false;

        public Builder withNumberRepresentation(boolean useNumberRepresentation) {
            this.useNumberRepresentation = useNumberRepresentation;
            return this;
        }

        public Builder withNumberRepresentation() {
            return this.withNumberRepresentation(true);
        }

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