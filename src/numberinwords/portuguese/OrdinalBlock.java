package numberinwords.portuguese;

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
    OrdinalBlock addNext(Long value) {
        return new OrdinalBlock(value, this);
    }

    @Override
    OrdinalBlock getNextPronounceableBlock() {
        return (OrdinalBlock) super.getNextPronounceableBlock();
    }

    public String inWords() {
        var numberDescriptionMap = getNumberDescriptionsMapForGender(gender);

        String result = "";
        String comma = useCommaSeparator ? ", " : " ";

        if (this.getValue() != 0) {
            int hundred = (int) (this.getValue() / 100);
            int dozens = (int) (this.getValue() % 100);

            if (hundred > 0)
                result += numberDescriptionMap.get(hundred * 100) + " ";

            if (dozens > 0 && dozens < 10)
                //ex. 1200. previne 'primeiro milésimo ducentésimo'. fica 'milésimo ducentésimo'
                if (!(dozens == 1 && this.suffix.compareTo(Suffix.NO_SUFFIX) > 0))
                    result += numberDescriptionMap.get(dozens) + " ";

            if (dozens >= 10) {
                int ten = dozens / 10;
                int one = dozens % 10;

                result += numberDescriptionMap.get(ten * 10) + " ";

                if (one > 0)
                    result += numberDescriptionMap.get(one) + " ";
            }
        }

        result += OrdinalDescriptions.getSuffixDescriptionForGender(suffix, gender) + " ";

        if (this.isLastPronounceableBlock())
            return result;

        return result.trim() + comma;
    }

    Map<Integer, String> getNumberDescriptionsMapForGender(Gender gender) {
        if (gender.equals(Gender.MALE))
            return OrdinalDescriptions.maleDescriptionsMap;

        return OrdinalDescriptions.femaleDescriptionsMap;
    }

    public static class Builder extends Block.Builder {
        public boolean useCommaSeparator;
        public Gender gender;

        Builder withNumber(Long number) {
            this.number = Math.abs(number);
            return this;
        }

        Builder withCommaSeparator(boolean useCommaSeparator) {
            this.useCommaSeparator = useCommaSeparator;
            return this;
        }

        Builder withGender(Gender gender) {
            this.gender = gender;
            return this;
        }

        OrdinalBlock build() {
            OrdinalBlock block = new OrdinalBlock(this);

            for (long number = this.number/1000; number > 0; number /= 1000)
                block = block.addNext(number);

            return block;
        }
    }
}
