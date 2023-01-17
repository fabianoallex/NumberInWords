package numberinwords.english;

import numberinwords.Block;
import numberinwords.Suffix;

import java.util.Map;

public class EnglishCardinalBlock extends Block {
    private final CardinalInEnglish cardinalInEnglish;
    private final boolean useAndInHundred;

    private EnglishCardinalBlock(Builder builder) {
        super(builder);
        this.cardinalInEnglish = builder.cardinalInEnglish;
        this.useAndInHundred = builder.useAndInHundred;
    }

    private EnglishCardinalBlock(Long value, EnglishCardinalBlock next) {
        super(value, next);
        this.cardinalInEnglish = next.cardinalInEnglish;
        this.useAndInHundred = next.useAndInHundred;
    }

    @Override
    public EnglishCardinalBlock addNext(Long value) {
        return new EnglishCardinalBlock(value, this);
    }

    @Override
    public EnglishCardinalBlock getNextPronounceable() {
        return (EnglishCardinalBlock) super.getNextPronounceable();
    }

    @Override
    public String getNumberDescription() {
        String result = "";

        var numberDescriptionMap = getNumberDescriptionsMap();

        if (this.getValue() == 0)
            result = this.getZeroDescription() + " ";

        if (result.isEmpty()) {
            int hundred = (int) (this.getValue() / 100);
            int dozens = (int) (this.getValue() % 100);

            String and = this.useAndInHundred ? " and " : " ";

            if (hundred > 0)
                result += numberDescriptionMap.get(hundred) + " " + numberDescriptionMap.get(100) + ((dozens > 0) ? and : " ");

            if (dozens > 0 && dozens < 20)
                    result += numberDescriptionMap.get(dozens) + " ";

            if (dozens >= 20) {
                int ten = dozens / 10;
                int one = dozens % 10;

                result += numberDescriptionMap.get(ten * 10) + ((one > 0) ? "-" : " ");

                if (one > 0)
                    result += numberDescriptionMap.get(one) + " ";
            }
        }

        return result;
    }

    private String getZeroDescription() {
        if (this.cardinalInEnglish.getZeroDescription() == null)
            return EnglishCardinalDescriptions.descriptionsMap.get(0);

        return this.cardinalInEnglish.getZeroDescription();
    }

    @Override
    public String getSuffixDescription() {
        return EnglishCardinalDescriptions.getSuffixDescriptionForValue(this.getSuffix(), this.getValue());
    }

    private Map<Integer, String> getNumberDescriptionsMap() {
        return EnglishCardinalDescriptions.descriptionsMap;
    }

    @Override
    public String getConjuction() {
        String comma = this.cardinalInEnglish.isUsingCommaSeparator() ? ", " : " ";

        if (this.isLastPronounceable())
            return "";

        if (!this.getNextPronounceable().isLastPronounceable())
            return comma;

        if (this.getNextPronounceable().getValue() % 100 == 0)
            return comma;

        if (this.getNextPronounceable().getValue() < 100 &&
                this.getNextPronounceable().getSuffix().compareTo(Suffix.NO_SUFFIX) == 0)
            return " and ";

        return comma;
    }

    public static class Builder extends Block.Builder {
        CardinalInEnglish cardinalInEnglish;

        private boolean useAndInHundred = false;

        public Builder(Long number) {
            super(number);
        }

        public Builder withAndInHundred() {
            return this.withAndInHundred(true);
        }

        public Builder withAndInHundred(boolean useAndInHundred) {
            this.useAndInHundred = useAndInHundred;
            return this;
        }

        public Builder withCardinalInEnglish(CardinalInEnglish cardinalInEnglish) {
            this.cardinalInEnglish = cardinalInEnglish;
            return this;
        }

        @Override
        public EnglishCardinalBlock build() {
            EnglishCardinalBlock block = new EnglishCardinalBlock(this);

            for (long number = this.number/1000; number > 0; number /= 1000)
                block = block.addNext(number);

            return block;
        }
    }
}
