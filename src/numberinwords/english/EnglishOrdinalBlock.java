package numberinwords.english;

import numberinwords.Block;
import numberinwords.Suffix;

import java.util.Map;

public class EnglishOrdinalBlock extends Block {
    private final OrdinalInEnglish ordinalInEnglish;
    private final boolean useAndInHundred;

    public EnglishOrdinalBlock(Builder builder) {
        super(builder);
        this.ordinalInEnglish = builder.ordinalInEnglish;
        this.useAndInHundred = builder.useAndInHundred;
    }

    public EnglishOrdinalBlock(Long value, EnglishOrdinalBlock next) {
        super(value, next);
        this.ordinalInEnglish = next.ordinalInEnglish;
        this.useAndInHundred = next.useAndInHundred;
    }

    @Override
    public EnglishOrdinalBlock addNext(Long value) {
        return new EnglishOrdinalBlock(value, this);
    }

    @Override
    public EnglishOrdinalBlock getNextPronounceable() {
        return (EnglishOrdinalBlock) super.getNextPronounceable();
    }

    @Override
    public String getSuffixDescription() {
        if (this.isLastPronounceable())
            return EnglishOrdinalDescriptions.getSuffixDescriptionForValue(this.getSuffix(), this.getValue());

        return EnglishCardinalDescriptions.getSuffixDescriptionForValue(this.getSuffix(), this.getValue());
    }

    @Override
    public String getConjuction() {
        String comma = this.ordinalInEnglish.isUsingCommaSeparator() ? ", " : " ";

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

    @Override
    public String getNumberDescription() {
        String result = "";

        if (this.getValue() == 0)
            return result;

        var cardinalNumberDescriptionsMap = getCardinalNumberDescriptionsMap();
        var ordinalNumberDescriptionsMap = getOrdinalNumberDescriptionsMap();

        int hundred = (int) (this.getValue() / 100);
        int dozens = (int) (this.getValue() % 100);

        String and = this.useAndInHundred ? " and " : " ";

        if (hundred > 0)
            result += cardinalNumberDescriptionsMap.get(hundred) + " " + getNumberDescriptionsMap(dozens).get(100) + ((dozens > 0) ? and : " ");

        if (dozens == 0)
            return result;

        if (dozens > 0 && dozens < 20)
            return result + getNumberDescriptionsMap(0).get(dozens) + " ";

        int ten = dozens / 10;
        int one = dozens % 10;

        result += getNumberDescriptionsMap(one).get(ten * 10) + ((one > 0) ? "-" : " ");

        if (one > 0)
            result += getNumberDescriptionsMap(0).get(one) + " ";

        return result;
    }

    private Map<Integer, String> getNumberDescriptionsMap(long nextValue) {
        if (nextValue == 0 && this.isLastPronounceable() && this.getSuffix().compareTo(Suffix.NO_SUFFIX) == 0)
            return this.getOrdinalNumberDescriptionsMap();

        return this.getCardinalNumberDescriptionsMap();
    }

    private Map<Integer, String> getCardinalNumberDescriptionsMap() {
        return EnglishCardinalDescriptions.descriptionsMap;
    }

    private Map<Integer, String> getOrdinalNumberDescriptionsMap() {
        return EnglishOrdinalDescriptions.descriptionsMap;
    }

    public static class Builder extends Block.Builder {
        OrdinalInEnglish ordinalInEnglish;

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

        public Builder withOrdinalInEnglish(OrdinalInEnglish ordinalInEnglish) {
            this.ordinalInEnglish = ordinalInEnglish;
            return this;
        }

        @Override
        public EnglishOrdinalBlock build() {
            EnglishOrdinalBlock block = new EnglishOrdinalBlock(this);

            for (long number = this.number/1000; number > 0; number /= 1000)
                block = block.addNext(number);

            return block;
        }
    }
}
