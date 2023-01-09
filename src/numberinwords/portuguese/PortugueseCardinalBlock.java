package numberinwords.portuguese;

import numberinwords.Block;
import numberinwords.Gender;
import numberinwords.Suffix;

import java.util.Map;

public class PortugueseCardinalBlock extends Block {
    private final CardinalInPortuguese cardinalInPortuguese;

    private PortugueseCardinalBlock(Builder builder) {
        super(builder);
        this.cardinalInPortuguese = builder.cardinalInPortuguese;
    }

    private PortugueseCardinalBlock(Long value, PortugueseCardinalBlock next) {
        super(value, next);
        this.cardinalInPortuguese = next.cardinalInPortuguese;
    }

    @Override
    public PortugueseCardinalBlock addNext(Long value) {
        return new PortugueseCardinalBlock(value, this);
    }

    @Override
    public PortugueseCardinalBlock getNextPronounceable() {
        return (PortugueseCardinalBlock) super.getNextPronounceable();
    }

    @Override
    public String getNumberDescription() {
        String result = "";

        var numberDescriptionMap = getNumberDescriptionsMap(this.cardinalInPortuguese.getGender());

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
        if (this.cardinalInPortuguese.getZeroDescription() == null)
            return PortugueseCardinalDescriptions.maleDescriptionsMap.get(0);

        return this.cardinalInPortuguese.getZeroDescription();
    }

    @Override
    public String getSuffixDescription() {
        return PortugueseCardinalDescriptions.getSuffixDescriptionForValue(this.getSuffix(), this.getValue());
    }

    private Map<Integer, String> getNumberDescriptionsMap(Gender gender) {
        if (gender.equals(Gender.MALE))
            return PortugueseCardinalDescriptions.maleDescriptionsMap;

        if (this.getSuffix().equals(Suffix.NO_SUFFIX) || this.getSuffix().equals(Suffix.THOUSAND))
            return PortugueseCardinalDescriptions.femaleDescriptionsMap;

        return PortugueseCardinalDescriptions.maleDescriptionsMap;
    }

    @Override
    public String getConjuction() {
        String comma = this.cardinalInPortuguese.isUsingCommaSeparator() ? ", " : " ";

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
        CardinalInPortuguese cardinalInPortuguese;

        public Builder(Long number) {
            super(number);
        }

        public Builder withCardinalInPortuguese(CardinalInPortuguese cardinalInPortuguese) {
            this.cardinalInPortuguese = cardinalInPortuguese;
            return this;
        }

        @Override
        public PortugueseCardinalBlock build() {
            PortugueseCardinalBlock block = new PortugueseCardinalBlock(this);

            for (long number = this.number/1000; number > 0; number /= 1000)
                block = block.addNext(number);

            return block;
        }
    }
}
