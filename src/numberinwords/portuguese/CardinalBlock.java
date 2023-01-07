package numberinwords.portuguese;

import java.util.Map;

public class CardinalBlock extends Block {
    private CardinalBlock(Builder builder) {
        super(builder);
    }

    private CardinalBlock(Long value, CardinalBlock next) {
        super(value, next);
    }

    @Override
    CardinalBlock addNext(Long value) {
        return new CardinalBlock(value, this);
    }

    @Override
    CardinalBlock getNextPronounceableBlock() {
        return (CardinalBlock) super.getNextPronounceableBlock();
    }

    Map<Integer, String> getNumberDescriptionsMap(Gender gender, Suffix suffix) {
        if (gender.equals(Gender.MALE))
            return CardinalDescriptions.maleDescriptionsMap;

        if (suffix.equals(Suffix.NO_SUFFIX) || suffix.equals(Suffix.THOUSAND))
            return CardinalDescriptions.femaleDescriptionsMap;

        return CardinalDescriptions.maleDescriptionsMap;
    }

    public String inWords(boolean useCommaSeparator, Gender gender, String zeroDescription) {
        if (zeroDescription == null)
            zeroDescription = CardinalDescriptions.maleDescriptionsMap.get(0);

        String result = "";
        String comma = useCommaSeparator ? ", " : " ";

        var numberDescriptionMap = getNumberDescriptionsMap(gender, this.suffix);

        if (this.getValue() == 0)
            result = zeroDescription + " ";

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

        result += CardinalDescriptions.getSuffixDescriptionForValue(suffix, getValue());

        if (this.isLastPronounceableBlock())
            return result;

        if (!this.getNextPronounceableBlock().isLastPronounceableBlock())
            return result + comma;

        if (this.getNextPronounceableBlock().getValue() % 100 == 0)
            return result + " e ";

        if (this.getNextPronounceableBlock().getValue() < 100)
            return result + " e ";

        return result + comma;
    }

    public static class Builder extends Block.Builder {
        Builder withNumber(Long number) {
            this.number = Math.abs(number);
            return this;
        }

        CardinalBlock build() {
            CardinalBlock block = new CardinalBlock(this);

            for (long number = this.number/1000; number > 0; number /= 1000)
                block = block.addNext(number);

            return block;
        }
    }
}
