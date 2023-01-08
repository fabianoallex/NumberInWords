package numberinwords.portuguese;

import java.util.Map;

public class CardinalBlock extends Block {
    private final boolean useCommaSeparator;
    private final String zeroDescription;
    private final Gender gender;

    private CardinalBlock(Builder builder) {
        super(builder);
        this.useCommaSeparator = builder.useCommaSeparator;
        this.zeroDescription = builder.zeroDescription;
        this.gender = builder.gender;
    }

    private CardinalBlock(Long value, CardinalBlock next) {
        super(value, next);
        this.useCommaSeparator = next.useCommaSeparator;
        this.zeroDescription = next.zeroDescription;
        this.gender = next.gender;
    }

    @Override
    public CardinalBlock addNext(Long value) {
        return new CardinalBlock(value, this);
    }

    @Override
    CardinalBlock getNextPronounceableBlock() {
        return (CardinalBlock) super.getNextPronounceableBlock();
    }

    @Override
    protected String getNumberDescription() {
        String result = "";

        var numberDescriptionMap = getNumberDescriptionsMap(this.gender);

        if (this.getValue() == 0)
            result = this.getZeroDescription() + " ";

        if (this.getValue() == 100)
            result = numberDescriptionMap.get(-100) + " "; //-100 returns 'cem'. +100 returns 'cento'

        if (result.isEmpty()) {
            int hundred = (int) (this.getValue() / 100);
            int dozens = (int) (this.getValue() % 100);

            if (hundred > 0)
                result += numberDescriptionMap.get(hundred * 100) + ((dozens > 0) ? " e " : " ");

            if (dozens > 0 && dozens < 20)
                //ex. 1200. previne 'um mil e duzentos'. fica 'mil e duzentos'
                if (!(dozens == 1 && this.suffix.equals(Suffix.THOUSAND)))
                    result += numberDescriptionMap.get(dozens) + " ";

            if (dozens >= 20) {
                int ten = dozens / 10;
                int one = dozens % 10;

                result += numberDescriptionMap.get(ten * 10) + ((one > 0) ? " e " : " ");

                if (one > 0)
                    result += numberDescriptionMap.get(one) + " ";
            }
        }

        return result;
    }

    private String getZeroDescription() {
        if (this.zeroDescription == null)
            return CardinalDescriptions.maleDescriptionsMap.get(0);

        return this.zeroDescription;
    }

    protected String getSuffixDescription() {
        return CardinalDescriptions.getSuffixDescriptionForValue(this.suffix, this.getValue());
    }

    private Map<Integer, String> getNumberDescriptionsMap(Gender gender) {
        if (gender.equals(Gender.MALE))
            return CardinalDescriptions.maleDescriptionsMap;

        if (this.suffix.equals(Suffix.NO_SUFFIX) || this.suffix.equals(Suffix.THOUSAND))
            return CardinalDescriptions.femaleDescriptionsMap;

        return CardinalDescriptions.maleDescriptionsMap;
    }

    @Override
    protected String getConjuction() {
        String comma = this.useCommaSeparator ? ", " : " ";

        if (this.isLastPronounceableBlock())
            return "";

        if (!this.getNextPronounceableBlock().isLastPronounceableBlock())
            return comma;

        if (this.getNextPronounceableBlock().getValue() % 100 == 0)
            return " e ";

        if (this.getNextPronounceableBlock().getValue() < 100)
            return " e ";

        return comma;
    }

    public static class Builder extends Block.Builder {
        boolean useCommaSeparator = false;
        String zeroDescription;
        public Gender gender;

        @Override
        public Builder withNumber(Long number) {
            this.number = Math.abs(number);
            return this;
        }

        public Builder withCommaSeparator(boolean useCommaSeparator) {
            this.useCommaSeparator = useCommaSeparator;
            return this;
        }

        public Builder withZeroDescription(String zeroDescription) {
            this.zeroDescription = zeroDescription;
            return this;
        }

        public Builder withGender(Gender gender) {
            this.gender = gender;
            return this;
        }

        @Override
        public CardinalBlock build() {
            CardinalBlock block = new CardinalBlock(this);

            for (long number = this.number/1000; number > 0; number /= 1000)
                block = block.addNext(number);

            return block;
        }
    }
}
