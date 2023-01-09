package numberinwords.portuguese;

import numberinwords.Block;
import numberinwords.Gender;
import numberinwords.Suffix;

import java.util.Map;

public class OrdinalBlock extends Block {
    private final boolean useCommaSeparator;
    private final Gender gender;

    private OrdinalBlock(Builder builder) {
        super(builder);
        this.useCommaSeparator = builder.useCommaSeparator;
        this.gender = builder.gender;
    }

    private OrdinalBlock(Long value, OrdinalBlock next) {
        super(value, next);
        this.useCommaSeparator = next.useCommaSeparator;
        this.gender = next.gender;
    }

    @Override
    public OrdinalBlock addNext(Long value) {
        return new OrdinalBlock(value, this);
    }

    @Override
    public OrdinalBlock getNextPronounceable() {
        return (OrdinalBlock) super.getNextPronounceable();
    }

    @Override
    public String getNumberDescription() {
        var numberDescriptionMap = getNumberDescriptionsMapForGender(gender);

        String result = "";

        if (this.getValue() != 0) {
            int hundred = (int) (this.getValue() / 100);
            int dozens = (int) (this.getValue() % 100);

            if (hundred > 0)
                result += numberDescriptionMap.get(hundred * 100) + " ";

            if (dozens > 0 && dozens < 10)
                //ex. 1200. previne 'primeiro milésimo ducentésimo'. fica 'milésimo ducentésimo'
                if (!(dozens == 1 && this.getSuffix().compareTo(Suffix.NO_SUFFIX) > 0))
                    result += numberDescriptionMap.get(dozens) + " ";

            if (dozens >= 10) {
                int ten = dozens / 10;
                int one = dozens % 10;

                result += numberDescriptionMap.get(ten * 10) + " ";

                if (one > 0)
                    result += numberDescriptionMap.get(one) + " ";
            }
        }

        return result;
    }

    @Override
    public String getConjuction() {
        if (this.isLastPronounceable())
            return "";

        return this.useCommaSeparator ? ", " : " ";
    }

    @Override
    public String getSuffixDescription() {
        return OrdinalDescriptions.getSuffixDescriptionForGender(this.getSuffix(), gender);
    }

    private Map<Integer, String> getNumberDescriptionsMapForGender(Gender gender) {
        if (gender.equals(Gender.MALE))
            return OrdinalDescriptions.maleDescriptionsMap;

        return OrdinalDescriptions.femaleDescriptionsMap;
    }

    public static class Builder extends Block.Builder {
        public boolean useCommaSeparator;
        public Gender gender;

        public Builder(Long number) {
            super(number);
        }

        public Builder withCommaSeparator(boolean useCommaSeparator) {
            this.useCommaSeparator = useCommaSeparator;
            return this;
        }

        public Builder withGender(Gender gender) {
            this.gender = gender;
            return this;
        }

        @Override
        public OrdinalBlock build() {
            OrdinalBlock block = new OrdinalBlock(this);

            for (long number = this.number/1000; number > 0; number /= 1000)
                block = block.addNext(number);

            return block;
        }
    }
}
