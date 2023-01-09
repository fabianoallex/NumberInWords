package numberinwords.portuguese;

import numberinwords.Block;
import numberinwords.Gender;
import numberinwords.Suffix;

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
    public CardinalBlock getNextPronounceable() {
        return (CardinalBlock) super.getNextPronounceable();
    }

    @Override
    public String getNumberDescription() {
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
                if (!(dozens == 1 && this.getSuffix().equals(Suffix.THOUSAND)))
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

    @Override
    public String getSuffixDescription() {
        return CardinalDescriptions.getSuffixDescriptionForValue(this.getSuffix(), this.getValue());
    }

    private Map<Integer, String> getNumberDescriptionsMap(Gender gender) {
        if (gender.equals(Gender.MALE))
            return CardinalDescriptions.maleDescriptionsMap;

        if (this.getSuffix().equals(Suffix.NO_SUFFIX) || this.getSuffix().equals(Suffix.THOUSAND))
            return CardinalDescriptions.femaleDescriptionsMap;

        return CardinalDescriptions.maleDescriptionsMap;
    }

    @Override
    public String getConjuction() {
        String comma = this.useCommaSeparator ? ", " : " ";

        if (this.isLastPronounceable())
            return "";

        if (!this.getNextPronounceable().isLastPronounceable())
            return comma;

        if (this.getNextPronounceable().getValue() % 100 == 0)
            return " e ";

        if (this.getNextPronounceable().getValue() < 100)
            return " e ";

        return comma;
    }

    public static class Builder extends Block.Builder {
        boolean useCommaSeparator = false;
        String zeroDescription;
        public Gender gender;

        public Builder(Long number) {
            super(number);
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
